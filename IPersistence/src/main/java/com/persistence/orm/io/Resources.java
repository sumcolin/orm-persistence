package com.persistence.orm.io;

import java.io.InputStream;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/5
 */
public class Resources {

    public static InputStream getResourceAsSteam(String path){
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
