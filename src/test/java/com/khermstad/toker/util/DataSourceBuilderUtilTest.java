package com.khermstad.toker.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unittest")
@SpringBootTest
public class DataSourceBuilderUtilTest {

    @Autowired
    Environment environment;

    @Autowired
    DataSourceBuilderUtil dataSourceBuilderUtil;

    @Test
    public void returnDataSourceTest(){
        DataSource dataSource = dataSourceBuilderUtil.returnDataSourceFromEnvironmentVariables(environment);
        Assert.assertNotNull(dataSource);
    }

}
