package com.su.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Classname ViewCircularDepend
 * @Description TODO
 * @Date 2020/12/6 12:22
 * @Created by SGZ
 */

public class ViewCircularDepend {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
       // context
      /* context.getBean("a");
       context.getBean("b");*/
    }
}
