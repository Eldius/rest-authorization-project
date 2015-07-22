package net.eldiosantos.brutauth.model.auth;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.Period;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Eldius on 16/05/2015.
 */
@Entity
public class UserSessionAuth implements Serializable {

    public static enum ExpirationType {
        SHORT_TERM(Period.minutes(15))
        , LONG_TERM(Period.years(1));

        private final Period period;

        ExpirationType(Period period) {
            this.period = period;
        }

        public Period period() {
            return period;
        }
    }

    @Id
    private String token;

    @ManyToOne
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date validUntil;

    private ExpirationType expirationTime;

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

    public DateTime getValidUntil() {
        return new DateTime(validUntil);
    }

    public UserSessionAuth setValidUntil(DateTime validUntil) {
        this.validUntil = validUntil.toDate();
        return this;
    }

    public UserSessionAuth setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
        return this;
    }

    public ExpirationType getExpirationTime() {
        return expirationTime;
    }

    public UserSessionAuth setExpirationTime(ExpirationType expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    public UserSessionAuth renew() {
        this.setValidUntil(DateTime.now().plus(getExpirationTime().period()));
        return this;
    }

    public Boolean isValid() {
        return this.getValidUntil().isAfter(DateTime.now());
    }

    public UserSessionAuth invalidate() {
        this.setValidUntil(new DateTime(System.currentTimeMillis() - 1));
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
