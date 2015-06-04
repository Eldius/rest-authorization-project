package net.eldiosantos.authorization.rules;

import net.eldiosantos.authorization.model.auth.UserSessionAuth;
import net.eldiosantos.authorization.repository.UserSessionAuthRepository;
import net.eldiosantos.authorization.rules.support.TokenHeaderExtractor;
import net.eldiosantos.brutauth.rules.SimpleBrutauthRule;

import javax.inject.Inject;
import java.util.Calendar;

/**
 * Created by Eldius on 16/05/2015.
 */
public class LoggedUserRule implements SimpleBrutauthRule {

    @Inject
    private UserSessionAuthRepository userSessionAuthRepository;

    @Inject
    private TokenHeaderExtractor tokenHeaderExtractor;

    @Override
    public Boolean canAccess(Long accessLevel) {

        final UserSessionAuth session = userSessionAuthRepository.getByPk(tokenHeaderExtractor.extract());
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -15);
        if((session == null) || (session.getUser() == null)) {
            return false;
        } else if(session.isValid()) {
            session.renew();
            userSessionAuthRepository.saveOrUpdate(session);
            return true;
        }
        return false;
    }
}
