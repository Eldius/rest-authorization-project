package net.eldiosantos.brutauth.interceptor;

import net.eldiosantos.brutauth.annotations.CustomBrutauthRules;
import net.eldiosantos.brutauth.handler.DefaultRuleHandler;
import net.eldiosantos.brutauth.rules.CustomBrutauthRule;
import net.eldiosantos.brutauth.util.model.Car;
import net.eldiosantos.brutauth.util.resource.MyTestResource;
import net.eldiosantos.brutauth.util.rule.MyTestCustomBrutauthRule;
import org.jglue.cdiunit.AdditionalPackages;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.InRequestScope;
import org.jglue.cdiunit.jaxrs.SupportJaxRs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/**
 * Created by Eldius on 01/08/2015.
 */
@RunWith(CdiRunner.class)
@AdditionalPackages({
        CustomBrutauthRuleInterceptor.class
        , MyTestCustomBrutauthRule.class
        , CustomBrutauthRule.class
        , CustomBrutauthRules.class
        , MyTestResource.class
})
public class CustomBrutauthRuleInterceptorTest {

    @Inject
    private MyTestResource resource;

    @Context
    private HttpServletResponse response;

    @Mock
    @Produces
    private DefaultRuleHandler handler;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    @InRequestScope
    public void testApplyRules0() throws Exception {
        final Car car = new Car(1L, "Brasilia", "Wolksvagen");
        final Response response = resource.post(car, 1L);
        assertEquals("We received the same car, right?", car, response.getEntity());
    }

    @Test
    @InRequestScope
    public void testApplyRules1() throws Exception {
        final Car car = new Car(69L, "Brasilia", "Wolksvagen");
        final Response response = resource.post(car, 2L);
        assertNull("We can't reach the resource, right?", response);
        Mockito.verify(handler, Mockito.atLeastOnce()).handle();
    }

    @Test
    @InRequestScope
    public void testApplyRules2() throws Exception {
        final Car car = new Car(69L, "Brasilia", "Wolksvagen");
        final Response response = resource.put(car, 1L);
        assertNull("We can't reach the resource, right?", response);
        Mockito.verify(handler, Mockito.atLeastOnce()).handle();
    }

    @Test
    @InRequestScope
    public void testApplyRules3() throws Exception {
        final Car car = new Car(69L, "Brasilia", "Wolksvagen");
        final Response response = resource.put(car, 2L);
        assertNull("We can't reach the resource, right?", response);
        Mockito.verify(handler, Mockito.atLeastOnce()).handle();
    }
}
