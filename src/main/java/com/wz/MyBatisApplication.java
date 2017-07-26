package com.wz;

import com.wz.bean.TestBean;
import com.wz.simple.MySqlSession;
import com.wz.simple.TestMapper;

/**
 * 模拟Mybatis
 * Created by wangzi on 2017-07-25.
 */
public class MyBatisApplication {
    public static void main(String[] args) {
        startSimple();
    }

    private static void startSimple(){
        MySqlSession sqlSession = new MySqlSession();
        //使用同一的代理类去完成sql操作
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        TestBean test = testMapper.selectByPrimaryKey(1);
        System.out.println(test);
    }
}
