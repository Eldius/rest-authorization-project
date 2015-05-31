package net.eldiosantos.brutauth.interceptor;

import net.eldiosantos.brutauth.annotations.HandledBy;
import net.eldiosantos.brutauth.annotations.SimpleBrutauthRules;
import net.eldiosantos.brutauth.handler.RuleHandler;
import net.eldiosantos.brutauth.interceptor.extractor.HandlerExtractor;
import net.eldiosantos.brutauth.rules.SimpleBrutauthRule;
import net.eldiosantos.brutauth.interceptor.extractor.AnnotationExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Eldius on 16/05/2015.
 */
@Interceptor
@SimpleBrutauthRules
public class SimpleBrutauthRuleInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    @Any
    private Instance<SimpleBrutauthRule> rules;

    @Inject
    private AnnotationExtractor annotationExtractor;

    @Inject
    private HandlerExtractor handlerExtractor;

    @Deprecated
    public SimpleBrutauthRuleInterceptor() {
    }

    public SimpleBrutauthRuleInterceptor(Instance<SimpleBrutauthRule> rules, AnnotationExtractor annotationExtractor, HandlerExtractor handlerExtractor) {
        this.rules = rules;
        this.annotationExtractor = annotationExtractor;
        this.handlerExtractor = handlerExtractor;
    }

    @AroundInvoke
    public Object applyRules(final InvocationContext ctx) throws Exception {
        logger.debug("Starting rules validation...");
        boolean isValid = true;
        final SimpleBrutauthRules annotation = annotationExtractor.getAnnotation(ctx, SimpleBrutauthRules.class);
        final Iterator<? extends SimpleBrutauthRule> ruleIt = getRules(annotation).iterator();
        Boolean passed = false;
        do {
            final SimpleBrutauthRule rule = ruleIt.next();
            passed = validate(annotation, rule);
            if(!passed) {
                handleAcessDenied(rule);
                logger.info("Could not execute method...");
                return null;
            }
        } while(ruleIt.hasNext() && (passed));

        return ctx.proceed();

    }

    private void handleAcessDenied(final SimpleBrutauthRule rule) {
        try {
            final RuleHandler handler = handlerExtractor.getHandler(rule.getClass().getAnnotation(HandledBy.class));
            handler.handle();
        } catch (Exception e) {
            logger.debug("Error execunting rule handler.", e);
        }
    }

    private Boolean validate(final SimpleBrutauthRules annotation, final SimpleBrutauthRule rule) {
        Boolean passed = false;
        try {
            passed = rule.canAccess(annotation.accessLevel());
        } catch (Exception e) {
            logger.debug("Error validating user acces.", e);
            passed = false;
        }
        return passed;
    }

    private Set<? extends SimpleBrutauthRule> getRules(final SimpleBrutauthRules annotation) {
        final Set<SimpleBrutauthRule>applicableRules = new HashSet<>();
        final Set<Class<? extends SimpleBrutauthRule>>ruleSet = new HashSet<>(Arrays.asList(annotation.value()));

        for(SimpleBrutauthRule rule: rules) {
            if(ruleSet.contains(rule.getClass()) || ruleSet.contains(rule.getClass().getSuperclass())) {
                applicableRules.add(rule);
            }
        }

        return applicableRules;
    }
}
