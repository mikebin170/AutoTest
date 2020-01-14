package com.myTest.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;

public class Friend {

    private String name;
    private int age;
    private String address;

    public Friend() {
    }

    public Friend(String name,int age,String address) {
        this.name = name;
        this.age=age;
        this.address=address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}