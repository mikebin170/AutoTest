package com.myTest.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
//Marshaller写如xml文件
//Unmarshaller读取xml文件中的属性
public class JAXBTest {

    /**
     * 将java对象转换成xml，并用注解指定生成规则，是生成属性还是生成节点
     * @throws Exception
     */
    public static void testMarshaller() throws Exception{
        //获得转换的上下文对象
        JAXBContext context = JAXBContext.newInstance(Students.class);
        //获得Marshaller对象
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //将java对象进行转换成xml
        Students students=new Students();
        List<Student> list=new ArrayList<Student>();
        //第一个Student
        Student student1=new Student("张三",19,"男",new Date(),"北京市怀柔区");
        List<Friend> friends1=new ArrayList<Friend>();
        Friend f11=new Friend("李四",20,"北京市新城区");
        friends1.add(f11);
        Friend f12=new Friend("王五",18,"北京市朝阳区");
        friends1.add(f12);
        student1.setFriends(friends1);
        list.add(student1);
        // 第二个Student
        Student student2 = new Student("李四", 18, "男", new Date(), "北京市怀柔区");
        List<Friend> friends2 = new ArrayList<Friend>();
        Friend f21 = new Friend("张三", 20, "北京市新城区");
        friends2.add(f21);
        Friend f22 = new Friend("赵六", 18, "北京市朝阳区");
        friends2.add(f22);
        student2.setFriends(friends2);
        list.add(student2);
        //添加到Students
        students.setStudents(list);
        
        //打印到控制台
        FileWriter writer=new FileWriter(new File("students.xml"));
        marshaller.marshal(students, writer);
        marshaller.marshal(students, System.out);
    }
    
    /**
     * 读取xml文档，并将xml文档反序列化为java对象
     * @throws Exception
     */
    public static void testUnMarshaller() throws Exception{
        JAXBContext context = JAXBContext.newInstance(Students.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File("ProgramBasic/src/main/resources/config.xml");
        Students students = (Students) unmarshaller.unmarshal(file);
        System.out.println("country---"+students.getCountry());
        List<Student> list = students.getStudents();
        for(Student student:list){
            System.out.println("name:"+student.getName());
            System.out.println("age:"+student.getAge());
            System.out.println("address:"+student.getAddress());
            System.out.println("-----------------------");
        }
    }
    
    public static void main(String[] args) {
    	try {
			//testMarshaller();
			testUnMarshaller();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}