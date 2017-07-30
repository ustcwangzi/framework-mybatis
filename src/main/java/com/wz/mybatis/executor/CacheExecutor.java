package com.wz.mybatis.executor;

import com.wz.mybatis.hadler.StatementHandler;
import com.wz.mybatis.config.Configuration;
import com.wz.mybatis.mapper.TestMapperXml;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟Myb一级缓存
 * Created by wangzi on 2017-07-30.
 */
public class CacheExecutor implements Executor {
    private Configuration configuration;
    private SimpleExecutor delegate;
    private Map<String , Object> localCache = new HashMap();

    public CacheExecutor(SimpleExecutor delegate) {
        this.delegate = delegate;
    }

    public CacheExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> E query(TestMapperXml.MapperData mapperData, Object parameter) throws Exception {
        StatementHandler handler = new StatementHandler(configuration);
        Object result = localCache.get(mapperData.getSql());
        if( null != result){
            System.out.println("缓存命中");
            return (E)result;
        }
        result =  (E) delegate.query(mapperData,parameter);
        localCache.put(mapperData.getSql(),result);
        return (E)result;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
