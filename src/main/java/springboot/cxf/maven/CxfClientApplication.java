package springboot.cxf.maven;


import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import springboot.cxf.maven.entities.Student;
import springboot.cxf.maven.services.SchoolService;
import springboot.cxf.maven.services.StudentService;

public class CxfClientApplication {
    public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.setServiceClass(StudentService.class);
        factory.setAddress("http://localhost:8088/services/studentService?wsdl");
        StudentService service = (StudentService) factory.create();
        String name = service.getStudentName();
        System.out.println("student name:"+name);
        Student stu = service.getOneStudentInfo();
        System.out.println("学生信息："+stu);
        
        factory.setServiceClass(SchoolService.class);
        factory.setAddress("http://localhost:8088/services/schoolService?wsdl");
        SchoolService service2 = (SchoolService) factory.create();
        String name1 = service2.getSchoolName();
        System.out.println("学校名字："+name1);
    }
    
}
