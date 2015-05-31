package net.eldiosantos.brutauth.interceptor.extractor;

import net.eldiosantos.brutauth.annotations.HandledBy;
import net.eldiosantos.brutauth.handler.DefaultRuleHandler;
import net.eldiosantos.brutauth.handler.RuleHandler;
import net.eldiosantos.brutauth.support.handler.TestHandler;
import net.eldiosantos.brutauth.support.rule.AnotherSimpleBrutauthRule;
import net.eldiosantos.brutauth.support.rule.TestSimpleBrutauthRule;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by Eldius on 31/05/2015.
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({
        TestHandler.class
})
public class HandlerExtractorTest {

    @Inject
    private HandlerExtractor handlerExtractor;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetEspecificHandler() throws Exception {
        final HandledBy annotation = AnotherSimpleBrutauthRule.class.getAnnotation(HandledBy.class);
        final RuleHandler handler = handlerExtractor.getHandler(annotation);

        assertTrue("What kind of handler is that?", handler instanceof TestHandler);
    }

    @Test
    public void testGetDefaultHandler() throws Exception {
        final HandledBy annotation = TestSimpleBrutauthRule.class.getAnnotation(HandledBy.class);
        final RuleHandler handler = handlerExtractor.getHandler(annotation);

        assertTrue("What kind of handler is that?", handler instanceof DefaultRuleHandler);
    }
}