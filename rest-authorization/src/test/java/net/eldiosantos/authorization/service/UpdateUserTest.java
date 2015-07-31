package net.eldiosantos.authorization.service;

import net.eldiosantos.authorization.auth.builder.CredentialsBuilder;
import net.eldiosantos.authorization.vo.CredentialsVO;
import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.repository.UserRepository;
import org.jglue.cdiunit.CdiRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by esjunior on 31/07/2015.
 */
@RunWith(CdiRunner.class)
public class UpdateUserTest {

    private UserRepository userRepository;

    @Inject
    private CredentialsBuilder credentialsBuilder;

    @Before
    public void setUp() throws Exception {
        userRepository = Mockito.mock(UserRepository.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

        final String newPassword = "abre-te sesamo";
        final String username = "Vaarsuvius";
        final CredentialsVO vaarsuvius = new CredentialsVO(username, newPassword);

        final String oldPasswordHash = "oldWeakPasswordHashed";

        Mockito.when(userRepository.findByLogin(vaarsuvius.getUser())).thenReturn(
                new User()
                        .setPass(oldPasswordHash)
                        .setUser(username)
        );

        final User updatedUser = new UpdateUser(userRepository, credentialsBuilder).update(vaarsuvius);

        assertNotEquals("Password was hashed", newPassword, updatedUser.getPass());
        assertNotEquals("Password was hashed", oldPasswordHash, updatedUser.getPass());
        assertNotNull("Password was defined", updatedUser.getPass());
        assertNotNull("Salt attribute was defined", updatedUser.getSalt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithoutPassord() throws Exception {

        final String newPassword = "";
        final String username = "Vaarsuvius";
        final CredentialsVO vaarsuvius = new CredentialsVO(username, newPassword);

        final String oldPasswordHash = "oldWeakPasswordHashed";

        Mockito.when(userRepository.findByLogin(vaarsuvius.getUser())).thenReturn(
                new User()
                        .setPass(oldPasswordHash)
                        .setUser(username)
        );

        final User updatedUser = new UpdateUser(userRepository, credentialsBuilder).update(vaarsuvius);

        assertNotEquals("Password was hashed", newPassword, updatedUser.getPass());
        assertNotEquals("Password was hashed", oldPasswordHash, updatedUser.getPass());
        assertNotNull("Password was defined", updatedUser.getPass());
        assertNotNull("Salt attribute was defined", updatedUser.getSalt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithNullPassword() throws Exception {

        final String newPassword = null;
        final String username = "Vaarsuvius";
        final CredentialsVO vaarsuvius = new CredentialsVO(username, newPassword);

        final String oldPasswordHash = "oldWeakPasswordHashed";

        Mockito.when(userRepository.findByLogin(vaarsuvius.getUser())).thenReturn(
                new User()
                        .setPass(oldPasswordHash)
                        .setUser(username)
        );

        final User updatedUser = new UpdateUser(userRepository, credentialsBuilder).update(vaarsuvius);

        assertNotEquals("Password was hashed", newPassword, updatedUser.getPass());
        assertNotEquals("Password was hashed", oldPasswordHash, updatedUser.getPass());
        assertNotNull("Password was defined", updatedUser.getPass());
        assertNotNull("Salt attribute was defined", updatedUser.getSalt());
    }
}