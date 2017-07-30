package com.wz.mybatis.executor;

import com.wz.mybatis.mapper.TestMapperXml;

/**
 * Created by wangzi on 2017-07-27.
 */
public interface Executor {
    <T> T query(TestMapperXml.MapperData mapperData, Object parameter) throws Exception;
}
