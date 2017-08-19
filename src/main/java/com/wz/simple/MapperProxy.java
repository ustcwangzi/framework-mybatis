package com.wz.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 统一的接口代理实现
 * 所有接口都是用这个类进行代理的
 * Created by wangzi on 2017-07-26.
 */
public class MapperProxy<T> implements InvocationHandler{
    private static final Logger logger = LoggerFactory.getLogger(MapperProxy.class);
    private final SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getDeclaringClass().getName() + "." + method.getName();
        if(methodName.equals(TestMapperXml.nameSpace)){
            String sql = TestMapperXml.methodSqlMapping.get(methodName);
            if (sql != null) {
                logger.info("SQL[ {} ], parameter[ {} ]", sql, args[0]);
                return sqlSession.selectOne(sql, String.valueOf(args[0]));
            }
        }
        logger.error("Cannot found Corresponding sql of method[ {} ]", method);
        return null;
    }
}
