package com.wz.simple;

import com.wz.bean.TestBean;

/**
 * Created by wangzi on 2017-07-26.
 */
public interface TestMapper {
    TestBean selectByPrimaryKey(Integer id);
}
