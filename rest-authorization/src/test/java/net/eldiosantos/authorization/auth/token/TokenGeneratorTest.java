package net.eldiosantos.authorization.auth.token;

import net.eldiosantos.authorization.auth.hash.HASHProvider;
import net.eldiosantos.brutauth.model.auth.Credentials;
import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by esjunior on 22/07/2015.
 */
@RunWith(CdiRunner.class)
public class TokenGeneratorTest {

    @Inject
    private HASHProvider provider;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGenerate() throws Exception {
        final TokenGenerator tokenGenerator = new TokenGenerator(provider);
        final Credentials credentials = new Credentials()
                .setPass("a-strong-pass")
                .setUser("any-user")
                .setSalt("just-hoping");
        final User user = new User(credentials);
        final UserSessionAuth sessionToken = tokenGenerator.generate(user);

        assertNotNull("Token generated?", sessionToken.getToken());
        assertTrue("Created a short term user session", sessionToken.getExpirationTime().equals(UserSessionAuth.ExpirationType.SHORT_TERM));
        assertNotEquals("Generated a diferent token", sessionToken.getToken(), tokenGenerator.generate(user).getToken());
    }

    @Test
    public void testGenerateLongterm() throws Exception {
        final TokenGenerator tokenGenerator = new TokenGenerator(provider);
        final Credentials credentials = new Credentials()
                .setPass("a-strong-pass")
                .setUser("any-user")
                .setSalt("just-hoping");
        final User user = new User(credentials);
        final UserSessionAuth sessionToken = tokenGenerator.generateLongterm(user);

        assertNotNull("Token generated?", sessionToken.getToken());
        assertTrue("Created a short term user session", sessionToken.getExpirationTime().equals(UserSessionAuth.ExpirationType.LONG_TERM));
        assertNotEquals("Generated a diferent token", sessionToken.getToken(), tokenGenerator.generateLongterm(user).getToken());
    }
}