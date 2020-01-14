package com.myTest.xml;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//修改根名称@XmlRootElement(name="students")
//修改属性名称@XmlElement(name="student")
//@XmlAttribute将age字段变成student的一个属性
@XmlRootElement(name="students")
public class Students {

    private List<Student> students;
    
    private String country;

    public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement(name="student")
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    public static void main(String[] args) {
    	JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Students.class);
			Marshaller ms = jc.createMarshaller();  
	        Students st = new Students(); 
	        st.setCountry("中国");
	        ms.marshal(st, System.out);  
		} catch (JAXBException e) {
			e.printStackTrace();
		}  
        
	}
}
