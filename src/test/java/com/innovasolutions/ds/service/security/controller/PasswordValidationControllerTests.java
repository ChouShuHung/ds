package com.innovasolutions.ds.service.security.controller;

import com.innovasolutions.ds.service.security.password.IPasswordValidationService;
import com.innovasolutions.ds.service.security.password.vo.PasswordValidationOutputVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void testvValidatePassword() throws Exception {
        String password = "pas5w0rd";
        PasswordValidationOutputVO outputVO = new PasswordValidationOutputVO(Boolean.TRUE, new HashSet<>());

        given(pwdValidationService.validatePassword(password)).willReturn(outputVO);

//        this.mockMvc.perform(post("/validatePassword"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("[{\n" +
//                        "    \"messages\": [],\n" +
//                        "    \"passwordValid\": true\n" +
//                        "}]"));
    }
}
