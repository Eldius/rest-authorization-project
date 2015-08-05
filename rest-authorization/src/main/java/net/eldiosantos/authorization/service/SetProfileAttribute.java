package net.eldiosantos.authorization.service;

import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.repository.ProfileAttributeRepository;
import net.eldiosantos.brutauth.model.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by esjunior on 04/08/2015.
 */
public class SetProfileAttribute {

    @Inject
    private FindProfileAttribute findProfileAttribute;

    @Inject
    private ProfileAttributeRepository profileAttributeRepository;

    @Inject
    private UserRepository userRepository;

    public User addProfileInfo(final User user, final String attributeName, final String attributeValue) {
        profileAttributeRepository.saveOrUpdate(findProfileAttribute.find(user, attributeName).setAttributeValue(attributeValue));

        return userRepository.getByPk(user.getId());
    }
}
