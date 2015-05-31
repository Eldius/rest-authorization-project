package net.eldiosantos.brutauth.annotations;

import net.eldiosantos.brutauth.handler.DefaultRuleHandler;
import net.eldiosantos.brutauth.handler.RuleHandler;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Eldius on 16/05/2015.
 */
@InterceptorBinding
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandledBy {
    Class<? extends RuleHandler> value() default DefaultRuleHandler.class;
}
