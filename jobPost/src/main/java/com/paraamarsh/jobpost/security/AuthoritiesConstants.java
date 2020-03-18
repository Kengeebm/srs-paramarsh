package com.paraamarsh.jobpost.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String RECRUITERMANAGER = "ROLE_RECRUITERMANAGER";

    public static final String RECRUITER = "ROLE_RECRUITER";

    public static final String APPADMIN = "ROLE_APPADMIN";

    public static final String SUPERADMIN = "ROLE_SUPERADMIN";

    public static final String IM_URL = "http://192.168.1.47:8083";

    private AuthoritiesConstants() {
    }
}
