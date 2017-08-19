package com.wz.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 接口代理类
 * Created by wangzi on 2017-07-30.
 */
public class MapperProxy<T> implements InvocationHandler {
    private static final Logger logger = LoggerFactory.getLogger(MapperProxy.class);
    private final SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperXml.MapperData mapperData = sqlSession.getConfiguration().getMapperXml()
                .get(method.getDeclaringClass().getName() + "." + method.getName());
        if (null != mapperData){
            logger.info("SQL[ {} ], parameter[ {} ]", mapperData.getSql(), args[0]);
            return sqlSession.selectOne(mapperData, String.valueOf(args[0]));
        }
        logger.error("Cannot found Corresponding sql of method[ {} ]", method);
        return null;
    }
}
