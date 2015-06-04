package net.eldiosantos.authorization.auth.token;

import net.eldiosantos.authorization.auth.hash.HASHProvider;
import net.eldiosantos.authorization.model.auth.User;
import net.eldiosantos.authorization.model.auth.UserSessionAuth;

import javax.inject.Inject;

/**
 * Created by Eldius on 16/05/2015.
 */
public class TokenGenerator {

    @Inject
    private HASHProvider hashProvider;

    public UserSessionAuth generate(final User user) throws Exception {
        return createUserSessionAuth(user, UserSessionAuth.ExpirationType.SHORT_TERM);
    }

    public UserSessionAuth generateLongterm(final User user) throws Exception {
        return createUserSessionAuth(user, UserSessionAuth.ExpirationType.LONG_TERM);
    }

    private UserSessionAuth createUserSessionAuth(User user, UserSessionAuth.ExpirationType expirationType) throws Exception {
        final String token = hashProvider.stringHash(user.getCredentials().toString() + System.currentTimeMillis());

        return new UserSessionAuth().setToken(token)
                .setUser(user)
                .setExpirationTime(expirationType)
                .renew();
    }
}
