package net.eldiosantos.brutauth.util.resource;

import net.eldiosantos.brutauth.annotations.CustomBrutauthRules;
import net.eldiosantos.brutauth.util.model.Car;
import net.eldiosantos.brutauth.util.rule.MySecondTestCustomBrutauthRule;
import net.eldiosantos.brutauth.util.rule.MyTestCustomBrutauthRule;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Eldius on 01/08/2015.
 */
@Path("/test")
public class MyTestResource {

    @POST
    @CustomBrutauthRules(MyTestCustomBrutauthRule.class)
    public Response post(final Car car, final Long userId) {
        return Response.ok(car).build();
    }

    @PUT
    @CustomBrutauthRules({
            MyTestCustomBrutauthRule.class
            , MySecondTestCustomBrutauthRule.class
    })
    public Response put(final Car car, final Long userId) {
        return Response.ok(car).build();
    }
}
