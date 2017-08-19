package com.wz.mybatis;

import com.wz.mybatis.executor.Executor;

import java.lang.reflect.Proxy;

/**
 * 持有总的配置Configuration和执行sql的Executor
 * Created by wangzi on 2017-07-30.
 */
public class SqlSession {
    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new MapperProxy<T>(this));
    }

    public <T> T selectOne(MapperXml.MapperData mapperData, Object parameter) throws Exception{
        return executor.query(mapperData, parameter);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
