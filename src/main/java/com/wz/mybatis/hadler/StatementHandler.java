package com.wz.mybatis.hadler;

import com.wz.mybatis.config.Configuration;
import com.wz.mybatis.mapper.TestMapperXml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 数据库操作和结果处理
 * Created by wangzi on 2017-07-30.
 */
public class StatementHandler {
    private final Configuration configuration;
    private final ResultSetHandler resultSetHandler;

    public StatementHandler(Configuration configuration) {
        this.configuration = configuration;
        resultSetHandler = new ResultSetHandler(configuration);
    }

    public <E> E query(TestMapperXml.MapperData mapperData, Object parameter) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(mapperData.getSql(), Integer.parseInt(String.valueOf(parameter))));
            preparedStatement.execute();
            return (E)resultSetHandler.handler(preparedStatement, mapperData);
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
