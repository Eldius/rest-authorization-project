package net.eldiosantos.brutauth.model.auth.profile;

import javax.persistence.*;

/**
 * Created by esjunior on 04/08/2015.
 */
@Entity
public class ProfileAttribute {

    @Id
    private ProfileAttributeId attributeId = new ProfileAttributeId();

    private String attributeValue;

    @ManyToOne
    @MapsId("profileId")
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;

    public ProfileAttribute() {
    }

    public ProfileAttribute(String attributeName, String attributeValue) {
        this.attributeId.setAttributeName(attributeName);
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeId.getAttributeName();
    }

    public ProfileAttribute setAttributeName(String attributeName) {
        this.attributeId.setAttributeName(attributeName);
        return this;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public ProfileAttribute setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
        return this;
    }
}
