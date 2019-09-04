package com.innovasolutions.ds.service.security.controller;

import com.innovasolutions.ds.service.security.password.IPasswordValidationService;
import com.innovasolutions.ds.service.security.password.vo.PasswordValidationOutputVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static com.innovasolutions.ds.controller.PasswordValidationController.WARNING_INCORRECT_CONTENT;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class PasswordValidationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPasswordValidationService pwdValidationService;

    @Test
    public void testValidPassword() throws Exception {
        String password = "pas5w0rd";
        String passwordInJson = "{\"password\":\"" + password + "\"}";
        Set<String> result = new HashSet<>();
        PasswordValidationOutputVO outputVO = new PasswordValidationOutputVO(result);

        given(pwdValidationService.validatePassword(password)).willReturn(outputVO);

        this.mockMvc.perform(post("/validatePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(passwordInJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testIncorrectJsonElement() throws Exception {
        String passwordElement = "pa55word";
        String passwordValue = "pas5w0rd";
        String passwordInJson = "{\"" + passwordElement + "\":\"" + passwordValue + "\"}";

        this.mockMvc.perform(post("/validatePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(passwordInJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(WARNING_INCORRECT_CONTENT));
    }
}
