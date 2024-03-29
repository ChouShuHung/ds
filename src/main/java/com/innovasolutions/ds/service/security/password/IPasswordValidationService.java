package com.innovasolutions.ds.service.security.password;

import com.innovasolutions.ds.service.security.password.vo.PasswordValidationOutputVO;

import java.util.Set;

/**
 * The services for Password validation.
 */
public interface IPasswordValidationService {

    /**
     * Password validation.
     * Assign the password which will be validated whether it follows the validation rules.
     *
     * @param password password
     * @return validation result. If password is valid, it returns "Passed". Conversely, returns the warning messages
     */
    PasswordValidationOutputVO validatePassword(String password);

}
