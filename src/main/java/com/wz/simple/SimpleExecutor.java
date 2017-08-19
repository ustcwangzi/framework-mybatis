package com.wz.simple;

import com.wz.bean.TestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * 执行sql的一种实现
 * 用以sql的执行与结果的映射
 * Created by wangzi on 2017-07-26.
 */
public class SimpleExecutor implements Executor {
    private static final Logger logger = LoggerFactory.getLogger(SimpleExecutor.class);
    @Override
    public <E> E query(String sql, Object parameter) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(sql, Integer.parseInt(String.valueOf(parameter))));
            ResultSet rs = preparedStatement.executeQuery();
            TestBean test = new TestBean();
            while (rs.next()){
                test.setId(rs.getInt(1));
                test.setName(rs.getString(2));
            }
            return (E)test;
        }catch (Exception e){
            logger.error("Executor SQL ERROR {},{}", sql, e.getMessage());
        }
        return null;
    }

    private Connection getConnection() throws Exception{
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String passWord = "123456";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, userName, passWord);
        return connection;
    }
}
