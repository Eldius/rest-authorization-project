package net.eldiosantos.brutauth.annotations;

import net.eldiosantos.brutauth.rules.SimpleBrutauthRule;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Eldius on 16/05/2015.
 */
@InterceptorBinding
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleBrutauthRules {

    @Nonbinding
    Class<? extends SimpleBrutauthRule>[] value() default {};

    @Nonbinding
    long accessLevel() default 0l;
}
