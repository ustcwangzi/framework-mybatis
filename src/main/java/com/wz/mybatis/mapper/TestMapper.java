package com.wz.mybatis.mapper;

import com.wz.bean.TestBean;

/**
 * Created by wangzi on 2017-07-27.
 */
public interface TestMapper {
    TestBean selectByPrimaryKey(Integer id);
}
