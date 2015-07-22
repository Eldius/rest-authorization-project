package net.eldiosantos.authorization.rules;

import net.eldiosantos.brutauth.model.auth.UserSessionAuth;
import net.eldiosantos.authorization.rules.support.TokenHeaderExtractor;
import net.eldiosantos.brutauth.model.repository.UserSessionAuthRepository;
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

    /**
     * Just for the CDI eyes.
     */
    @Deprecated
    public LoggedUserRule() {
    }

    public LoggedUserRule(UserSessionAuthRepository userSessionAuthRepository, TokenHeaderExtractor tokenHeaderExtractor) {
        this.userSessionAuthRepository = userSessionAuthRepository;
        this.tokenHeaderExtractor = tokenHeaderExtractor;
    }

    @Override
    public Boolean canAccess(Long accessLevel) {

        final UserSessionAuth session = userSessionAuthRepository.getByPk(tokenHeaderExtractor.extract());
        final Calendar calendar = Calendar.getInstance();
        if((session == null) || (session.getUser() == null)) {
            return Boolean.FALSE;
        } else if(session.isValid()) {
            session.renew();
            userSessionAuthRepository.saveOrUpdate(session);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
