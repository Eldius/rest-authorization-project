package net.eldiosantos.authorization.vo;

import net.eldiosantos.authorization.model.auth.UserRole;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * Created by Eldius on 09/05/2015.
 */
public class UserVO implements Serializable {
    private Long id;
    private String profileName;
    private String profileEmail;

    private String user;

    private UserRole role;

    public Long getId() {
        return id;
    }

    public UserVO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProfileName() {
        return profileName;
    }

    public UserVO setprofileName(String profileName) {
        this.profileName = profileName;
        return this;
    }

    public String getProfileEmail() {
        return profileEmail;
    }

    public UserVO setProfileEmail(String profileEmail) {
        this.profileEmail = profileEmail;
        return this;
    }

    public String getUser() {
        return user;
    }

    public UserVO setUser(String user) {
        this.user = user;
        return this;
    }

    public String getRoleDisplayName() {
        return getRole().getDisplayName();
    }

    public Long getRoleAccess() {
        return getRole().getAccess();
    }

    public UserVO setRoleAccess(Long access) {
        getRole().setAccess(access);
        return this;
    }

    public UserVO setRoleDisplayName(String displayName) {
        getRole().setDisplayName(displayName);
        return this;
    }

    public Long getRoleId() {
        return getRole().getId();
    }

    @XmlTransient
    public UserRole getRole() {
        return role!=null?role:new UserRole();
    }

    public UserVO setRole(UserRole role) {
        this.role = role;
        return this;
    }
}
