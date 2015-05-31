package net.eldiosantos.authorization.auth.builder;

import net.eldiosantos.authorization.model.auth.Credentials;

/**
 * Created by Eldius on 16/05/2015.
 */
public interface PassCredentialsBuilder {
    public Credentials pass(String pass) throws Exception;
}
