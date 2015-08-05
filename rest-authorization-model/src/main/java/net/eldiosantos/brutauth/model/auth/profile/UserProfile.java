package net.eldiosantos.brutauth.model.auth.profile;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by eldio.junior on 22/05/2015.
 */
@Entity
public class UserProfile {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "userProfile")
    private List<ProfileAttribute>attributes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public UserProfile setId(Long id) {
        this.id = id;
        return this;
    }

    public UserProfile isEmpty() {
        attributes.isEmpty();
        return this;
    }

    public int size() {
        return attributes.size();
    }

    public UserProfile addAttribute(ProfileAttribute profileAttribute) {
        attributes.add(profileAttribute);
        return this;
    }

    public UserProfile addAttribute(String attributeName, String attributeValue) {
        attributes.add(new ProfileAttribute(attributeName, attributeValue));
        return this;
    }

    public UserProfile addAll(Collection<? extends ProfileAttribute> c) {
        attributes.addAll(c);
        return this;
    }

    public UserProfile removeAll(Collection<?> c) {
        attributes.removeAll(c);
        return this;
    }

    public UserProfile remove(Object o) {
        attributes.remove(o);
        return this;
    }

    public List<ProfileAttribute> getAttributes() {
        return attributes;
    }

    public UserProfile setAttributes(List<ProfileAttribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
