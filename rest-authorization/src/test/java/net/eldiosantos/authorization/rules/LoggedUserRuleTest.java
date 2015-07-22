package net.eldiosantos.authorization.rules;

import net.eldiosantos.authorization.rules.support.TokenHeaderExtractor;
import net.eldiosantos.brutauth.model.auth.Credentials;
import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
import net.eldiosantos.brutauth.model.repository.UserSessionAuthRepository;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

/**
 * Created by esjunior on 22/07/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoggedUserRuleTest {

    @Mock
    private UserSessionAuthRepository userSessionAuthRepository;

    @Mock
    private TokenHeaderExtractor tokenHeaderExtractor;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCanAccess() throws Exception {
        final String token = "a big fucking token string";
        Mockito.when(tokenHeaderExtractor.extract()).thenReturn(token);
        Mockito.when(userSessionAuthRepository.getByPk(token)).thenReturn(
                new UserSessionAuth()
                        .setExpirationTime(UserSessionAuth.ExpirationType.LONG_TERM)
                        .setToken(token)
                        .setUser(
                                new User(
                                        new Credentials()
                                                .setUser("myUser")
                                                .setPass("myPass")
                                                .setSalt("mySalt")
                                )
                        ).renew()
        );
        final LoggedUserRule loggedUserRule = new LoggedUserRule(userSessionAuthRepository, tokenHeaderExtractor);
        assertTrue("You can pass, of course", loggedUserRule.canAccess(0L));
    }

    @Test
    public void testCantAccess() throws Exception {
        final String token = "a big fucking token string";
        Mockito.when(tokenHeaderExtractor.extract()).thenReturn(token);
        Mockito.when(userSessionAuthRepository.getByPk(token)).thenReturn(
                new UserSessionAuth()
                        .setExpirationTime(UserSessionAuth.ExpirationType.LONG_TERM)
                        .setToken(token)
                        .setUser(
                                new User(
                                        new Credentials()
                                                .setUser("myUser")
                                                .setPass("myPass")
                                                .setSalt("mySalt")
                                )
                        ).invalidate()
        );
        final LoggedUserRule loggedUserRule = new LoggedUserRule(userSessionAuthRepository, tokenHeaderExtractor);
        assertFalse("You shall not pass!!", loggedUserRule.canAccess(0L));
    }

    @Test
    public void testCantAccessAgain() throws Exception {
        final String token = "a big fucking token string";
        Mockito.when(tokenHeaderExtractor.extract()).thenReturn(token);
        Mockito.when(userSessionAuthRepository.getByPk(token)).thenReturn(null);
        final LoggedUserRule loggedUserRule = new LoggedUserRule(userSessionAuthRepository, tokenHeaderExtractor);
        assertFalse("You shall not pass!!+1", loggedUserRule.canAccess(0L));
    }

    @Test
    public void testCantAccessOneMoretime() throws Exception {
        final String token = "a big fucking token string";
        Mockito.when(tokenHeaderExtractor.extract()).thenReturn(token);
        Mockito.when(userSessionAuthRepository.getByPk(token)).thenReturn(
                new UserSessionAuth()
                        .setExpirationTime(UserSessionAuth.ExpirationType.LONG_TERM)
                        .setToken(token)
                        .setUser(null).invalidate()
        );
        final LoggedUserRule loggedUserRule = new LoggedUserRule(userSessionAuthRepository, tokenHeaderExtractor);
        assertFalse("You shall not pass!!+1", loggedUserRule.canAccess(0L));
    }

    @Test
    public void testCantAccessLastChance() throws Exception {
        final String token = "a big fucking token string";
        Mockito.when(tokenHeaderExtractor.extract()).thenReturn(token);
        Mockito.when(userSessionAuthRepository.getByPk(token)).thenReturn(
                new UserSessionAuth()
                        .setExpirationTime(UserSessionAuth.ExpirationType.LONG_TERM)
                        .setToken(token)
                        .setUser(null)
                        .renew()
        );
        final LoggedUserRule loggedUserRule = new LoggedUserRule(userSessionAuthRepository, tokenHeaderExtractor);
        assertFalse("You shall not pass!!+1", loggedUserRule.canAccess(0L));
    }
}