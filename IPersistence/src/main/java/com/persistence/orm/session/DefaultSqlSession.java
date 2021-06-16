package com.persistence.orm.session;

import com.persistence.orm.pojo.Configuration;
import com.persistence.orm.pojo.MapperStatement;

import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/6
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public <T> T getMapper(Class<?> mapperClass) {

        Object objectInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                // 获取类路径名称
                String className = method.getDeclaringClass().getName();
                String methodName = method.getName();

                // 获取Statement+id对象
                String key = className + "." + methodName;

                Type genericReturnType = method.getGenericReturnType();
                SimpleExecutor simpleExecutor = new SimpleExecutor();
                String sql = configuration.getMapperStatements().get(key).getSql();
                if (sql.indexOf("select") >= 0) {
                    List<Object> objects = simpleExecutor.query(configuration, configuration.getMapperStatements().get(key), args);
                    if (genericReturnType instanceof ParameterizedType) {
                        return objects;
                    }
                    return objects.get(0);
                } else {
                    // 更新、删除、新增
                    simpleExecutor.update(configuration, configuration.getMapperStatements().get(key), args);
                    return proxy;
                }
            }
        });

        return (T) objectInstance;
    }
}
