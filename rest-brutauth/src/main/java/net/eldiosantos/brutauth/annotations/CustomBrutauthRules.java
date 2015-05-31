package net.eldiosantos.brutauth.annotations;


import net.eldiosantos.brutauth.rules.CustomBrutauthRule;

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
public @interface CustomBrutauthRules {

    @Nonbinding
    Class<? extends CustomBrutauthRule>[] value() default {};

    @Nonbinding
    long accessLevel() default 0l;
}
