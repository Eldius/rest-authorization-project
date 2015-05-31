package net.eldiosantos.brutauth.interceptor.extractor;

import javax.interceptor.InvocationContext;
import java.lang.annotation.Annotation;

/**
 * Created by Eldius on 23/05/2015.
 */
public class AnnotationExtractor {

    public <T extends Annotation> T getAnnotation(final InvocationContext ctx, final Class<T> type) {
        if(ctx.getTarget().getClass().isAnnotationPresent(type)) {
            return ctx.getTarget().getClass().getAnnotation(type);
        } else if(ctx.getMethod().isAnnotationPresent(type)) {
            return ctx.getMethod().getAnnotation(type);
        } else {
            return ctx.getTarget().getClass().getSuperclass().getAnnotation(type);
        }
    }
}
