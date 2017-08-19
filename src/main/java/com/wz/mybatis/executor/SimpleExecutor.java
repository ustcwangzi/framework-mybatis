package com.wz.mybatis.executor;

import com.wz.mybatis.hadler.StatementHandler;
import com.wz.mybatis.MapperXml;

/**
 * Created by wangzi on 2017-07-30.
 */
public class SimpleExecutor implements Executor {
    @Override
    public <E> E query(MapperXml.MapperData mapperData, Object parameter) throws Exception {
        StatementHandler handler = new StatementHandler();
        return (E) handler.query(mapperData, parameter);
    }
}
