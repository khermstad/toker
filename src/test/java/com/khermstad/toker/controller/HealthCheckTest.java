package com.khermstad.toker.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HealthCheckTest {

    @Autowired
    private WebApplicationContext wac;

    public MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void healthCheck() throws Exception {

        String checkResponse = mockMvc.perform(get("/")).andReturn().getResponse().getContentAsString();

        Assert.assertEquals("Application is Running", checkResponse);

    }

    public MockMvc getMockMvc() { return mockMvc; }
    public void setMockMvc(MockMvc mockMvc) { this.mockMvc = mockMvc; }
}