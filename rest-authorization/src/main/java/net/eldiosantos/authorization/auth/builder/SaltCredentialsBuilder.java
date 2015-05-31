package net.eldiosantos.authorization.auth.builder;

/**
 * Created by Eldius on 16/05/2015.
 */
public interface SaltCredentialsBuilder {
    public PassCredentialsBuilder salt(String salt);

    public PassCredentialsBuilder generateSalt() throws Exception;
}
