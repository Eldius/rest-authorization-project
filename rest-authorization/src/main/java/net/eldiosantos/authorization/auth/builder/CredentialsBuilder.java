package net.eldiosantos.authorization.auth.builder;

import net.eldiosantos.authorization.auth.builder.impl.CredentialsBuilderImpl;

import javax.inject.Inject;

/**
 * Created by Eldius on 16/05/2015.
 */
public class CredentialsBuilder {

    @Inject
    private CredentialsBuilderImpl builder;

    public UserCredentialsBuilder start() {
        return builder.start();
    }
}
