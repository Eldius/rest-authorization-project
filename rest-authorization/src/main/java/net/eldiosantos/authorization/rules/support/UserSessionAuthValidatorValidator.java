package net.eldiosantos.authorization.rules.support;

import net.eldiosantos.authorization.model.auth.User;
import net.eldiosantos.authorization.model.auth.UserSessionAuth;
import net.eldiosantos.authorization.repository.UserSessionAuthRepository;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;

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
        } else if(session.getLastAccess().after(calendar.getTime())) {
            session.setLastAccess(new Date());
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
