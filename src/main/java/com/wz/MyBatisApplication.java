package com.wz;

import com.wz.bean.TestBean;
import com.wz.mybatis.config.Configuration;
import com.wz.mybatis.executor.ExecutorFactory;
import com.wz.simple.SqlSession;
import com.wz.simple.TestMapper;

import java.io.IOException;

/**
 * 模拟Mybatis
 * Created by wangzi on 2017-07-25.
 */
public class MyBatisApplication {
    public static void main(String[] args) throws Exception{
        startSimple();
//        start();
    }

    //简单版本
    private static void startSimple(){
        SqlSession sqlSession = new SqlSession();
        //使用同一的代理类去完成sql操作
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        TestBean test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);
    }

    private static void start() throws IOException{
        Configuration configuration = new Configuration().scanPath("com.wz.mybatis.mapper");
        configuration.build();
//        SqlSession sqlSession = new SqlSession(configuration, ExecutorFactory.defaultExecutor(configuration));
        com.wz.mybatis.session.SqlSession sqlSession = new com.wz.mybatis.session.SqlSession(configuration, ExecutorFactory.get(ExecutorFactory.ExecutorType.CACHE, configuration));
        com.wz.mybatis.mapper.TestMapper testMapper =  sqlSession.getMapper(com.wz.mybatis.mapper.TestMapper.class);
        long start = System.currentTimeMillis();
        TestBean test = testMapper.selectByPrimaryKey(1);
        System.out.println("cost:"+ (System.currentTimeMillis() -start));
        System.out.println(test);
        start = System.currentTimeMillis();
        test = testMapper.selectByPrimaryKey(1);
        System.out.println("cost:"+ (System.currentTimeMillis() -start));
        System.out.println(test);
    }
}
