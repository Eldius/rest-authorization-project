package net.eldiosantos.brutauth.support.rule;

import net.eldiosantos.brutauth.annotations.HandledBy;
import net.eldiosantos.brutauth.rules.SimpleBrutauthRule;
import net.eldiosantos.brutauth.support.handler.ErrorGeneratorRuleHandler;

/**
 * Created by Eldius on 31/05/2015.
 */
@HandledBy(ErrorGeneratorRuleHandler.class)
public class OneMoreSimpleBrutauthRule implements SimpleBrutauthRule {

    @Override
    public Boolean canAccess(Long accessLevel) {
        return false;
    }
}
