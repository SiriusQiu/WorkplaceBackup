package com.cqupt.sirius.dubbo.demo.service;

import com.cqupt.sirius.dubbo.demo.api.SimpleService;

public class SimpleImpl implements SimpleService {
    @Override
    public void sayHello() {
        System.out.println("Hello world!");
    }
}
