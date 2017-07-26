package com.wz.simple;

import com.wz.bean.TestBean;

import java.sql.*;

/**
 * 模拟sql的执行与结果的映射
 * Created by wangzi on 2017-07-26.
 */
public class SimpleExecutor implements Executor {
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
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws Exception{
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String passWord = "123456";
        Connection connection = null;
        Class.forName(driver);
        connection = DriverManager.getConnection(url, userName, passWord);
        return connection;
    }
}
