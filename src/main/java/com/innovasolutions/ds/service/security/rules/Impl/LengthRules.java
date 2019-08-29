package com.innovasolutions.ds.service.security.rules.Impl;

import com.innovasolutions.ds.service.security.rules.IValidationRules;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.innovasolutions.ds.service.security.rules.ValidationMessages.*;


@Service("lengthRules")
public class LengthRules implements IValidationRules {

    /**
     * Check Length of parameter.
     *
     * @param password password
     * @return validation result. if pass, returns PASS_VALIDATION. otherwise ERROR_PASSWORD_LENGTH.
     */
    @Override
    public String validate(String password) {
        if (StringUtils.isEmpty(password)) {
            return ERROR_NULL_PASSWORD;
        } else {
            return (password.length() < 5 || password.length() > 12) ? ERROR_PASSWORD_LENGTH : PASS_VALIDATION;
        }
    }
}
