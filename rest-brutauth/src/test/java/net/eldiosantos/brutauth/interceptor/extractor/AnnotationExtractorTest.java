package net.eldiosantos.brutauth.interceptor.extractor;

import net.eldiosantos.brutauth.annotations.HandledBy;
import net.eldiosantos.brutauth.support.resource.SimpleResource;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import java.lang.annotation.Annotation;

import static org.junit.Assert.*;

/**
 * Created by Eldius on 31/05/2015.
 */
@RunWith(CdiRunner.class)
public class AnnotationExtractorTest {

    private final AnnotationExtractor annotationExtractor = new AnnotationExtractor();

    @Inject
    private SimpleResource resource;

    @Mock
    private InvocationContext context;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetNullAnnotation() throws Exception {
        Mockito.when(context.getTarget()).thenReturn(new SimpleResource());
        Mockito.when(context.getMethod()).thenReturn(SimpleResource.class.getMethod("method"));

        final Annotation annotation = annotationExtractor.getAnnotation(context, HandledBy.class);

        Mockito.verify(context, Mockito.times(2)).getTarget();
        Mockito.verify(context, Mockito.times(1)).getMethod();

        assertNull("We can't find this annotation, right?", annotation);
    }

    @Test
    public void testGetClassAnnotation() throws Exception {
        Mockito.when(context.getTarget()).thenReturn(new SimpleResource());

        final Annotation annotation = annotationExtractor.getAnnotation(context, Path.class);

        Mockito.verify(context, Mockito.times(2)).getTarget();

        assertNotNull("I hope we can find this annotation now", annotation);
    }

    @Test
    public void testGetMethodAnnotation() throws Exception {
        Mockito.when(context.getTarget()).thenReturn(new SimpleResource());
        Mockito.when(context.getMethod()).thenReturn(SimpleResource.class.getMethod("method"));

        final Annotation annotation = annotationExtractor.getAnnotation(context, GET.class);

        Mockito.verify(context, Mockito.times(1)).getTarget();
        Mockito.verify(context, Mockito.times(2)).getMethod();

        assertNotNull("I hope we can find this annotation now", annotation);
    }
}