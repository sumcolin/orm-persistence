package com.persistence.orm.config;

import com.persistence.orm.pojo.Configuration;
import com.persistence.orm.pojo.MapperStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/5
 */
public class MapperXmlBuilder {

    private Configuration configuration;

    public MapperXmlBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        // 1、获取跟目录标识
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");

        // 2、获取select标签xml节点
        List<Element> selectElements = rootElement.selectNodes("select");

        selectElements.forEach(e -> {
            String id = e.attributeValue("id");
            String parameterType = e.attributeValue("parameterType");
            String resultType = e.attributeValue("resultType");
            String sqlTextTrim = e.getTextTrim();

            MapperStatement mapperStatement = new MapperStatement();
            mapperStatement.setId(id);
            mapperStatement.setParameterType(parameterType);
            mapperStatement.setResultType(resultType);
            mapperStatement.setSql(sqlTextTrim);

            String key = namespace + "." + id;
            configuration.getMapperStatements().put(key, mapperStatement);

        });

        // 2、获取select标签xml节点
        List<Element> updateElements = rootElement.selectNodes("update");

        updateElements.forEach(e -> {
            String id = e.attributeValue("id");
            String parameterType = e.attributeValue("parameterType");
            String resultType = e.attributeValue("resultType");
            String sqlTextTrim = e.getTextTrim();

            MapperStatement mapperStatement = new MapperStatement();
            mapperStatement.setId(id);
            mapperStatement.setParameterType(parameterType);
            mapperStatement.setResultType(resultType);
            mapperStatement.setSql(sqlTextTrim);

            String key = namespace + "." + id;
            configuration.getMapperStatements().put(key, mapperStatement);

        });


        List<Element> insertElements = rootElement.selectNodes("insert");

        insertElements.forEach(e -> {
            String id = e.attributeValue("id");
            String parameterType = e.attributeValue("parameterType");
            String resultType = e.attributeValue("resultType");
            String sqlTextTrim = e.getTextTrim();

            MapperStatement mapperStatement = new MapperStatement();
            mapperStatement.setId(id);
            mapperStatement.setParameterType(parameterType);
            mapperStatement.setResultType(resultType);
            mapperStatement.setSql(sqlTextTrim);

            String key = namespace + "." + id;
            configuration.getMapperStatements().put(key, mapperStatement);

        });

        List<Element> deleteElements = rootElement.selectNodes("delete");

        deleteElements.forEach(e -> {
            String id = e.attributeValue("id");
            String parameterType = e.attributeValue("parameterType");
            String resultType = e.attributeValue("resultType");
            String sqlTextTrim = e.getTextTrim();

            MapperStatement mapperStatement = new MapperStatement();
            mapperStatement.setId(id);
            mapperStatement.setParameterType(parameterType);
            mapperStatement.setResultType(resultType);
            mapperStatement.setSql(sqlTextTrim);

            String key = namespace + "." + id;
            configuration.getMapperStatements().put(key, mapperStatement);

        });

    }

}
