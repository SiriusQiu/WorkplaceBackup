package com.cqupt.sirius.dubbo.demo.api;

import java.io.Serializable;

public class Dog implements Serializable {
    private String name;

    public Dog(String name) {
        this.name = name;
    }
    public String toString(){
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
