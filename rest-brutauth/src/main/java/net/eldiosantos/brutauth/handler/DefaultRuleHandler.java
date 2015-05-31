package net.eldiosantos.brutauth.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.io.IOException;

/**
 * Created by Eldius on 16/05/2015.
 */
public class DefaultRuleHandler implements RuleHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Context
    private HttpServletResponse response;

    @Deprecated
    public DefaultRuleHandler() {
    }

    public DefaultRuleHandler(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void handle() throws IOException {
        logger.debug("response: " + response.toString());
        response.sendError(403, "YOU SHALL NOT PASS!!");
    }
}
