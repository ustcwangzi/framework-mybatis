package com.wz.mybatis.proxy;

import com.wz.mybatis.mapper.TestMapperXml;
import com.wz.mybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 接口代理类
 * Created by wangzi on 2017-07-30.
 */
public class MapperProxy<T> implements InvocationHandler {
    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TestMapperXml.MapperData mapperData = sqlSession.getConfiguration().getTestMapperXml()
                .get(method.getDeclaringClass().getName() + "." + method.getName());
        if (null != mapperData){
            System.out.println(String.format("SQL [ %s ], parameter [%s] ", mapperData.getSql(), args[0]));
            return sqlSession.selectOne(mapperData, String.valueOf(args[0]));
        }
        return method.invoke(proxy, args);
    }
}
