package net.eldiosantos.authorization.auth.service;

import net.eldiosantos.authorization.auth.builder.CredentialsBuilder;
import net.eldiosantos.authorization.auth.token.TokenGenerator;
import net.eldiosantos.brutauth.model.auth.Credentials;
import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
import net.eldiosantos.authorization.rules.support.TokenHeaderExtractor;
import net.eldiosantos.authorization.vo.CredentialsVO;
import net.eldiosantos.brutauth.model.repository.UserRepository;
import net.eldiosantos.brutauth.model.repository.UserSessionAuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by Eldius on 16/05/2015.
 */
public class AuthService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private TokenGenerator tokenGenerator;

    @Inject
    private UserSessionAuthRepository sessionAuthRepository;

    @Inject
    private CredentialsBuilder builder;

    @Inject
    private UserRepository userRepository;

    @Inject
    private TokenHeaderExtractor tokenHeaderExtractor;

    public UserSessionAuth login(final CredentialsVO loginData) throws Exception {
        try {
            final User user = userRepository.findByLogin(loginData.getUser());
            final Credentials credentials = builder.start()
                    .user(loginData.getUser())
                    .salt(user.getSalt())
                    .pass(loginData.getPass());

            if (user.getCredentials().equals(credentials)) {
                final UserSessionAuth auth = tokenGenerator.generate(user);
                sessionAuthRepository.saveOrUpdate(auth);
                return auth;
            }
        }catch (Exception e) {
            logger.warn("Error while trying to authenticate user", e);
        }
        return null;
    }

    public void logout() {
        sessionAuthRepository.update(sessionAuthRepository.getByPk(tokenHeaderExtractor.extract()).invalidate());
    }
}
