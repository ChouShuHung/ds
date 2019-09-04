package com.innovasolutions.ds.service.security.rules;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.innovasolutions.ds.service.security.rules.ValidationMessages.PASS_VALIDATION;

public class CompositePasswordValidationRules {
    Set<IValidationRules> rules;

    Set<String> result;

    public CompositePasswordValidationRules() {
        rules = new HashSet<>();
        result = new HashSet<>();
    }

    public void addRules(IValidationRules rule) {
        rules.add(rule);
    }

    public void removeRules(IValidationRules rule) {
        rules.remove(rule);
    }

    public void validate(String password) {
        rules.forEach(rule -> result.add(rule.validate(password)));
    }

    public Set<String> getResult() {
        return result.stream().filter(msg -> !PASS_VALIDATION.equals(msg)).collect(Collectors.toSet());
    }
}

