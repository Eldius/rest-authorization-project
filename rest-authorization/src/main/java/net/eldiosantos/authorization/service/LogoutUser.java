package net.eldiosantos.authorization.service;

import net.eldiosantos.authorization.auth.service.AuthService;

import javax.inject.Inject;

/**
 * Created by Eldius on 11/07/2015.
 */
public class LogoutUser {

    @Inject
    private AuthService authService;

    /**
     * Just for CDI do not complain.
     */
    @Deprecated
    public LogoutUser() {
    }

    public LogoutUser(AuthService authService) {
        this.authService = authService;
    }

    public void logout() {
        authService.logout();
    }
}
