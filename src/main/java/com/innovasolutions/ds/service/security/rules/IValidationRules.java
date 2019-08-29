package com.innovasolutions.ds.service.security.rules;

/**
 * Interface of password validation rules.
 */
public interface IValidationRules {
    /**
     * Validate password by rules.
     *
     * @param password password
     * @return validation result
     */
    String validate(String password);
}
