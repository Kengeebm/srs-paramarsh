package com.paraamarsh.jobpost.restTemplate;

import java.io.IOException;
import java.util.List;

import com.paraamarsh.jobpost.security.AuthoritiesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paraamarsh.jobpost.config.Constants;
import com.paraamarsh.jobpost.domain.Candidate;

@Component
public class ImConnection {

    private static String TOKEN = null;

    @Autowired
    RestTemplate restTemplate;

    private void doLogin() {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> req = new HttpEntity<String>("{\"username\":\"jobpostsystem\",\"password\":\"system\"}", headers);
        ResponseEntity<String> en = restTemplate.postForEntity(AuthoritiesConstants.IM_URL + "/api/authenticate", req, String.class);
        JsonNode root = null;
        try {
            root = objectMapper.readTree(en.getBody());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TOKEN = Constants.BEARER + root.path(Constants.ID_TOKEN).asText();
    }

    public List<Candidate> getData(String url) {
        ResponseEntity<List<Candidate>> en = null;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> req;
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (TOKEN != null) {
            headers.set(Constants.AUTHORIZATION, TOKEN);
            req = new HttpEntity<String>(headers);
            en = restTemplate.exchange(url, HttpMethod.GET, req, new ParameterizedTypeReference<List<Candidate>>() {
            });
            if (en.getStatusCode() != HttpStatus.OK) {
                doLogin();
                headers.set(Constants.AUTHORIZATION, TOKEN);
                en = restTemplate.exchange(url, HttpMethod.GET, req, new ParameterizedTypeReference<List<Candidate>>() {
                });
            }
        } else {
            doLogin();
            headers.set(Constants.AUTHORIZATION, TOKEN);
            req = new HttpEntity<String>(headers);
            en = restTemplate.exchange(url, HttpMethod.GET, req, new ParameterizedTypeReference<List<Candidate>>() {
            });
            if (en.getStatusCode() != HttpStatus.OK) {
                doLogin();
                System.out.println(TOKEN);
                headers.set(Constants.AUTHORIZATION, TOKEN);
                en = restTemplate.exchange(url, HttpMethod.GET, req, new ParameterizedTypeReference<List<Candidate>>() {
                });
            }
        }
        return en.getBody();
    }
}
