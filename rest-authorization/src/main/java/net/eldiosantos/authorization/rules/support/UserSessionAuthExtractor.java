package net.eldiosantos.authorization.rules.support;

import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
import net.eldiosantos.brutauth.model.repository.UserSessionAuthRepository;

import javax.inject.Inject;

/**
 * Created by SYSTEM on 27/05/2015.
 */
public class UserSessionAuthExtractor {

    @Inject
    private TokenHeaderExtractor tokenHeaderExtractor;

    @Inject
    private UserSessionAuthRepository userSessionAuthRepository;

    public UserSessionAuth extract() {
        return userSessionAuthRepository.getByPk(tokenHeaderExtractor.extract());
    }
}
