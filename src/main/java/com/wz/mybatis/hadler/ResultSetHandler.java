package com.wz.mybatis.hadler;

import com.wz.mybatis.config.Configuration;
import com.wz.mybatis.mapper.TestMapperXml;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 结果类型映射
 * Created by wangzi on 2017-07-30.
 */
public class ResultSetHandler {
    private final Configuration configuration;

    public ResultSetHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> E handler(PreparedStatement statement, TestMapperXml.MapperData mapperData) throws Exception{
        Object resultObj = new DefaultObjectFactory().create(mapperData.getType());
        ResultSet rs = statement.getResultSet();
        if (rs.next()){
            for (Field field : resultObj.getClass().getDeclaredFields()){
                setValue(resultObj, field, rs);
            }
        }
        return (E)resultObj;
    }

    private void setValue(Object resultObj, Field field, ResultSet rs) throws Exception{
        Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        setMethod.invoke(resultObj, getResult(field, rs));
    }

    /**
     * Type Handler
     * @param field
     * @param rs
     * @return
     * @throws SQLException
     */
    private Object getResult(Field field, ResultSet rs) throws SQLException{
        Class type = field.getType();
        if(Integer.class == type){
            return rs.getInt(field.getName());
        }
        if (String.class == type){
            return rs.getString(field.getName());
        }
        return rs.getString(field.getName());
    }

    /**
     * 首字符大写
     * @param str
     * @return
     */
    private String upperCapital(String str){
        String first = str.substring(0, 1);
        String tail = str.substring(1);
        return first.toUpperCase() + tail;
    }

}
