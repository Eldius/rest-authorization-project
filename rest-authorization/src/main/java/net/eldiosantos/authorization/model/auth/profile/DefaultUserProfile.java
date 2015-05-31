package net.eldiosantos.authorization.model.auth.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Created by eldio.junior on 22/05/2015.
 */
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class DefaultUserProfile extends UserProfile {

    @Column(unique = true)
    private String email;

    private String name;

    public String getEmail() {
        return email;
    }

    public DefaultUserProfile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public DefaultUserProfile setName(String name) {
        this.name = name;
        return this;
    }
}
