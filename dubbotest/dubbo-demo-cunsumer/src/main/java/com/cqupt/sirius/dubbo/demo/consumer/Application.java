package com.cqupt.sirius.dubbo.demo.consumer;

import com.cqupt.sirius.dubbo.demo.api.SimpleService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        SimpleService ss = (SimpleService) context.getBean("simpleImpl");
        ss.sayHello();
    }
}
