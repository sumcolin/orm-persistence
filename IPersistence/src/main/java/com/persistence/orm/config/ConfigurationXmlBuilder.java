package com.persistence.orm.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.persistence.orm.io.Resources;
import com.persistence.orm.pojo.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/5
 */
public class ConfigurationXmlBuilder {

    private Configuration configuration;

    public ConfigurationXmlBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {

        // 1、获取文件输入流
        Document document = new SAXReader().read(inputStream);
        // 2、解析文件流
        List<Element> propertyElements = document.selectNodes("/configuration/property");
        Properties properties = new Properties();
        propertyElements.forEach(e -> {
            String k = e.attributeValue("name");
            String v = e.attributeValue("value");
            properties.setProperty(k, v);
        });
        // 3、数据库连接池配置

        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
        comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
        comboPooledDataSource.setUser(properties.getProperty("username"));
        comboPooledDataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(comboPooledDataSource);

        //4、获取sqlMapper配置
        List<Element> mapperElements = document.selectNodes("/configuration/mapper");
        mapperElements.forEach(e -> {
            String mapperResourcePath = e.attributeValue("resource");
            InputStream resourceAsSteam = Resources.getResourceAsSteam(mapperResourcePath);
            MapperXmlBuilder mapperXmlBuilder = new MapperXmlBuilder(configuration);
            try {
                mapperXmlBuilder.parse(resourceAsSteam);
            } catch (DocumentException documentException) {
                documentException.printStackTrace();
            }
        });

        return configuration;

    }

}
