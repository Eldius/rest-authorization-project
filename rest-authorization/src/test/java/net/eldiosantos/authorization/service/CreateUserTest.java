package net.eldiosantos.authorization.service;

import net.eldiosantos.authorization.repository.hibernate.UserRepositoryHibernateImpl;
import net.eldiosantos.authorization.util.EntityManagerFactoryProducer;
import net.eldiosantos.authorization.vo.CredentialsVO;
import net.eldiosantos.brutauth.model.auth.User;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.*;

/**
 * Created by esjunior on 23/07/2015.
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({
        EntityManagerFactoryProducer.class
        , UserRepositoryHibernateImpl.class
})
public class CreateUserTest {

    @Inject
    private CreateUser createUser;

    @Inject
    private EntityManager entityManager;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreate() throws Exception {
        final EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        final User frank = createUser.create(new CredentialsVO("createdUser", "strongP@ass001"));
        tx.commit();

        final User persistedUser = entityManager.find(User.class, frank.getId());

        assertEquals("The right user was loaded from database", frank, persistedUser);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithNullUsername() throws Exception {
        final User frank = createUser.create(new CredentialsVO(null, "strongP@ass001"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithNullPassword() throws Exception {
        final User frank = createUser.create(new CredentialsVO("invalidUser", null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithEmptyUsername() throws Exception {
        final User frank = createUser.create(new CredentialsVO("", "strongP@ass001"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithEmptyPassword() throws Exception {
        final User frank = createUser.create(new CredentialsVO("invalidUser", ""));
    }
}
