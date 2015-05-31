package net.eldiosantos.brutauth.handler;

import net.eldiosantos.brutauth.handler.DefaultRuleHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Eldius on 31/05/2015.
 */
public class DefaultRuleHandlerTest {

    private DefaultRuleHandler defaultRuleHandler;

    @Mock
    private HttpServletResponse servletResponse;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        defaultRuleHandler = new DefaultRuleHandler(servletResponse);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHandle() throws Exception {
        defaultRuleHandler.handle();
        Mockito.verify(servletResponse, Mockito.atLeastOnce()).sendError(403, "YOU SHALL NOT PASS!!");
    }
}
