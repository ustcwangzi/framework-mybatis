package com.wz.mybatis.executor;

import com.wz.mybatis.MapperXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟Mybatis一级缓存
 * Created by wangzi on 2017-07-30.
 */
public class CacheExecutor implements Executor {
    private static final Logger logger = LoggerFactory.getLogger(CacheExecutor.class);
    private SimpleExecutor delegate;
    private Map<String , Object> localCache = new HashMap();

    public CacheExecutor(SimpleExecutor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <E> E query(MapperXml.MapperData mapperData, Object parameter) throws Exception {
        Object result = localCache.get(mapperData.getSql());
        if( null != result){
            logger.info("[{}] 命中缓存", mapperData.getSql());
            return (E)result;
        }
        result =  (E) delegate.query(mapperData,parameter);
        localCache.put(mapperData.getSql(),result);
        return (E)result;
    }
}
