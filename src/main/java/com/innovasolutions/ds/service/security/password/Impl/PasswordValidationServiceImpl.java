package com.innovasolutions.ds.service.security.password.Impl;

import com.innovasolutions.ds.service.security.password.IPasswordValidationService;
import com.innovasolutions.ds.service.security.rules.IValidationRules;
import com.innovasolutions.ds.service.security.rules.Impl.CharacterRules;
import com.innovasolutions.ds.service.security.rules.Impl.LengthRules;
import com.innovasolutions.ds.service.security.rules.Impl.SequenceRules;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.innovasolutions.ds.service.security.rules.IValidationRules.PASS_VALIDATION;

@Service("passwordValidationServiceImpl")
public class PasswordValidationServiceImpl implements IPasswordValidationService {

    /**
     * Provides the implementation for IValidationRules and Password Validation Rules are made by Innova Solution.
     * <p>
     * ⦁Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
     * ⦁Must be between 5 and 12 characters in length.
     * ⦁Must not contain any sequence of characters immediately followed by the same sequence.
     *
     * @param password password
     * @return validation result. If password is valid, it returns "Passed". Conversely, returns the warning messages
     */
    @Override
    public Set<String> validatePassword(String password) {
        Set<String> result = new HashSet<>();
        Set<IValidationRules> rules = new HashSet<>();
        rules.add(new LengthRules());
        rules.add(new CharacterRules());
        rules.add(new SequenceRules());
        rules.forEach(strategy -> result.add(strategy.validate(password)));
        Set<String> failReason = result.stream().filter(msg -> !PASS_VALIDATION.equals(msg)).collect(Collectors.toSet());
        return failReason.isEmpty() ? result : failReason;
    }
}

