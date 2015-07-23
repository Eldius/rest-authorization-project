package net.eldiosantos.authorization.service;

import net.eldiosantos.authorization.auth.service.AuthService;
import net.eldiosantos.authorization.auth.service.MobileAuthService;
import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
import net.eldiosantos.authorization.vo.CredentialsVO;

import javax.inject.Inject;

/**
 * Created by Eldius on 11/07/2015.
 */
public class LoginUser {

    @Inject
    private AuthService authService;

    @Inject
    private MobileAuthService mobileAuthService;

    /**
     * Just to compatibility with CDI.
     */
    @Deprecated
    public LoginUser() {
    }

    public LoginUser(AuthService authService, MobileAuthService mobileAuthService) {
        this.authService = authService;
        this.mobileAuthService = mobileAuthService;
    }

    public UserSessionAuth authenticate(final CredentialsVO credentials, final UserSessionAuth.ExpirationType expirationType) throws Exception {
        return UserSessionAuth.ExpirationType.SHORT_TERM.equals(expirationType)?
                authService.login(credentials):
                mobileAuthService.login(credentials);
    }

    public UserSessionAuth authenticate(final CredentialsVO credentials) throws Exception {
        return authService.login(credentials);
    }
}
