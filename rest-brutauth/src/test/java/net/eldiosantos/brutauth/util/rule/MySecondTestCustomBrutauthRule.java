package net.eldiosantos.brutauth.util.rule;

import net.eldiosantos.brutauth.annotations.BrutauthValidation;
import net.eldiosantos.brutauth.rules.CustomBrutauthRule;
import net.eldiosantos.brutauth.util.model.Car;

/**
 * Created by Eldius on 01/08/2015.
 */
public class MySecondTestCustomBrutauthRule implements CustomBrutauthRule {

    @BrutauthValidation
    public Boolean validate(final Car car) {
        return car.getId().equals(Long.valueOf(1L));
    }
}
