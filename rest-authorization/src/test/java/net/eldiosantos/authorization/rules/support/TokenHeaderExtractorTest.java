package net.eldiosantos.authorization.rules.support;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

/**
 * Created by esjunior on 23/07/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class TokenHeaderExtractorTest {

    @Mock
    private HttpServletRequest request;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testExtract() throws Exception {
        final String magicWords = "Open Sesame";
        when(request.getHeader(TokenHeaderExtractor.AUTHORIZATION_HEADER)).thenReturn("token " + magicWords);
        final String extractedToken = new TokenHeaderExtractor(request).extract();

        assertEquals("It takes the right token", magicWords, extractedToken);
    }

    @Test(expected = IllegalStateException.class)
    public void testExtractWrongHeaderContent() throws Exception {
        final String magicWords = "Open Sesame";
        when(request.getHeader(TokenHeaderExtractor.AUTHORIZATION_HEADER)).thenReturn(magicWords);
        final String extractedToken = new TokenHeaderExtractor(request).extract();

    }

    @Test(expected = IllegalStateException.class)
    public void testExtractWithoutHeaderContent() throws Exception {
        when(request.getHeader(TokenHeaderExtractor.AUTHORIZATION_HEADER)).thenReturn(null);
        final String extractedToken = new TokenHeaderExtractor(request).extract();

    }
}