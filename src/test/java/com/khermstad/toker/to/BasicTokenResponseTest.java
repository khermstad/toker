package com.khermstad.toker.to;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unittest")
@SpringBootTest
public class BasicTokenResponseTest {

    @Test
    public void basicTokenResponseConstructorTest() {
        BasicTokenResponse basicTokenResponse = new BasicTokenResponse("Token Has a message", false);
        Assert.assertTrue(null != basicTokenResponse.getMessage() && basicTokenResponse.getMessage().length() > 0);
        Assert.assertFalse(basicTokenResponse.isSuccessful());
    }

    @Test
    public void basicTokenResponseGetterSetterTest(){
        BasicTokenResponse basicTokenResponse = new BasicTokenResponse();
        basicTokenResponse.setMessage("Token has a message");
        basicTokenResponse.setSuccessful(true);
        Assert.assertTrue(null != basicTokenResponse.getMessage() && basicTokenResponse.getMessage().length() > 0);
        Assert.assertTrue(basicTokenResponse.isSuccessful());

    }
}
