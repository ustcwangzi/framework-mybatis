package com.wz.mybatis.executor;

import com.wz.mybatis.MapperXml;

/**
 * Created by wangzi on 2017-07-27.
 */
public interface Executor {
    <T> T query(MapperXml.MapperData mapperData, Object parameter) throws Exception;
}
