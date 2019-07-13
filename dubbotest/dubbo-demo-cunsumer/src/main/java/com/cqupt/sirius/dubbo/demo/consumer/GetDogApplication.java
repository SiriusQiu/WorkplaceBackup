package com.cqupt.sirius.dubbo.demo.consumer;

import com.cqupt.sirius.dubbo.demo.api.GetDog;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetDogApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        GetDog getDog = (GetDog) context.getBean("getDogImpl");
        System.out.println(getDog.getDog("hello"));
    }
}
