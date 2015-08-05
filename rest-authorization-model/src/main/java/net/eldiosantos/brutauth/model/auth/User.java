package net.eldiosantos.brutauth.model.auth;

import net.eldiosantos.brutauth.model.auth.profile.UserProfile;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by eldio.junior on 08/05/2015.
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String user;

    @Lob
    private String pass;
    private String salt;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn()
    private UserProfile profile = new UserProfile();

    public User() {
    }

    public User(final Credentials credentials) {
        this.setCredentials(credentials);
    }

    public User(final Long id, final String user, final String pass, final String salt, final UserProfile profile) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.salt = salt;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUser() {
        return user;
    }

    public User setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public User setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public User setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public Credentials getCredentials() {
        return new Credentials()
                .setPass(getPass())
                .setUser(getUser())
                .setSalt(getSalt());
    }

    public User setCredentials(final Credentials credentials) {
        setPass(credentials.getPass())
                .setUser(credentials.getUser())
                .setSalt(credentials.getSalt());
        return this;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public User setProfile(UserProfile profile) {
        this.profile = profile;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
