package net.eldiosantos.authorization.model.auth;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Eldius on 16/05/2015.
 */
public class Credentials {
    private String user;
    private String pass;
    private String salt;

    public String getUser() {
        return user != null ? user : "";
    }

    public Credentials setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPass() {
        return pass != null ? pass : "";
    }

    public Credentials setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getSalt() {
        return salt != null ? salt : "";
    }

    public Credentials setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Credentials) {
            final Credentials cred = (Credentials) obj;

            return cred.getPass().equals(getPass()) &&
                    cred.getSalt().equals(getSalt()) &&
                    cred.getUser().equalsIgnoreCase(getUser());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
