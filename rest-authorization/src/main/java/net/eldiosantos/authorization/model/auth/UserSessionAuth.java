package net.eldiosantos.authorization.model.auth;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Eldius on 16/05/2015.
 */
@Entity
public class UserSessionAuth implements Serializable {

    @Id
    private String token;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccess;

    public String getToken() {
        return token;
    }

    public UserSessionAuth setToken(String token) {
        this.token = token;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserSessionAuth setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public UserSessionAuth setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
        return this;
    }
}
