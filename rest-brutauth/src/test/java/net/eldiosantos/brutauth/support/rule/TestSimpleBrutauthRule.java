package net.eldiosantos.brutauth.support.rule;

import net.eldiosantos.brutauth.rules.SimpleBrutauthRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eldius on 31/05/2015.
 */
public class TestSimpleBrutauthRule implements SimpleBrutauthRule {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public Boolean canAccess(Long accessLevel) {
        logger.info("Validating user access...");
        return false;
    }
}
