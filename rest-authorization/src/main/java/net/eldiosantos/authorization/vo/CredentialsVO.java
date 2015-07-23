package net.eldiosantos.authorization.vo;

import java.io.Serializable;

/**
 * Created by Eldius on 09/05/2015.
 */
public class CredentialsVO implements Serializable {
    private String user;
    private String pass;

    public CredentialsVO() {
    }

    public CredentialsVO(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
