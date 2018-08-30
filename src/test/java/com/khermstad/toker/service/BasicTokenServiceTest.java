package com.khermstad.toker.service;

import com.khermstad.toker.entity.BasicToken;
import com.khermstad.toker.repository.BasicTokenRepository;
import com.khermstad.toker.to.BasicTokenResponse;
import com.khermstad.toker.util.RepositoryUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// TODO: refactor tests to remove string variables for ApplicationId and Value
// TODO: Try and remove RepositoryUtil from Test dependencies. place inside class.
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

    @Test
    public void insertNewBasicTokenTest(){
        BasicToken basicToken = new BasicToken("A1", "ABC123", true, repositoryUtil.getCurrentTimestamp());
        BasicTokenResponse basicTokenResponse = basicTokenService.insertNewBasicToken(basicToken);
        Assert.assertTrue(basicTokenResponse.isSuccessful());
    }

    @Test
    public void insertDuplicateBasicTokenTest(){
        BasicToken basicToken = new BasicToken("B1", "ABC123", true, repositoryUtil.getCurrentTimestamp());
        BasicToken basicToken2 = new BasicToken("B1", "ABC1234", true, repositoryUtil.getCurrentTimestamp());

        BasicTokenResponse basicTokenResponse1 = basicTokenService.insertNewBasicToken(basicToken);
        Assert.assertTrue(basicTokenResponse1.isSuccessful());

        BasicTokenResponse basicTokenResponse2 = basicTokenService.insertNewBasicToken(basicToken2);
        Assert.assertFalse(basicTokenResponse2.isSuccessful());
    }

    @Test
    public void applicationIdDoesNotExistTest(){
        basicTokenService.applicationIdExists("C1");
        Assert.assertFalse(basicTokenService.applicationIdExists("C1"));
    }

    @Test
    public void applicationIdDoesExistTest(){
        basicTokenRepository.save(new BasicToken("D1", "abc123", false, repositoryUtil.getCurrentTimestamp()));
        Assert.assertTrue(basicTokenService.applicationIdExists("D1"));
    }

    @Test
    public void updateTokenToEnabledTest(){
        BasicToken initialToken = new BasicToken("E1", "ABC123", false, repositoryUtil.getCurrentTimestamp());
        basicTokenService.insertNewBasicToken(initialToken);
        BasicToken savedToken = basicTokenRepository.findByApplicationId("E1");
        Assert.assertFalse(savedToken.isEnabled());

        savedToken.setEnabled(true);
        basicTokenService.updateTokenIsEnabled(savedToken);
        BasicToken updatedToken = basicTokenRepository.findByApplicationId("E1");
        Assert.assertTrue(updatedToken.isEnabled());
    }

    @Test
    public void updateTokenToDisabledTest(){
        BasicToken initialToken = new BasicToken("F1", "ABC123", true, repositoryUtil.getCurrentTimestamp());
        basicTokenService.insertNewBasicToken(initialToken);
        BasicToken savedToken = basicTokenRepository.findByApplicationId("F1");
        Assert.assertTrue(savedToken.isEnabled());

        savedToken.setEnabled(false);
        basicTokenService.updateTokenIsEnabled(savedToken);
        BasicToken updatedToken = basicTokenRepository.findByApplicationId("F1");
        Assert.assertFalse(updatedToken.isEnabled());
    }

    @Test
    public void enableTokenForApplicationIdTest() {
        BasicToken initialToken = new BasicToken("G1", "ABC123", false, repositoryUtil.getCurrentTimestamp());
        basicTokenService.insertNewBasicToken(initialToken);
        BasicToken savedToken = basicTokenRepository.findByApplicationId("G1");
        Assert.assertFalse(savedToken.isEnabled());

        BasicTokenResponse basicTokenResponse = basicTokenService.enableTokenForApplicationId("G1");
        Assert.assertTrue(basicTokenResponse.isSuccessful());
    }

    @Test
    public void enableTokenForApplicationIdNullTokenTest() {
        BasicTokenResponse basicTokenResponse = basicTokenService.enableTokenForApplicationId("H1");
        Assert.assertFalse(basicTokenResponse.isSuccessful());
    }

    @Test
    public void disableTokenForApplicationTest(){
        BasicToken initialToken = new BasicToken("I1", "ABC123", false, repositoryUtil.getCurrentTimestamp());
        basicTokenService.insertNewBasicToken(initialToken);
        BasicToken savedToken = basicTokenRepository.findByApplicationId("I1");
        Assert.assertFalse(savedToken.isEnabled());

        BasicTokenResponse basicTokenResponse = basicTokenService.disableTokenForApplicationId("I1");
        Assert.assertTrue(basicTokenResponse.isSuccessful());
    }

    @Test
    public void disableTokenForApplicationIdNullTokenTest(){
        BasicTokenResponse basicTokenResponse = basicTokenService.disableTokenForApplicationId("J1");
        Assert.assertFalse(basicTokenResponse.isSuccessful());
    }

}
