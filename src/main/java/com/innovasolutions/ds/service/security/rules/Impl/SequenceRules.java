package com.innovasolutions.ds.service.security.rules.Impl;

import com.innovasolutions.ds.service.security.rules.IValidationRules;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("sequenceRules")
public class SequenceRules implements IValidationRules {

    public static final String ERROR_PASSWORD_REPEATING_SEQUENCE = "Password must not contain any sequence of characters immediately followed by the same sequence.";

    private Pattern checkSequenceRepetition = Pattern.compile("(\\w{1,}|\\W{1,})\\1");

    /**
     * Check whether the parameter consist of repeating sequence.
     *
     * @param password password
     * @return validation result. if pass, returns PASS_VALIDATION. otherwise ERROR_PASSWORD_REPEATING_SEQUENCE.
     */
    @Override
    public String validate(String password) {
        Matcher matcher = checkSequenceRepetition.matcher(password);
        return (matcher.find()) ? ERROR_PASSWORD_REPEATING_SEQUENCE : PASS_VALIDATION;
    }
}
