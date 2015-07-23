package net.eldiosantos.authorization.rules.support;

import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
import net.eldiosantos.brutauth.model.repository.UserSessionAuthRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by esjunior on 23/07/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserSessionAuthExtractorTest {

    @Mock
    private TokenHeaderExtractor tokenHeaderExtractor;

    @Mock
    private UserSessionAuthRepository userSessionAuthRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testExtract() throws Exception {
        final String token = "it's just a session token...";
        when(tokenHeaderExtractor.extract()).thenReturn(token);
        final UserSessionAuth session = new UserSessionAuth();
        when(userSessionAuthRepository.getByPk(token)).thenReturn(session);
        final UserSessionAuth extractedSession = new UserSessionAuthExtractor(tokenHeaderExtractor, userSessionAuthRepository).extract();
        validateMockitoUsage();
        assertTrue("Returned the same instance", session == extractedSession);
    }

    @Test(expected = IllegalStateException.class)
    public void testExtractWithoutAuthHeader() throws Exception {
        when(tokenHeaderExtractor.extract()).thenThrow(new IllegalStateException("Ooops!"));
        final UserSessionAuth session = new UserSessionAuth();
        final UserSessionAuth extractedSession = new UserSessionAuthExtractor(tokenHeaderExtractor, userSessionAuthRepository).extract();
    }
}
