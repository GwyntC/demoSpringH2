package com.example.demo;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = DemoApplication.class)
@AutoConfigureMockMvc
public class e2eTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

//Getting User with good output
    @Test
public void getUserPositiveTest() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/api/users/get/2")
                )
                .andReturn();
        User user = parseResponse(mvcResult, User.class);
        assertThat(user.getAge()).isEqualTo(72);
    }
//Getting user with bad id
    @Test
    public void getUserNegativeTest() throws Exception {
        Exception ex = Assertions.assertThrows(Exception.class, () -> {
                    MvcResult mvcResult = mvc.perform(get("/api/users/get/22")
                            )
                            .andReturn();
                    User user = parseResponse(mvcResult, User.class);
                },
                ("Allowed wrong id"));
        assertThat(ex.getMessage()).contains("Error message");
    }
    private <T> T parseResponse(MvcResult mvcResult, Class<T> c) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }
}
