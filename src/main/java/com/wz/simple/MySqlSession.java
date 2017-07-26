package com.wz.simple;

import java.lang.reflect.Proxy;

/**
 * 模拟Session
 * Created by wangzi on 2017-07-26.
 */
public class MySqlSession {
    private Executor executor = new SimpleExecutor();

    public <T> T selectOne(String sql, Object parameter){
        return executor.query(sql, parameter);
    }

    public <T> T getMapper(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new MapperProxy<T>(this, clazz));
    }
}
