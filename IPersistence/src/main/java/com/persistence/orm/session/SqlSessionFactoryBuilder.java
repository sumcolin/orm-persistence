package com.persistence.orm.session;

import com.persistence.orm.config.ConfigurationXmlBuilder;
import com.persistence.orm.pojo.Configuration;

import java.io.InputStream;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/6
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) throws Exception {
        // 第一步解析配置文件
        ConfigurationXmlBuilder configurationXmlBuilder = new ConfigurationXmlBuilder();
        Configuration configuration = configurationXmlBuilder.parseConfig(inputStream);

        // 生成sqlSessionFactory对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        return defaultSqlSessionFactory;
    }
}
