package net.eldiosantos.brutauth.interceptor;

import net.eldiosantos.brutauth.annotations.HandledBy;
import net.eldiosantos.brutauth.annotations.BrutauthValidation;
import net.eldiosantos.brutauth.annotations.CustomBrutauthRules;
import net.eldiosantos.brutauth.handler.RuleHandler;
import net.eldiosantos.brutauth.interceptor.extractor.HandlerExtractor;
import net.eldiosantos.brutauth.rules.CustomBrutauthRule;
import net.eldiosantos.brutauth.interceptor.extractor.AnnotationExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by Eldius on 16/05/2015.
 */
@Interceptor
@CustomBrutauthRules
public class CustomBrutauthRuleInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    @Any
    private Instance<CustomBrutauthRule> rules;

    @Inject
    @Any
    private Instance<RuleHandler> handlers;

    @Inject
    private AnnotationExtractor annotationExtractor;

    @Inject
    private HandlerExtractor handlerExtractor;

    @AroundInvoke
    public Object manageTransaction(InvocationContext ctx) throws Exception {

        CustomBrutauthRules rule = annotationExtractor.getAnnotation(ctx, CustomBrutauthRules.class);

        Boolean isValid = Boolean.TRUE;

        for(CustomBrutauthRule r: getRules(rule)) {
            isValid = isValid && execute(getMethod(r), r, ctx);
        }

        if(isValid) {
            return ctx.proceed();
        } else {
            handlerExtractor.getHandler(annotationExtractor.getAnnotation(ctx, HandledBy.class)).handle();
            return null;
        }
    }

    private Set<? extends CustomBrutauthRule>getRules(CustomBrutauthRules annotation) {
        final Set<CustomBrutauthRule>applicableRules = new HashSet<>();

        for(CustomBrutauthRule rule: rules) {
            if(Arrays.asList(annotation.value()).contains(rule.getClass())) {
                applicableRules.add(rule);
            }
        }

        return applicableRules;
    }

    public Boolean execute(final Method ruleMethod, CustomBrutauthRule rule, final InvocationContext ctx) throws InvocationTargetException, IllegalAccessException {

        final List<Type>targetParamTypes = Arrays.asList(ctx.getMethod().getGenericParameterTypes());
        final List<Type>validatorParamTypes = Arrays.asList(ruleMethod.getGenericParameterTypes());
        final List<Object>targetParams = Arrays.asList(ctx.getParameters());
        final List<Object>params = new ArrayList<>();

        for(Type type: validatorParamTypes) {
            final int index = targetParamTypes.indexOf(type);
            if(index > -1) {
                params.add(targetParams.get(index));
            }
        }

        return (Boolean)ruleMethod.invoke(rule, params.toArray());
    }

    private Method getMethod(final CustomBrutauthRule rule) {
        final Class<? extends CustomBrutauthRule> type = rule.getClass();
        for(Method m: type.getMethods()) {
            if(m.isAnnotationPresent(BrutauthValidation.class) || (m.getName().equals("isAllowed"))) {
                return m;
            }
        }

        throw new IllegalArgumentException(String.format("%s is an invalid CustomBrutauthRule", type.getCanonicalName()));
    }
}
