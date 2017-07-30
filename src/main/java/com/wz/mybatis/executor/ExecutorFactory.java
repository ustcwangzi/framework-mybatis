package com.wz.mybatis.executor;

import com.wz.mybatis.config.Configuration;
import org.apache.ibatis.executor.CachingExecutor;

/**
 * Executor工厂方法
 * Created by wangzi on 2017-07-30.
 */
public class ExecutorFactory {

    public static Executor defaultExecutor(Configuration configuration){
        return get(ExecutorType.SIMPLE, configuration);
    }

    public static Executor get(ExecutorType type, Configuration configuration){
        if (ExecutorType.SIMPLE.equals(type)){
            return new SimpleExecutor(configuration);
        }
        if (ExecutorType.CACHE.equals(type)){
            return new CacheExecutor(new SimpleExecutor(configuration));
        }
        throw new RuntimeException("No executor found");
    }

    public enum ExecutorType{
        SIMPLE, CACHE
    }
}
