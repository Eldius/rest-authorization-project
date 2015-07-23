package net.eldiosantos.authorization.vo;

import net.eldiosantos.brutauth.model.auth.profile.UserProfile;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Created by Eldius on 09/05/2015.
 */
public class UserVO implements Serializable {
    private Long id;

    private String user;

    private UserProfile role;

    public UserVO() {
    }

    public UserVO(Long id, String user, UserProfile role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public UserVO setId(Long id) {
        this.id = id;
        return this;
    }

    public UserProfile getRole() {
        return role;
    }

    public UserVO setRole(UserProfile role) {
        this.role = role;
        return this;
    }

    public String getUser() {
        return user;
    }

    public UserVO setUser(String user) {
        this.user = user;
        return this;
    }

}
