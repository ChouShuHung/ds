package com.innovasolutions.ds.config.service.security.rules;

import com.innovasolutions.ds.service.security.rules.IValidationRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.innovasolutions.ds.service.security.rules.IValidationRules.PASS_VALIDATION;
import static com.innovasolutions.ds.service.security.rules.Impl.SequenceRules.ERROR_PASSWORD_REPEATING_SEQUENCE;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SequenceRulesTests {


    @Autowired
    @Qualifier("sequenceRules")
    private IValidationRules sequenceRules;

    @Test
    public void testNonRepeatingSequence() {
        // The validation should pass.
        assertEquals(PASS_VALIDATION, sequenceRules.validate("pas$w0rd"));
    }

    @Test
    public void testSequenceRepeatLetters() {
        // The validation should fail. "word" is repeated.
        assertEquals(ERROR_PASSWORD_REPEATING_SEQUENCE, sequenceRules.validate("wordword"));
    }

    @Test
    public void testRepeatSingleLetter() {
        // The validation should fail. "pp" is repeated.
        assertEquals(ERROR_PASSWORD_REPEATING_SEQUENCE, sequenceRules.validate("pp"));
    }

    @Test
    public void testRepeatLettersAndDigits() {
        // The validation should fail. "w0" is repeated
        assertEquals(ERROR_PASSWORD_REPEATING_SEQUENCE, sequenceRules.validate("w0w0"));
    }

    @Test
    public void testSeparateDuplicatedLetters() {
        // The validation should pass.
        assertEquals(PASS_VALIDATION, sequenceRules.validate("abc123abc"));
    }

    @Test
    public void testSeparateDuplicatedDigits() {
        // The validation should pass.
        assertEquals(PASS_VALIDATION, sequenceRules.validate("123abc123"));
    }
}
