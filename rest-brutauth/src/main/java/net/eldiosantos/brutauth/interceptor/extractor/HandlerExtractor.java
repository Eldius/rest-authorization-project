package net.eldiosantos.brutauth.interceptor.extractor;

import net.eldiosantos.brutauth.annotations.HandledBy;
import net.eldiosantos.brutauth.handler.DefaultRuleHandler;
import net.eldiosantos.brutauth.handler.RuleHandler;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

/**
 * Created by Eldius on 23/05/2015.
 */
public class HandlerExtractor {

    @Inject
    @Any
    private Instance<RuleHandler> handlers;

    @Inject
    private DefaultRuleHandler defaultRuleHandler;

    public RuleHandler getHandler(HandledBy annotation) {
        Class<? extends RuleHandler> handlerClass = (annotation!=null?annotation.value(): DefaultRuleHandler.class);
        for(RuleHandler handler:handlers) {
            if(handler.getClass().equals(handlerClass)) {
                return handler;
            }
        }
        return defaultRuleHandler;
    }
}
