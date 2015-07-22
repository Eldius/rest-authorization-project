package net.eldiosantos.authorization.rules.support;

import net.eldiosantos.brutauth.model.auth.User;
import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
import net.eldiosantos.brutauth.model.repository.UserSessionAuthRepository;

import javax.inject.Inject;
import java.util.Calendar;

/**
 * Created by SYSTEM on 27/05/2015.
 */
public class UserSessionAuthValidatorValidator {

    @Inject
    private UserSessionAuthExtractor userSessionAuthExtractor;

    private UserSessionAuthRepository userSessionAuthRepository;

    public Boolean validate() {
        final UserSessionAuth session = userSessionAuthExtractor.extract();
        final Boolean isValid = validateSession(session);

        if(isValid) {
            userSessionAuthRepository.saveOrUpdate(session);
        }

        return isValid;
    }

    private Boolean validateSession(UserSessionAuth session) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -15);

        if(session == null) {
            return false;
        } else if(session.isValid()) {
            session.renew();
            return true;
        }
        return null;
    }

    public User extractUser() {
        final UserSessionAuth session = userSessionAuthExtractor.extract();
        final Boolean isValid = validateSession(session);

        if(isValid) {
            return session.getUser();
        } else {
            throw  new IllegalStateException("Get out. You have 20 seconds to comply.");
        }
    }
}
