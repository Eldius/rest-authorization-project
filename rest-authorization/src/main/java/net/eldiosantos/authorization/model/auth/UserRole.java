package net.eldiosantos.authorization.model.auth;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * Created by eldio.junior on 08/05/2015.
 */
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String displayName;

    private Long access;

    public Long getId() {
        return id;
    }

    public UserRole setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserRole setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Long getAccess() {
        return access;
    }

    public UserRole setAccess(Long access) {
        this.access = access;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
