package springboot.cxf.maven.services;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import springboot.cxf.maven.entities.Student;

@WebService(targetNamespace = "http://services.maven.cxf.springboot") // 接口包名倒着写
public interface StudentService {

	@WebMethod
	public Integer getStudentAge(@WebParam(name = "name") String name);

	@WebMethod
	public String getStudentName();

	@WebMethod
	public Student getOneStudentInfo();

	@WebMethod
	public List<Student> getAllStudentsInfo();

	@WebMethod
	public Map<String, Object> getSchoolInfo();
	
}
