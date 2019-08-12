package com.innovasolutions.ds.service.security.rules.Impl;

import com.innovasolutions.ds.service.security.rules.IValidationRules;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

import static com.innovasolutions.ds.service.security.rules.IValidationRules.PASS_VALIDATION;

@Configuration
public class CharacterRules implements IValidationRules {

    public static final String ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT = "Password must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.";

    private Pattern nonWordPattern = Pattern.compile("\\W");
    private Pattern lowerCasePattern = Pattern.compile("[a-z]");
    private Pattern upperCasePattern = Pattern.compile("[A-Z]");
    private Pattern digitPattern = Pattern.compile("\\d");

    /**
     * Check the parameter consists of at least a letter and a digit.
     * <p>
     * Uppercase letter, whitespace, symbol are invalid.
     *
     * @param password password
     * @return validation result. if pass returns PASS_VALIDATION, otherwise ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT.
     */
    @Override
    public String validate(String password) {
        boolean minRequirement = checkLowerCaseLetter(password) && checkDigit(password);
        boolean violationRules = checkNonWord(password) || checkUpperCaseLetter(password);

        return (!minRequirement || violationRules) ? ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT : PASS_VALIDATION;
    }

    private boolean checkNonWord(String password) {
        return nonWordPattern.matcher(password).find();
    }

    private boolean checkLowerCaseLetter(String password) {
        return lowerCasePattern.matcher(password).find();
    }

    private boolean checkUpperCaseLetter(String password) {
        return upperCasePattern.matcher(password).find();
    }

    private boolean checkDigit(String password) {
        return digitPattern.matcher(password).find();
    }
}
