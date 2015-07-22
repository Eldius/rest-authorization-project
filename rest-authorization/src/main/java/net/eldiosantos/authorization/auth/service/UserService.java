package net.eldiosantos.authorization.auth.service;

import net.eldiosantos.authorization.auth.builder.CredentialsBuilder;
import net.eldiosantos.brutauth.model.auth.Credentials;
import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.authorization.vo.CredentialsVO;
import net.eldiosantos.authorization.vo.UserVO;
import net.eldiosantos.brutauth.model.repository.UserRepository;
import net.eldiosantos.brutauth.model.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Eldius on 09/05/2015.
 */
public class UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserRoleRepository roleRepository;

    @Inject
    private CredentialsBuilder credentialsBuilder;

    @Inject
    private ModelMapper modelMapper;

    public UserVO create(final CredentialsVO cred) throws Exception {
        final Credentials credentials = credentialsBuilder.start()
                .user(cred.getUser())
                .generateSalt()
                .pass(cred.getPass());
        final User user = new User()
                .setCredentials(credentials)
                .setRole(roleRepository.getByPk(cred.getRoleId()));
        userRepository.saveOrUpdate(user);

        return modelMapper.map(user, UserVO.class);
    }

    public List<UserVO> list() {
        final Type targetListType = new TypeToken<List<UserVO>>() {
        }.getType();
        return modelMapper.map(userRepository.listAll(), targetListType);
    }

    public UserVO get(final Long id) {
        return modelMapper.map(userRepository.getByPk(id), UserVO.class);
    }

    public void delete(final Long id) {
        userRepository.delete(userRepository.getByPk(id));
    }

}
