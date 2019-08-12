package com.innovasolutions.ds.config.service.security.rules;

import com.innovasolutions.ds.service.security.rules.IValidationRules;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.innovasolutions.ds.service.security.rules.IValidationRules.PASS_VALIDATION;
import static com.innovasolutions.ds.service.security.rules.Impl.LengthRules.ERROR_PASSWORD_LENGTH;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LengthRulesTests {

    @Autowired
    @Qualifier("lengthRules")
    private IValidationRules lengthRules;


    @Test
    public void testLength5to12() {
        // The validation should pass. The length is between 5 to 12.
        assertEquals(PASS_VALIDATION, lengthRules.validate("12345"));
        assertEquals(PASS_VALIDATION, lengthRules.validate("123456"));
        assertEquals(PASS_VALIDATION, lengthRules.validate("1234567"));
        assertEquals(PASS_VALIDATION, lengthRules.validate("12345678"));
        assertEquals(PASS_VALIDATION, lengthRules.validate("123456789"));
        assertEquals(PASS_VALIDATION, lengthRules.validate("1234567890"));
        assertEquals(PASS_VALIDATION, lengthRules.validate("12345678901"));
        assertEquals(PASS_VALIDATION, lengthRules.validate("123456789012"));
    }

    @Test
    public void testLengthLessThan5() {
        // The validation should fail. The length is less than 5;
        assertEquals(ERROR_PASSWORD_LENGTH, lengthRules.validate("1234"));
    }


    @Test
    public void testLengthGreaterThan12() {
        // The validation should fail. The length is greater than 12;
        assertEquals(ERROR_PASSWORD_LENGTH, lengthRules.validate("1234567890123"));
    }
}
