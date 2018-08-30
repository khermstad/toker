package com.khermstad.toker.util;

import com.khermstad.toker.constant.DataSourceConstant;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceBuilderUtil {

    public DataSource returnDataSourceFromEnvironmentVariables(Environment environment){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty(DataSourceConstant.DRIVER_CLASS_NAME));
        dataSource.setUrl(environment.getProperty(DataSourceConstant.URL));
        dataSource.setUsername(environment.getProperty(DataSourceConstant.USER_NAME));
        return dataSource;
    }

}
