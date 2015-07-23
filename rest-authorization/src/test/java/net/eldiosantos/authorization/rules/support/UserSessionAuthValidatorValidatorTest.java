package net.eldiosantos.authorization.rules.support;

import net.eldiosantos.brutauth.model.auth.Credentials;
import net.eldiosantos.brutauth.model.auth.User;
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

/**
 * Created by esjunior on 23/07/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserSessionAuthValidatorValidatorTest {

    @Mock
    private UserSessionAuthExtractor userSessionAuthExtractor;

    @Mock
    private UserSessionAuthRepository userSessionAuthRepository;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testValidate() throws Exception {
        Mockito.when(userSessionAuthExtractor.extract()).thenReturn(
                new UserSessionAuth()
                        .setExpirationTime(UserSessionAuth.ExpirationType.SHORT_TERM)
                        .setToken("the strongest token ever")
                        .renew()
                        .setUser(
                                new User(
                                        new Credentials()
                                                .setSalt("123")
                                                .setUser("justAnOrdinaryUser")
                                                .setPass("knoc knoc")
                                )
                        )
        );
        final Boolean isValid = new UserSessionAuthValidatorValidator(userSessionAuthExtractor, userSessionAuthRepository).validate();

        assertTrue("It's a valid user", isValid);
    }

    @Test
    public void testValidateInvalidSession() throws Exception {
        Mockito.when(userSessionAuthExtractor.extract()).thenReturn(
                new UserSessionAuth()
                        .setExpirationTime(UserSessionAuth.ExpirationType.SHORT_TERM)
                        .setToken("the strongest token ever")
                        .invalidate()
                        .setUser(
                                new User(
                                        new Credentials()
                                                .setSalt("123")
                                                .setUser("justAnOrdinaryUser")
                                                .setPass("knoc knoc")
                                )
                        )
        );
        final Boolean isValid = new UserSessionAuthValidatorValidator(userSessionAuthExtractor, userSessionAuthRepository).validate();

        assertFalse("It's not a valid user", isValid);
    }

    @Test
    public void testValidateNonexistantSession() throws Exception {
        Mockito.when(userSessionAuthExtractor.extract()).thenReturn(null);
        final Boolean isValid = new UserSessionAuthValidatorValidator(userSessionAuthExtractor, userSessionAuthRepository).validate();

        assertFalse("It's not a valid user too", isValid);
    }

    @Test
    public void testExtractUser() throws Exception {
        final User justAnOrdinaryUser = new User(
                new Credentials()
                        .setSalt("123")
                        .setUser("justAnOrdinaryUser")
                        .setPass("knoc knoc")
        );
        Mockito.when(userSessionAuthExtractor.extract()).thenReturn(
                new UserSessionAuth()
                        .setExpirationTime(UserSessionAuth.ExpirationType.SHORT_TERM)
                        .setToken("the strongest token ever")
                        .renew()
                        .setUser(justAnOrdinaryUser)
        );
        final User user = new UserSessionAuthValidatorValidator(userSessionAuthExtractor, userSessionAuthRepository).extractUser();

        assertNotNull("It's a valid user", user);
        assertEquals("It's a valid user", justAnOrdinaryUser, user);
    }

    @Test(expected = IllegalStateException.class)
    public void testExtractFromNonexistantSession() throws Exception {
        final User justAnOrdinaryUser = new User(
                new Credentials()
                        .setSalt("123")
                        .setUser("justAnOrdinaryUser")
                        .setPass("knoc knoc")
        );
        Mockito.when(userSessionAuthExtractor.extract()).thenReturn(null);
        new UserSessionAuthValidatorValidator(userSessionAuthExtractor, userSessionAuthRepository).extractUser();
    }

    @Test(expected = IllegalStateException.class)
    public void testExtractFromInvalidSession() throws Exception {
        final User justAnOrdinaryUser = new User(
                new Credentials()
                        .setSalt("123")
                        .setUser("justAnOrdinaryUser")
                        .setPass("knoc knoc")
        );
        Mockito.when(userSessionAuthExtractor.extract()).thenReturn(
                new UserSessionAuth()
                        .setExpirationTime(UserSessionAuth.ExpirationType.SHORT_TERM)
                        .setToken("the strongest token ever")
                        .invalidate()
                        .setUser(justAnOrdinaryUser)
        );
        Mockito.when(userSessionAuthExtractor.extract()).thenReturn(null);
        new UserSessionAuthValidatorValidator(userSessionAuthExtractor, userSessionAuthRepository).extractUser();
    }
}
