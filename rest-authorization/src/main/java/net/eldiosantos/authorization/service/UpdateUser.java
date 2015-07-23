package net.eldiosantos.authorization.service;

import net.eldiosantos.authorization.auth.builder.CredentialsBuilder;
import net.eldiosantos.authorization.vo.CredentialsVO;
import net.eldiosantos.brutauth.model.auth.Credentials;
import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by esjunior on 23/07/2015.
 */
public class UpdateUser {

    @Inject
    private UserRepository userRepository;

    @Inject
    private CredentialsBuilder credentialsBuilder;

    /**
     * Just for CDI
     */
    @Deprecated
    public UpdateUser() {
    }

    public UpdateUser(UserRepository userRepository, CredentialsBuilder credentialsBuilder) {
        this.userRepository = userRepository;
        this.credentialsBuilder = credentialsBuilder;
    }

    public User update(final CredentialsVO vo) throws Exception {
        final User user = userRepository.findByLogin(vo.getUser());
        final Credentials credentials = credentialsBuilder.start()
                .user(user.getUser())
                .generateSalt()
                .pass(vo.getPass());
        user.setCredentials(credentials);

        userRepository.update(user);

        return user;
    }
}
