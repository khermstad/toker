package com.khermstad.toker.service;

import com.khermstad.toker.entity.BasicToken;
import com.khermstad.toker.repository.BasicTokenRepository;
import com.khermstad.toker.to.BasicTokenResponse;
import com.khermstad.toker.util.BasicTokenGenerator;
import com.khermstad.toker.util.RepositoryUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unittest")
@EnableJpaRepositories
@SpringBootTest
public class BasicTokenServiceTest {

    @Autowired
    BasicTokenService basicTokenService;

    @Autowired
    BasicTokenRepository basicTokenRepository;

    @Autowired
    RepositoryUtil repositoryUtil;

    @Autowired
    BasicTokenGenerator basicTokenGenerator;

    public String applicationId = "TEST_APP_1";
    public String tokenValue = "ABC123";

    @After
    public void cleanUpPreviousTest(){
            basicTokenRepository.deleteAll();
    }

    @Test
    public void insertNewBasicTokenTest(){
        BasicToken basicToken = getValidTestToken(true);
        BasicTokenResponse basicTokenResponse = basicTokenService.insertNewBasicToken(basicToken);
        Assert.assertTrue(basicTokenResponse.isSuccessful());
    }

    @Test
    public void insertDuplicateBasicTokenTest(){
        BasicToken basicToken =  getValidTestToken(true);
        BasicToken basicToken2 =  getValidTestToken(true);

        BasicTokenResponse basicTokenResponse1 = basicTokenService.insertNewBasicToken(basicToken);
        Assert.assertTrue(basicTokenResponse1.isSuccessful());

        BasicTokenResponse basicTokenResponse2 = basicTokenService.insertNewBasicToken(basicToken2);
        Assert.assertFalse(basicTokenResponse2.isSuccessful());
    }

    @Test
    public void applicationIdDoesNotExistTest(){
        basicTokenService.applicationIdExists(applicationId);
        Assert.assertFalse(basicTokenService.applicationIdExists(applicationId));
    }

    @Test
    public void applicationIdDoesExistTest(){
        basicTokenRepository.save( getValidTestToken(false));
        Assert.assertTrue(basicTokenService.applicationIdExists(applicationId));
    }

    @Test
    public void updateTokenToEnabledTest(){
        BasicToken initialToken =  getValidTestToken(false);
        basicTokenService.insertNewBasicToken(initialToken);
        BasicToken savedToken = basicTokenRepository.findByApplicationId(applicationId);
        Assert.assertFalse(savedToken.isEnabled());

        savedToken.setEnabled(true);
        basicTokenService.updateTokenIsEnabled(savedToken);
        BasicToken updatedToken = basicTokenRepository.findByApplicationId(applicationId);
        Assert.assertTrue(updatedToken.isEnabled());
    }

    @Test
    public void updateTokenToDisabledTest(){
        BasicToken initialToken =  getValidTestToken(true);
        basicTokenService.insertNewBasicToken(initialToken);
        BasicToken savedToken = basicTokenRepository.findByApplicationId(applicationId);
        Assert.assertTrue(savedToken.isEnabled());

        savedToken.setEnabled(false);
        basicTokenService.updateTokenIsEnabled(savedToken);
        BasicToken updatedToken = basicTokenRepository.findByApplicationId(applicationId);
        Assert.assertFalse(updatedToken.isEnabled());
    }

    @Test
    public void enableTokenForApplicationIdTest() {
        BasicToken initialToken =  getValidTestToken(false);
        basicTokenService.insertNewBasicToken(initialToken);
        BasicToken savedToken = basicTokenRepository.findByApplicationId(applicationId);
        Assert.assertFalse(savedToken.isEnabled());

        BasicTokenResponse basicTokenResponse = basicTokenService.enableTokenForApplicationId(applicationId);
        Assert.assertTrue(basicTokenResponse.isSuccessful());
    }

    @Test
    public void enableTokenForApplicationIdNullTokenTest() {
        BasicTokenResponse basicTokenResponse = basicTokenService.enableTokenForApplicationId(applicationId);
        Assert.assertFalse(basicTokenResponse.isSuccessful());
    }

    @Test
    public void disableTokenForApplicationTest(){
        BasicToken initialToken =  getValidTestToken(false);
        basicTokenService.insertNewBasicToken(initialToken);
        BasicToken savedToken = basicTokenRepository.findByApplicationId(applicationId);
        Assert.assertFalse(savedToken.isEnabled());

        BasicTokenResponse basicTokenResponse = basicTokenService.disableTokenForApplicationId(applicationId);
        Assert.assertTrue(basicTokenResponse.isSuccessful());
    }

    @Test
    public void disableTokenForApplicationIdNullTokenTest(){
        BasicTokenResponse basicTokenResponse = basicTokenService.disableTokenForApplicationId(applicationId);
        Assert.assertFalse(basicTokenResponse.isSuccessful());
    }

    public BasicToken getValidTestToken(boolean isEnabled){
        return new BasicToken(applicationId, tokenValue, isEnabled, repositoryUtil.getCurrentTimestamp());
    }

}
