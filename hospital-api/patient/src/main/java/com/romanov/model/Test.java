package com.romanov.model;

import lombok.Data;

@Data
public class Test {

    private String name;

    public Test(String name)
    {
        this.name = name;
    }

//    public String getName()
//    {
//        return this.name;
//    }
//
//    public void setName(String name)
//    {
//        this.name = name;
//    }

    private Test() {}

}
