package net.eldiosantos.authorization.service;

import net.eldiosantos.authorization.auth.builder.CredentialsBuilder;
import net.eldiosantos.authorization.model.auth.Credentials;
import net.eldiosantos.authorization.model.auth.User;
import net.eldiosantos.authorization.repository.UserRepository;
import net.eldiosantos.authorization.vo.CredentialsVO;

import javax.inject.Inject;

/**
 * Created by Eldius on 11/07/2015.
 */
public class CreateUser {

    @Inject
    private CredentialsBuilder credentialsBuilder;

    @Inject
    private UserRepository userRepository;

    public User create(final CredentialsVO vo) throws Exception {
        final Credentials credentials = credentialsBuilder.start()
                .user(vo.getUser())
                .generateSalt()
                .pass(vo.getPass());

        final User user = new User().setCredentials(credentials);
        userRepository.persist(user);

        return user;
    }
}
