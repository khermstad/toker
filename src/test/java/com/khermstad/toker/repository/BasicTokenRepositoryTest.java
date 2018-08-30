package com.khermstad.toker.repository;

import com.khermstad.toker.entity.BasicToken;
import com.khermstad.toker.util.RepositoryUtil;
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
public class BasicTokenRepositoryTest {

    @Autowired
    BasicTokenRepository basicTokenRepository;

    @Autowired
    RepositoryUtil repositoryUtil;

    public String applicationId = "TEST_APP_1";
    public String tokenValue = "ABC123";

    @Test
    public void testBasicTokenInsertion(){

        BasicToken basicToken = new BasicToken(
                applicationId,
                tokenValue,
                false,
                repositoryUtil.getCurrentTimestamp()
        );

        basicTokenRepository.save(basicToken);
        Assert.assertNotNull((basicTokenRepository.findByApplicationId(applicationId)));
    }

    @Test
    public void testInsertDuplicateApplicationId(){

        BasicToken basicToken1 = new BasicToken(
                applicationId,
                tokenValue,
                false,
                repositoryUtil.getCurrentTimestamp()
        );

        BasicToken basicToken2 = new BasicToken(
                applicationId,
                tokenValue,
                false,
                repositoryUtil.getCurrentTimestamp()
        );

        basicTokenRepository.save(basicToken1);
        basicTokenRepository.save(basicToken2);

        BasicToken basicToken = basicTokenRepository.findByApplicationId(applicationId);

        Assert.assertTrue(null != basicToken);
    }

    @Test
    public void findByApplicationIdAndValueTest(){
        BasicToken basicToken = new BasicToken(applicationId, tokenValue, false, repositoryUtil.getCurrentTimestamp());
        basicTokenRepository.save(basicToken);
        BasicToken findByResult = basicTokenRepository.findByApplicationIdAndValue(applicationId, tokenValue);
        Assert.assertNotNull(findByResult);
    }

}
