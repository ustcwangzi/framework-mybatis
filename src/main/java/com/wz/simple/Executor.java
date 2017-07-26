package com.wz.simple;

/**
 * Created by wangzi on 2017-07-26.
 */
public interface Executor {
    <E> E query(String sql, Object parameter);
}
