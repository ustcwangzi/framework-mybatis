package com.wz.mybatis.hadler;

import com.wz.mybatis.MapperXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 数据库操作和结果处理
 * Created by wangzi on 2017-07-30.
 */
public class StatementHandler {
    private static final Logger logger = LoggerFactory.getLogger(StatementHandler.class);
    private final ResultSetHandler resultSetHandler;

    public StatementHandler() {
        resultSetHandler = new ResultSetHandler();
    }

    public <E> E query(MapperXml.MapperData mapperData, Object parameter) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(String.format(mapperData.getSql(), Integer.parseInt(String.valueOf(parameter))));
            preparedStatement.execute();
            return (E)resultSetHandler.handler(preparedStatement, mapperData);
        }catch (Exception e){
            logger.error("Query Error [{}],{}", mapperData.getSql(), e.getMessage());
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
