package com.khermstad.toker.constant;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unittest")
@SpringBootTest
public class DataSourceConstantTest {

    @Autowired
    Environment environment;

    @Test
    public void testDataSourceEnvironmentVariables(){

        String driverClassName = environment.getProperty(DataSourceConstant.DRIVER_CLASS_NAME);
        String userName = environment.getProperty(DataSourceConstant.USER_NAME);
        String url = environment.getProperty(DataSourceConstant.URL);

        Assert.assertEquals("sa", userName);
        Assert.assertEquals("org.h2.Driver", driverClassName);
        Assert.assertEquals( "jdbc:h2:~/toker-h2;DB_CLOSE_ON_EXIT=FALSE",url);


    }

}
