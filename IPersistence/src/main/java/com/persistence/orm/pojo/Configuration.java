package com.persistence.orm.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/5
 */
public class Configuration {

    private DataSource dataSource;

    private Map<String,MapperStatement> mapperStatements = new HashMap<String, MapperStatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MapperStatement> getMapperStatements() {
        return mapperStatements;
    }

    public void setMapperStatements(Map<String, MapperStatement> mapperStatements) {
        this.mapperStatements = mapperStatements;
    }
}
