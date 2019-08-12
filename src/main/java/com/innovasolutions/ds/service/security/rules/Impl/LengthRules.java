package com.innovasolutions.ds.service.security.rules.Impl;

import com.innovasolutions.ds.service.security.rules.IValidationRules;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.innovasolutions.ds.service.security.rules.IValidationRules.PASS_VALIDATION;


@Service("lengthRules")
public class LengthRules implements IValidationRules {

    public static final String ERROR_PASSWORD_LENGTH = "Password must be between 5 and 12 characters in length";

    /**
     * Check Length of parameter.
     *
     * @param password password
     * @return validation result. if pass, returns PASS_VALIDATION. otherwise ERROR_PASSWORD_LENGTH.
     */
    @Override
    public String validate(String password) {
        return (StringUtils.isEmpty(password) || password.length() < 5 || password.length() > 12) ? ERROR_PASSWORD_LENGTH : PASS_VALIDATION;
    }
}
