package com.wz.mybatis.executor;

/**
 * Executor工厂方法
 * Created by wangzi on 2017-07-30.
 */
public class ExecutorFactory {

    public static Executor defaultExecutor(){
        return get(ExecutorType.SIMPLE);
    }

    public static Executor get(ExecutorType type){
        if (ExecutorType.SIMPLE.equals(type)){
            return new SimpleExecutor();
        }
        if (ExecutorType.CACHE.equals(type)){
            return new CacheExecutor(new SimpleExecutor());
        }
        throw new RuntimeException("No executor found");
    }

    public enum ExecutorType{
        SIMPLE, CACHE
    }
}
