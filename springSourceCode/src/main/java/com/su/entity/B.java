package com.su.entity;

/**
 * @Classname B
 * @Description 演示循环依赖
 * @Date 2020/12/6 12:16
 * @Created by SGZ
 */
public class B {

    private A a;

    public B() {
        System.out.println("B --- CREATE --- SUCCESS!");
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }
}
