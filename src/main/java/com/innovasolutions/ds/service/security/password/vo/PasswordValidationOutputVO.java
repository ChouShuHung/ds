package com.innovasolutions.ds.service.security.password.vo;

import java.util.Set;

public class PasswordValidationOutputVO {
    private boolean isPasswordValid;
    private  Set<String> messages;

    public PasswordValidationOutputVO(boolean isPasswordValid, Set<String> messages) {
        this.isPasswordValid = isPasswordValid;
        this.messages = messages;
    }

    public boolean isPasswordValid() {
        return isPasswordValid;
    }

    public void setPasswordValid(boolean passwordValid) {
        this.isPasswordValid = passwordValid;
    }

    public Set<String> getMessages() {
        return messages;
    }

    public void setMessages(Set<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "PasswordValidationOutputVO{" +
                "isPasswordValid=" + isPasswordValid +
                ", messages=" + messages +
                '}';
    }
}
