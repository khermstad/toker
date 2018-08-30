package com.khermstad.toker.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unittest")
@SpringBootTest
public class RepositoryUtilTest {

    @Autowired
    RepositoryUtil repositoryUtil;

    @Test
    public void getCurrentTimestampTest() throws InterruptedException{
        Timestamp timestamp1 = repositoryUtil.getCurrentTimestamp();
        Thread.sleep(10);
        Timestamp timestamp2 = repositoryUtil.getCurrentTimestamp();
        Thread.sleep(10);
        Timestamp timestamp3 = repositoryUtil.getCurrentTimestamp();

        Assert.assertTrue(timestamp1.before(timestamp2) && timestamp3.after(timestamp2));
    }

}
