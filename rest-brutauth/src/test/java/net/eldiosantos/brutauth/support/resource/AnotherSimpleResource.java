package net.eldiosantos.brutauth.support.resource;

import net.eldiosantos.brutauth.annotations.SimpleBrutauthRules;
import net.eldiosantos.brutauth.support.rule.OneMoreSimpleBrutauthRule;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by Eldius on 31/05/2015.
 */
@Path("simple")
@SimpleBrutauthRules({OneMoreSimpleBrutauthRule.class})
public class AnotherSimpleResource {

    @GET
    public String method() {
        return "It's alive!!";
    }
}
