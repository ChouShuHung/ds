package com.innovasolutions.ds.service.security.rules;

public class ValidationMessages {
    public static final String PASS_VALIDATION = "Passed";
    public static final String ERROR_NULL_PASSWORD = "Please type a valid password.";
    public static final String ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT = "Password must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.";
    public static final String ERROR_PASSWORD_LENGTH = "Password must be between 5 and 12 characters in length";
    public static final String ERROR_PASSWORD_REPEATING_SEQUENCE = "Password must not contain any sequence of characters immediately followed by the same sequence.";
}
