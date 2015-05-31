package net.eldiosantos.brutauth.support.rule;

import net.eldiosantos.brutauth.annotations.HandledBy;
import net.eldiosantos.brutauth.rules.SimpleBrutauthRule;
import net.eldiosantos.brutauth.support.handler.TestHandler;

/**
 * Created by Eldius on 31/05/2015.
 */
@HandledBy(TestHandler.class)
public class AnotherSimpleBrutauthRule implements SimpleBrutauthRule {

    @Override
    public Boolean canAccess(Long accessLevel) {
        return null;
    }
}
