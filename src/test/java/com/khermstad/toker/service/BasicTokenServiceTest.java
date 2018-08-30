package com.khermstad.toker.service;

import com.khermstad.toker.entity.BasicToken;
import com.khermstad.toker.repository.BasicTokenRepository;
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
public class BasicTokenServiceTest {

    @Autowired
    BasicTokenService basicTokenService;

    @Autowired
    BasicTokenRepository basicTokenRepository;

    @Autowired
    RepositoryUtil repositoryUtil;

    @Test
    public void applicationIdDoesntExistTest(){
        basicTokenService.applicationIdExists("A1");
        Assert.assertFalse(basicTokenService.applicationIdExists("A1"));
    }

    @Test
    public void applicationIdDoesExistTest(){
        basicTokenRepository.save(new BasicToken("abc", "abc123", false, repositoryUtil.getCurrentTimestamp()));
        Assert.assertTrue(basicTokenService.applicationIdExists("abc"));
    }

}
