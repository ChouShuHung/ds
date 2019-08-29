package com.innovasolutions.ds.service.security.rules.Impl;

import com.innovasolutions.ds.service.security.rules.IValidationRules;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.innovasolutions.ds.service.security.rules.ValidationMessages.*;

@Service("sequenceRules")
public class SequenceRules implements IValidationRules {

    private Pattern checkSequenceRepetition = Pattern.compile("(\\w{1,}|\\W{1,})\\1");

    /**
     * Check whether the parameter consist of repeating sequence.
     *
     * @param password password
     * @return validation result. if pass, returns PASS_VALIDATION. otherwise ERROR_PASSWORD_REPEATING_SEQUENCE.
     */
    @Override
    public String validate(String password) {
        if (StringUtils.isEmpty(password)) {
            return ERROR_NULL_PASSWORD;
        } else {
            Matcher matcher = checkSequenceRepetition.matcher(password);
            return (matcher.find()) ? ERROR_PASSWORD_REPEATING_SEQUENCE : PASS_VALIDATION;
        }
    }
}
