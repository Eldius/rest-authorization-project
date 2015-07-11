package net.eldiosantos.authorization.auth.service;

import net.eldiosantos.authorization.auth.builder.CredentialsBuilder;
import net.eldiosantos.authorization.auth.token.TokenGenerator;
import net.eldiosantos.authorization.model.auth.Credentials;
import net.eldiosantos.authorization.model.auth.User;
import net.eldiosantos.authorization.model.auth.UserSessionAuth;
import net.eldiosantos.authorization.repository.UserRepository;
import net.eldiosantos.authorization.repository.UserSessionAuthRepository;
import net.eldiosantos.authorization.rules.support.TokenHeaderExtractor;
import net.eldiosantos.authorization.vo.CredentialsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by Eldius on 16/05/2015.
 */
public class MobileAuthService {

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
                final UserSessionAuth auth = tokenGenerator.generateLongterm(user);
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
