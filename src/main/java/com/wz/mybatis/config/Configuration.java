package com.wz.mybatis.config;

import com.wz.mybatis.mapper.TestMapperXml;

import java.io.IOException;

/**
 * Created by wangzi on 2017-07-27.
 */
public class Configuration {
    private String scanPath;
    private TestMapperXml testMapperXml = new TestMapperXml();

    public Configuration scanPath(String scanPath) {
        this.scanPath = scanPath;
        return this;
    }

    public void build() throws IOException {
        if (null == scanPath || scanPath.length() < 1) {
            throw new RuntimeException("scan path is required .");
        }
    }

    public TestMapperXml getTestMapperXml() {
        return testMapperXml;
    }
}
