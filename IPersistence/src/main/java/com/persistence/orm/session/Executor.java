package com.persistence.orm.session;

import com.persistence.orm.pojo.Configuration;
import com.persistence.orm.pojo.MapperStatement;

import java.sql.SQLException;
import java.util.List;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/6
 */
public interface Executor {

    public <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object... params) throws SQLException, Exception;

    // 更新、删除、新增
    public int update(Configuration configuration, MapperStatement mapperStatement, Object... params) throws Exception;
}
