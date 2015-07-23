package net.eldiosantos.authorization.service;

import net.eldiosantos.authorization.auth.service.AuthService;
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
public class LogoutUserTest {

    @Mock
    private AuthService service;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testLogout() throws Exception {
        new LogoutUser(service).logout();
        Mockito.verify(service).logout();
    }
}