package com.cqupt.sirius.dubbo.demo.service;

import com.cqupt.sirius.dubbo.demo.api.Dog;
import com.cqupt.sirius.dubbo.demo.api.GetDog;

public class GetDogImpl implements GetDog {
    @Override
    public Dog getDog(String name) {
        Dog dog = new Dog(name);
        System.out.println(dog);
        dog.setName("world");
        return dog;
    }
}
