package com.innovasolutions.ds.service.security.password.Impl;

import com.innovasolutions.ds.service.security.password.IPasswordValidationService;
import com.innovasolutions.ds.service.security.password.vo.PasswordValidationOutputVO;
import com.innovasolutions.ds.service.security.rules.CompositePasswordValidationRules;
import com.innovasolutions.ds.service.security.rules.Impl.CharacterRules;
import com.innovasolutions.ds.service.security.rules.Impl.LengthRules;
import com.innovasolutions.ds.service.security.rules.Impl.SequenceRules;
import org.springframework.stereotype.Service;

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
    public PasswordValidationOutputVO validatePassword(String password) {
        CompositePasswordValidationRules rules = new CompositePasswordValidationRules();
        rules.addRules(new LengthRules());
        rules.addRules(new CharacterRules());
        rules.addRules(new SequenceRules());
        rules.validate(password);
        return new PasswordValidationOutputVO(rules.getResult());
    }
}

