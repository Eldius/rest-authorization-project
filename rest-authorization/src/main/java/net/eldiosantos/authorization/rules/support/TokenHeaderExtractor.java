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

    public String extract() {
        final String header = request.getHeader(AUTHORIZATION_HEADER);
        return header!=null?header.replace("token ", ""):"";
    }
}
