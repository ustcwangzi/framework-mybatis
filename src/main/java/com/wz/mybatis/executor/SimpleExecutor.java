package com.wz.mybatis.executor;

import com.wz.mybatis.hadler.StatementHandler;
import com.wz.mybatis.config.Configuration;
import com.wz.mybatis.mapper.TestMapperXml;

/**
 * Created by wangzi on 2017-07-30.
 */
public class SimpleExecutor implements Executor {
    private Configuration configuration;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> E query(TestMapperXml.MapperData mapperData, Object parameter) throws Exception {
        StatementHandler handler = new StatementHandler(configuration);
        return (E) handler.query(mapperData, parameter);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
