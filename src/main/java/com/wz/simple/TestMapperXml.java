package com.wz.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟Mybatis中的XML文件，建立方法和SQL
 * Created by wangzi on 2017-07-26.
 */
public class TestMapperXml {
    public static final String nameSpace = "com.wz.simple.TestMapper";
    //方法和sql的映射
    public static final Map<String, String> methodSqlMapping = new HashMap<>();
    static {
        methodSqlMapping.put("selectByPrimaryKey", "select * from test where id = %d");
    }
}
