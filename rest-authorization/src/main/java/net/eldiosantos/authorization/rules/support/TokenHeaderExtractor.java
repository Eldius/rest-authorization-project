package net.eldiosantos.authorization.rules.support;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

/**
 * Created by Eldius on 16/05/2015.
 */
public class TokenHeaderExtractor {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Context
    private HttpServletRequest request;

    /**
     * Just for CDI eyes.
     */
    @Deprecated
    public TokenHeaderExtractor() {

    }

    public TokenHeaderExtractor(HttpServletRequest request) {
        this.request = request;
    }


    public String extract() {
        final String header = request.getHeader(AUTHORIZATION_HEADER);
        if (header == null || !header.startsWith("token ")) {
            throw new IllegalStateException("User not authenticated.");
        }
        return header.replace("token ", "");
    }
}
