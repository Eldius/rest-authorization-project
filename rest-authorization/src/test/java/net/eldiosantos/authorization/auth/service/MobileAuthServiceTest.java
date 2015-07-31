package net.eldiosantos.authorization.auth.service;

import net.eldiosantos.authorization.repository.hibernate.UserRepositoryHibernateImpl;
import net.eldiosantos.authorization.repository.hibernate.UserSessionAuthRepositoryHibernateImpl;
import net.eldiosantos.authorization.rules.support.TokenHeaderExtractor;
import net.eldiosantos.authorization.service.CreateUser;
import net.eldiosantos.authorization.util.EntityManagerFactoryProducer;
import net.eldiosantos.authorization.vo.CredentialsVO;
import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.*;

/**
 * Created by esjunior on 31/07/2015.
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({
        EntityManagerFactoryProducer.class
        , UserSessionAuthRepositoryHibernateImpl.class
        , UserRepositoryHibernateImpl.class
        , TokenHeaderExtractor.class
})
public class MobileAuthServiceTest {

    @Inject
    private MobileAuthService authService;

    @Inject
    private CreateUser createUser;

    @Inject
    private EntityManager entityManager;

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testLogin() throws Exception {
        final String user = "Mephistoteles1";
        final String pass = "Knock Knock";

        final EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        final User createdUser = createUser.create(new CredentialsVO(user, pass));
        tx.commit();

        final UserSessionAuth session = authService.login(new CredentialsVO(user, pass));

        assertEquals("We have a long term session token", UserSessionAuth.ExpirationType.LONG_TERM, session.getExpirationTime());
        assertTrue("This is a valid token", session.isValid());
    }

    @Test
    public void testLoginInvalidPass() throws Exception {
        final String user = "shepparrd1";
        final String pass = "ReapersSucks2100";

        final EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        final User createdUser = createUser.create(new CredentialsVO(user, pass));
        tx.commit();

        final UserSessionAuth session = authService.login(new CredentialsVO(user, pass.toUpperCase()));

        assertNull("We can't pass", session);
    }

    @Test
    public void testLoginIsCaseInsensitive() throws Exception {
        final String user = "romanoff1";
        final String pass = "MyStrongPassword";

        final EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        final User createdUser = createUser.create(new CredentialsVO(user, pass));
        tx.commit();

        final UserSessionAuth session = authService.login(new CredentialsVO(user.toUpperCase(), pass));

        assertNotNull("We're in", session);
        assertTrue("We're in[2]", session.isValid());
    }

    @Test
    public void testLoginNonExistantUser() throws Exception {
        final String user = "who1";
        final String pass = "Who is Who";

        final UserSessionAuth session = authService.login(new CredentialsVO(user.toUpperCase(), pass));

        assertNull("We're in", session);
    }

    // TODO Add test to logout method
    @Test
    public void testLogout() throws Exception {

    }
}
