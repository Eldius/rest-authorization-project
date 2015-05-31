package net.eldiosantos.brutauth.interceptor;

import net.eldiosantos.brutauth.interceptor.SimpleBrutauthRuleInterceptor;
import net.eldiosantos.brutauth.support.handler.ErrorGeneratorRuleHandler;
import net.eldiosantos.brutauth.support.resource.AnotherSimpleResource;
import net.eldiosantos.brutauth.support.resource.SimpleResource;
import net.eldiosantos.brutauth.support.rule.AnotherSimpleBrutauthRule;
import net.eldiosantos.brutauth.support.rule.OneMoreSimpleBrutauthRule;
import net.eldiosantos.brutauth.support.rule.TestSimpleBrutauthRule;
import org.jglue.cdiunit.AdditionalPackages;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.InRequestScope;
import org.jglue.cdiunit.ProducesAlternative;
import org.jglue.cdiunit.jaxrs.SupportJaxRs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Eldius on 31/05/2015.
 */
@RunWith(CdiRunner.class)
@SupportJaxRs
@AdditionalPackages({
        SimpleBrutauthRuleInterceptor.class
        , TestSimpleBrutauthRule.class
        , AnotherSimpleBrutauthRule.class
        , OneMoreSimpleBrutauthRule.class
        , AnotherSimpleResource.class
        , ErrorGeneratorRuleHandler.class
})
public class SimpleBrutauthRuleInterceptorTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private SimpleResource simpleResource;

    @Inject
    private AnotherSimpleResource anotherSimpleResource;

    @Mock
    @Produces
    private TestSimpleBrutauthRule rule;

    @Context
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    @InRequestScope
    public void testAccessDenied() throws Exception {
        logger.debug("response: " + response.toString());

        Mockito.when(rule.canAccess(0L)).thenReturn(false);

        final Object result = simpleResource.method();

        Mockito.verify(rule, Mockito.atLeastOnce()).canAccess(0L);

        assertNull("You can't pass", result);
    }

    @Test
    @InRequestScope
    public void testAccessAllowed() throws Exception {
        logger.debug("response: " + response.toString());

        Mockito.when(rule.canAccess(0L)).thenReturn(true);

        final Object result = simpleResource.method();

        Mockito.verify(rule, Mockito.atLeastOnce()).canAccess(0L);

        assertEquals("Go ahead! You can pass", "It's alive!!", result);
    }

    @Test
    @InRequestScope
    public void testValidationError() throws Exception {
        logger.debug("response: " + response.toString());

        Mockito.when(rule.canAccess(0L))
                .thenThrow(new IllegalArgumentException("Dude, I think you screwed everything!"));

        final Object result = simpleResource.method();

        Mockito.verify(rule, Mockito.atLeastOnce()).canAccess(0L);
        assertNull("You can't reach the content because someone screwed the validation", result);
    }

    @Test
    @InRequestScope
    public void testHandlerError() throws Exception {
        logger.debug("response: " + response.toString());

        final Object result = anotherSimpleResource.method();

        assertNull("The handle crashed during his execution...", result);
    }
}
