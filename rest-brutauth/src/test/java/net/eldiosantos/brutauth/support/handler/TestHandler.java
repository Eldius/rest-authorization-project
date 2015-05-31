package net.eldiosantos.brutauth.support.handler;

import net.eldiosantos.brutauth.handler.RuleHandler;

import java.io.IOException;

/**
 * Created by Eldius on 31/05/2015.
 */
public class TestHandler implements RuleHandler {

    private Boolean passedHere = Boolean.FALSE;

    @Override
    public void handle() throws IOException {
        passedHere = Boolean.TRUE;
    }

    public Boolean getPassedHere() {
        return passedHere;
    }
}
