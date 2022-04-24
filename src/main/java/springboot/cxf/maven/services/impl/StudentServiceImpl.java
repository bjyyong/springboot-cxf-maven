package springboot.cxf.maven.services.impl;

import org.springframework.stereotype.Service;

import springboot.cxf.maven.entities.Student;
import springboot.cxf.maven.services.StudentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

@Service
@WebService(serviceName = "StudentService", // 服务名
		targetNamespace = "http://impl.services.maven.cxf.springboot", // 实现类包名倒写
		endpointInterface = "springboot.cxf.maven.services.StudentService") // 接口的全路径
public class StudentServiceImpl implements StudentService {

	@Override
	public Integer getStudentAge(String name) {
		return new Student(name, 18, "男").getAge();
	}

	@Override
	public String getStudentName() {
		return "斗战神佛";
	}

	@Override
	public Student getOneStudentInfo() {
		Student stu = new Student("紫霞仙子", 18, "女");
		return stu;
	}

	@Override
	public List<Student> getAllStudentsInfo() {
		List<Student> stus = new ArrayList<>();
		Student stu1 = new Student("唐三藏", 20, "男");
		Student stu2 = new Student("孙悟空", 20, "男");
		Student stu3 = new Student("猪八戒", 20, "男");
		Student stu4 = new Student("沙和尚", 20, "男");
		stus.add(stu1);
		stus.add(stu2);
		stus.add(stu3);
		stus.add(stu4);
		return stus;
	}

	@Override
	public Map<String, Object> getSchoolInfo() {
		Map<String, Object> school = new HashMap<>();
		school.put("name", "XX大学");
		school.put("location", "广东");
		return school;
	}
	
}
