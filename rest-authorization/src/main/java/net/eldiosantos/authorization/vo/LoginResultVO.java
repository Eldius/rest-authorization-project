package net.eldiosantos.authorization.vo;

import java.io.Serializable;

/**
 * Created by Eldius on 16/05/2015.
 */
public class LoginResultVO implements Serializable {
    private Boolean success;
    private String token;
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public LoginResultVO setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginResultVO setToken(String token) {
        this.token = token;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public LoginResultVO setMessage(String message) {
        this.message = message;
        return this;
    }
}
