package com.myTest.hello;


import com.myTest.hello.Person;

public class Teacher extends Person {

	public Teacher() {
		super();
	}
	public Teacher(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void setName(String name) {
		super.setName(name);
	}

	@Override
	public int getAge() {
		return super.getAge();
	}

	@Override
	public void setAge(int age) {
		super.setAge(age);
	}

	@Override
	public void eat() {
		super.eat();
	}
}




