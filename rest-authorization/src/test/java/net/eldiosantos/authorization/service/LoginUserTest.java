package net.eldiosantos.authorization.service;

import net.eldiosantos.authorization.auth.service.AuthService;
import net.eldiosantos.authorization.auth.service.MobileAuthService;
import net.eldiosantos.authorization.vo.CredentialsVO;
import net.eldiosantos.brutauth.model.auth.Credentials;
import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
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
public class LoginUserTest {

    @Mock
    private AuthService authService;

    @Mock
    private MobileAuthService mobileAuthService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testLongTermAuthenticate() throws Exception {
        final CredentialsVO credentials = new CredentialsVO();
        new LoginUser(authService,mobileAuthService).authenticate(credentials, UserSessionAuth.ExpirationType.LONG_TERM);
        Mockito.verify(mobileAuthService).login(credentials);
    }

    @Test
    public void testShortTermAuthenticate() throws Exception {
        final CredentialsVO credentials = new CredentialsVO();
        new LoginUser(authService,mobileAuthService).authenticate(credentials, UserSessionAuth.ExpirationType.SHORT_TERM);
        Mockito.verify(authService).login(credentials);
    }

    @Test
    public void testAuthenticate1() throws Exception {
        final CredentialsVO credentials = new CredentialsVO();
        new LoginUser(authService,mobileAuthService).authenticate(credentials);
        Mockito.verify(authService).login(credentials);
    }
}