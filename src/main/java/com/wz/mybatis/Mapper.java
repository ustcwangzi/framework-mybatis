package com.wz.mybatis;

import com.wz.bean.TestBean;

/**
 * Created by wangzi on 2017-07-27.
 */
public interface Mapper {
    TestBean selectByPrimaryKey(Integer id);
}
