package net.eldiosantos.brutauth.support.handler;

import net.eldiosantos.brutauth.handler.RuleHandler;


/**
 * Created by Eldius on 31/05/2015.
 */
public class ErrorGeneratorRuleHandler implements RuleHandler {
    @Override
    public void handle() throws Exception {
        throw new IllegalStateException("Oops!");
    }
}
