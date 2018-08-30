package com.khermstad.toker.config;

import com.khermstad.toker.util.DataSourceBuilderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

@Profile("unittest")
@Configuration
public class UnitTestConfig {

    @Autowired
    Environment environment;

    @Autowired
    DataSourceBuilderUtil dataSourceBuilderUtil;

    @Bean
    DataSource getDataSource() {
        return dataSourceBuilderUtil.returnDataSourceFromEnvironmentVariables(environment);
    }
}
