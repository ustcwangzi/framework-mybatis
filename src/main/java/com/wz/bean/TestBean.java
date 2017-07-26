package com.wz.bean;

/**
 * 测试实体
 * Created by wangzi on 2017-07-26.
 */
public class TestBean {
    private Integer id;
    private String name;

    public TestBean() {
    }

    public TestBean(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
