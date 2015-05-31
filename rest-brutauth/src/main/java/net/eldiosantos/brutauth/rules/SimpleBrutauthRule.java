package net.eldiosantos.brutauth.rules;

/**
 * Created by Eldius on 16/05/2015.
 */
public interface SimpleBrutauthRule {
    public Boolean canAccess(Long accessLevel);
}
