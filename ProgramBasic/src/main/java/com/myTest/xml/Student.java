package com.myTest.xml;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Student {
    private String name;
    private int age;
    private String sex;
    private Date birthday;
    private String address;
    private List<Friend> friends;

    public Student() {
    }

    public Student(String name, int age, String sex, Date birthday, String address) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //将age字段变成student的一个属性
    @XmlAttribute
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //指定用<friends>标签包围，子标签为<friend>
    @XmlElement(name="friend")
    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}