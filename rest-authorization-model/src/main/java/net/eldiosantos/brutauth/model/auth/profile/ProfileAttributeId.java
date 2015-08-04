package net.eldiosantos.brutauth.model.auth.profile;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by esjunior on 04/08/2015.
 */
@Embeddable
public class ProfileAttributeId implements Serializable {
    private String attributeName;

    @Column(name = "profile_id")
    private Long profileId;

    public ProfileAttributeId() {
    }

    public ProfileAttributeId(String attributeName, Long profileId) {
        this.attributeName = attributeName;
        this.profileId = profileId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
