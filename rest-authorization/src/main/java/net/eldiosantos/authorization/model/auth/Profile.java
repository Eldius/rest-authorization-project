package net.eldiosantos.authorization.model.auth;

import javax.persistence.*;

/**
 * Created by Eldius on 23/05/2015.
 */
@MappedSuperclass
public abstract class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Profile setUser(User user) {
        this.user = user;
        return this;
    }
}
