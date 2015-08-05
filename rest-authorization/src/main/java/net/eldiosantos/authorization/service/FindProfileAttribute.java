package net.eldiosantos.authorization.service;

import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.auth.profile.ProfileAttribute;
import net.eldiosantos.brutauth.model.auth.profile.UserProfile;
import net.eldiosantos.brutauth.model.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by esjunior on 04/08/2015.
 */
public class FindProfileAttribute {

    @Inject
    private UserRepository userRepository;

    public ProfileAttribute find(final User user, final String attributeName) {
        final UserProfile profile = userRepository.getByPk(user.getId()).getProfile();
        for(ProfileAttribute att: profile.getAttributes()) {
            if(att.getAttributeName().equals(attributeName)) {
                return att;
            }
        }

        final ProfileAttribute profileAttribute = new ProfileAttribute(attributeName, null);
        profile.addAttribute(profileAttribute);
        return profileAttribute;
    }
}
