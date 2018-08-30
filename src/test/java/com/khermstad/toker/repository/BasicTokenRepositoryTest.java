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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unittest")
@EnableJpaRepositories
@SpringBootTest
public class BasicTokenRepositoryTest {

    @Autowired
    BasicTokenRepository basicTokenRepository;

    @Autowired
    RepositoryUtil repositoryUtil;

    @Test
    public void testBasicTokenInsertion(){

        BasicToken basicToken = new BasicToken(
                "A1",
                "ABC123",
                false,
                repositoryUtil.getCurrentTimestamp()
        );

        basicTokenRepository.save(basicToken);
        Assert.assertNotNull((basicTokenRepository.findByApplicationId("A1")));
    }

    @Test
    public void testInsertDuplicateApplicationId(){

        BasicToken basicToken1 = new BasicToken(
                "A1",
                "ABC123",
                false,
                repositoryUtil.getCurrentTimestamp()
        );

        BasicToken basicToken2 = new BasicToken(
                "A1",
                "ABC123",
                false,
                repositoryUtil.getCurrentTimestamp()
        );

        basicTokenRepository.save(basicToken1);
        basicTokenRepository.save(basicToken2);

        List<BasicToken> basicTokens = basicTokenRepository.findByApplicationId("A1");

        Assert.assertTrue(basicTokens.size() == 1);
    }

    @Test
    public void findByApplicationIdAndValueTest(){
        BasicToken basicToken = new BasicToken("A1", "ABC123", false, repositoryUtil.getCurrentTimestamp());
        basicTokenRepository.save(basicToken);
        BasicToken findByResult = basicTokenRepository.findByApplicationIdAndValue("A1", "ABC123");
        Assert.assertNotNull(findByResult);
    }

}
