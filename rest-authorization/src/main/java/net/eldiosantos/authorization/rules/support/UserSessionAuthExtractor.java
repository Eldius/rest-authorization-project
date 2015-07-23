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

    /**
     * Just for CDI
     */
    @Deprecated
    public UserSessionAuthExtractor() {
    }

    public UserSessionAuthExtractor(TokenHeaderExtractor tokenHeaderExtractor, UserSessionAuthRepository userSessionAuthRepository) {
        this.tokenHeaderExtractor = tokenHeaderExtractor;
        this.userSessionAuthRepository = userSessionAuthRepository;
    }

    public UserSessionAuth extract() {
        return userSessionAuthRepository.getByPk(tokenHeaderExtractor.extract());
    }
}
