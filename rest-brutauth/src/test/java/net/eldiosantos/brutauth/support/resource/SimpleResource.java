package net.eldiosantos.brutauth.support.resource;

import net.eldiosantos.brutauth.annotations.SimpleBrutauthRules;
import net.eldiosantos.brutauth.support.rule.TestSimpleBrutauthRule;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Eldius on 31/05/2015.
 */
@Path("simple")
@SimpleBrutauthRules({TestSimpleBrutauthRule.class})
public class SimpleResource {

    @GET
    public String method() {
        return "It's alive!!";
    }
}
