package com.innovasolutions.ds.service.security.password;

import com.innovasolutions.ds.service.security.password.IPasswordValidationService;
import com.innovasolutions.ds.service.security.password.vo.PasswordValidationOutputVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.innovasolutions.ds.service.security.rules.ValidationMessages.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordValidationServiceTests {

    @Autowired
    private IPasswordValidationService pwdValidationService;

    private PasswordValidationOutputVO outputVO;

    @Test
    public void testAtLeastOneDigitAndLetter() {
        outputVO = pwdValidationService.validatePassword("pas5w0rd");

        // The validation should pass.
        assertFalse(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
    }

    @Test
    public void testOnlyLowerCaseLetters() {
        outputVO = pwdValidationService.validatePassword("password");
        // The validation should fail.  It needs a digit as well.
        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testUpperCaseLetters() {
        outputVO = pwdValidationService.validatePassword("PASSWORD");
        // The validation should fail. Capital letter is not allowed.
        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testOnlyOneLetter() {
        outputVO = pwdValidationService.validatePassword("p");
        // The validation should fail. It needs a digit as well.
        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testOnlyOneDigits() {
        outputVO = pwdValidationService.validatePassword("0");
        // The validation should fail. It needs a letter as well.
        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testOnlyDigits() {
        outputVO = pwdValidationService.validatePassword("00000");
        // The validation should fail. It needs a letter as well.
        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testSymbols() {
        outputVO = pwdValidationService.validatePassword("pa$$w0rd");
        // The validation should fail. Only letter and digit.
        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testWhitespace() {
        outputVO = pwdValidationService.validatePassword(" ");
        // The validation should fail. Only letter and digit.
        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));

        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testEmptyString() {
        outputVO = pwdValidationService.validatePassword("");
        // The validation should fail. Only letter and digit.
        assertTrue(outputVO.getMessages().contains(ERROR_NULL_PASSWORD));

        assertFalse(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testNullString() {
        outputVO = pwdValidationService.validatePassword(null);
        // The validation should fail. Only letter and digit.
        assertTrue(outputVO.getMessages().contains(ERROR_NULL_PASSWORD));

        assertFalse(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testLength5And12() {
        // The validation should pass. The length is between 5 to 12.
        outputVO = pwdValidationService.validatePassword("pas51");
        // The validation should pass.
        assertFalse(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));

        outputVO = pwdValidationService.validatePassword("1234567890pa");
        // The validation should pass.
        assertFalse(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
    }

    @Test
    public void testLengthLessThan5() {
        outputVO = pwdValidationService.validatePassword("1234");
        // The validation should fail. The length is less than 5;
        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));

        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }


    @Test
    public void testLengthGreaterThan12() {
        outputVO = pwdValidationService.validatePassword("1234567890123");
        // The validation should fail. The length is greater than 12;
        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));

        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testNonRepeatingSequence() {
        outputVO = pwdValidationService.validatePassword("pas5w0rd");

        // The validation should pass.
        assertFalse(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
    }

    @Test
    public void testSequenceRepeatLetters() {
        outputVO = pwdValidationService.validatePassword("wordword");
        // The validation should fail. "word" is repeated.
        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));

        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testRepeatSingleLetter() {
        outputVO = pwdValidationService.validatePassword("pp");
        // The validation should fail. "pp" is repeated.
        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));

        assertTrue(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testRepeatLettersAndDigits() {
        outputVO = pwdValidationService.validatePassword("w0w0");
        // The validation should fail. "w0" is repeated
        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));

        assertTrue(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(PASS_VALIDATION));
    }

    @Test
    public void testSeparateDuplicatedLetters() {
        outputVO = pwdValidationService.validatePassword("abc123abc");

        // The validation should pass.
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
    }

    @Test
    public void testSeparateDuplicatedDigits() {
        outputVO = pwdValidationService.validatePassword("123abc123");

        // The validation should pass.
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_LENGTH));
        assertFalse(outputVO.getMessages().contains(ERROR_AT_LEAST_ONE_OF_LETTER_AND_DIGIT));
        assertFalse(outputVO.getMessages().contains(ERROR_PASSWORD_REPEATING_SEQUENCE));
    }
}
