package net.eldiosantos.authorization.auth.builder.impl;

import net.eldiosantos.authorization.auth.builder.PassCredentialsBuilder;
import net.eldiosantos.authorization.auth.builder.SaltCredentialsBuilder;
import net.eldiosantos.authorization.auth.builder.UserCredentialsBuilder;
import net.eldiosantos.authorization.auth.hash.HASHProvider;
import net.eldiosantos.brutauth.model.auth.Credentials;
import org.apache.commons.codec.binary.Hex;

import javax.inject.Inject;
import java.security.SecureRandom;

/**
 * Created by Eldius on 16/05/2015.
 */
public class CredentialsBuilderImpl implements PassCredentialsBuilder, SaltCredentialsBuilder, UserCredentialsBuilder {

    @Inject
    private HASHProvider hashProvider;

    private Credentials credentials;

    public CredentialsBuilderImpl start() {
        this.credentials = new Credentials();
        return this;
    }

    @Override
    public Credentials pass(String pass) throws Exception {
        return credentials.setPass(hashProvider.stringHash(pass, credentials.getSalt()));
    }

    @Override
    public PassCredentialsBuilder salt(final String salt) {
        credentials.setSalt(salt);
        return this;
    }

    @Override
    public PassCredentialsBuilder generateSalt() throws Exception {
        byte[] binarySalt = new byte[16];
        SecureRandom.getInstance("SHA1PRNG").nextBytes(binarySalt);

        final String salt = Hex.encodeHexString(binarySalt);
        credentials.setSalt(salt);
        return this;
    }

    @Override
    public SaltCredentialsBuilder user(final String user) {
        credentials.setUser(user);
        return this;
    }
}
