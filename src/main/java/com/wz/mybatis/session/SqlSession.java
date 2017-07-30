package com.wz.mybatis.session;

import com.wz.mybatis.config.Configuration;
import com.wz.mybatis.executor.Executor;
import com.wz.mybatis.proxy.MapperProxy;
import com.wz.mybatis.mapper.TestMapperXml;

import java.lang.reflect.Proxy;

/**
 * Created by wangzi on 2017-07-30.
 */
public class SqlSession {
    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new MapperProxy<T>(this, clazz));
    }

    public <T> T selectOne(TestMapperXml.MapperData mapperData, Object parameter) throws Exception{
        return executor.query(mapperData, parameter);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
