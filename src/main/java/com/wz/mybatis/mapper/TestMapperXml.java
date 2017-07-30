package com.wz.mybatis.mapper;

import com.wz.bean.TestBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟xml配置文件
 * Created by wangzi on 2017-07-27.
 */
public class TestMapperXml {
    public static final Map<String, MapperData> methodSqlMapping = new HashMap<>();

    public TestMapperXml() {
        methodSqlMapping.put("com.wz.mybatis.mapper.TestMapper.selectByPrimaryKey", new MapperData("select * from test where id = %d", TestBean.class));
    }

    public MapperData get(String nameSpace){
        return methodSqlMapping.get(nameSpace);
    }

    /**
     * 模拟SQL与返回结果类型
     * @param <T>
     */
    public class MapperData<T>{
        private String sql;
        private Class<T> type;

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Class<T> getType() {
            return type;
        }

        public void setType(Class<T> type) {
            this.type = type;
        }
    }
}
