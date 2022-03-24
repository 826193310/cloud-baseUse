package com.su.entity;

/**
 * @Classname A
 * @Description 演示循环依赖
 * @Date 2020/12/6 12:14
 * @Created by SGZ
 */
public class A {

    private B b;

    public A() {
        System.out.println("A --- CREATE --- SUCCESS!");
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }
}
