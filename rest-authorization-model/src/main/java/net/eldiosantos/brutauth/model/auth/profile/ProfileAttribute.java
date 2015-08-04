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

    public void setAttributeName(String attributeName) {
        this.attributeId.setAttributeName(attributeName);
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
