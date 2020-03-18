package com.paraamarsh.jobpost;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;

import com.paraamarsh.jobpost.client.OAuth2InterceptedFeignConfiguration;
import com.paraamarsh.jobpost.config.ApplicationProperties;
import com.paraamarsh.jobpost.config.Constants;
import com.paraamarsh.jobpost.config.DefaultProfileUtil;

import io.github.jhipster.config.JHipsterConstants;

@ComponentScan(
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = OAuth2InterceptedFeignConfiguration.class)
)
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableDiscoveryClient
public class JobPostApp implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(JobPostApp.class);

    private final Environment env;

    public JobPostApp(Environment env) {
        this.env = env;
    }

    /**
     * Initializes jobPost.
     * <p>
     * Spring profiles can be configured with a program argument --spring.profiles.active=your-active-profile
     * <p>
     * You can find more information on how profiles work with JHipster on <a href="https://www.jhipster.tech/profiles/">https://www.jhipster.tech/profiles/</a>.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            log.error("You have misconfigured your application! It should not run " +
                "with both the 'dev' and 'prod' profiles at the same time.");
        }
        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT) && activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_CLOUD)) {
            log.error("You have misconfigured your application! It should not " +
                "run with both the 'dev' and 'cloud' profiles at the same time.");
        }
    }

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(JobPostApp.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = Constants.HTTP;
        if (env.getProperty(Constants.SERVER_SSL_KEY_STORE) != null) {
            protocol = Constants.HTTP;
        }
        String serverPort = env.getProperty(Constants.SERVER_PORT);
        String contextPath = env.getProperty(Constants.SERVER_SERVLET_CONTEXT_PATH);
        if (StringUtils.isBlank(contextPath)) {
            contextPath =Constants.SLASH;
        }
        String hostAddress = Constants.LOCAL_HOST;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}{}\n\t" +
                "External: \t{}://{}:{}{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            serverPort,
            contextPath,
            protocol,
            hostAddress,
            serverPort,
            contextPath,
            env.getActiveProfiles());

        String configServerStatus = env.getProperty(Constants.CONFIG_SERVER_STATUS);
        if (configServerStatus == null) {
            configServerStatus = Constants.NOT_FOUND_OR_NOT_SETUP_FOR_THIS_APPLICATION;
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Config Server: \t{}\n----------------------------------------------------------", configServerStatus);
    }
}
