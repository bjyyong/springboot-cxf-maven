package springboot.cxf.maven.entities;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;// 反序列化用的，可以不写

	private String name; // 姓名
	private Integer age; // 年龄
	private String gender; // 性别

	
	public Student() {
		super();
	}

	public Student(String name, Integer age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}

}
