package com.wz;

import com.wz.bean.TestBean;
import com.wz.mybatis.Configuration;
import com.wz.mybatis.Mapper;
import com.wz.mybatis.executor.ExecutorFactory;
import com.wz.simple.SqlSession;
import com.wz.simple.TestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 模拟Mybatis
 * Created by wangzi on 2017-07-25.
 */
public class MyBatisApplication {
    private static final Logger logger = LoggerFactory.getLogger(MyBatisApplication.class);
    public static void main(String[] args) throws Exception{
        startSimple();
//        start();
    }

    //简单版本
    private static void startSimple(){
        TestMapper testMapper = new SqlSession().getMapper(TestMapper.class);
        TestBean test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);
    }

    private static void start() throws IOException{
//        com.wz.mybatis.SqlSession sqlSession = new com.wz.mybatis.SqlSession(new Configuration(), ExecutorFactory.defaultExecutor());
        com.wz.mybatis.SqlSession sqlSession = new com.wz.mybatis.SqlSession(new Configuration(), ExecutorFactory.get(ExecutorFactory.ExecutorType.CACHE));
        Mapper testMapper =  sqlSession.getMapper(Mapper.class);
        long start = System.currentTimeMillis();
        TestBean test = testMapper.selectByPrimaryKey(1);
        logger.info("cost time:{}", System.currentTimeMillis() -start);
        System.out.println(test);
        start = System.currentTimeMillis();
        test = testMapper.selectByPrimaryKey(1);
        logger.info("cost time:{}", System.currentTimeMillis() -start);
        System.out.println(test);
    }
}
