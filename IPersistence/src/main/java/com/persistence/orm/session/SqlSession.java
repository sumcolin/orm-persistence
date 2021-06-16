package com.persistence.orm.session;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/6
 */
public interface SqlSession {

    public <T> T getMapper(Class<?> mapperClass);
}
