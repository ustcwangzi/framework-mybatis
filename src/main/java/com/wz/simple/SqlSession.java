package com.wz.simple;

import java.lang.reflect.Proxy;

/**
 * 模拟Session
 * Created by wangzi on 2017-07-26.
 */
public class SqlSession {
    private Executor executor = new SimpleExecutor();

    //真正调用此方法的是MapperProxy，即代理对象
    public <T> T selectOne(String sql, Object parameter){
        return executor.query(sql, parameter);
    }

    //返回代理对象
    public <T> T getMapper(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new MapperProxy<T>(this, clazz));
    }
}
