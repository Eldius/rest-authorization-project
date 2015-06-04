package net.eldiosantos.authorization.vo;

import java.io.Serializable;

/**
 * Created by Eldius on 09/05/2015.
 */
public class CredentialsVO implements Serializable {
    private String user;
    private String pass;

    private Long roleId;

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
