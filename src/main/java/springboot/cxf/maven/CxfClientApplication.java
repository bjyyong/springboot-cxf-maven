package springboot.cxf.maven;


import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import springboot.cxf.maven.entities.Student;
import springboot.cxf.maven.services.MsgService;
import springboot.cxf.maven.services.RequestMsgService;
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
        
        factory.setServiceClass(MsgService.class);
        factory.setAddress("http://localhost:8088/services/msgService?wsdl");
        MsgService service3 = (MsgService) factory.create();
        String xmlStr="<?xml version='1.0' encoding='UTF-8' standalone='no'?><root><user><id>1</id><name>张三</name><sex>男</sex></user></root>";
		String respMsg = service3.getResponseMsg(xmlStr);
        System.out.println("响应结果："+respMsg);
        
        factory.setServiceClass(RequestMsgService.class);
        factory.setAddress("http://localhost:8088/services/requestMsgService?wsdl");
        RequestMsgService service4 = (RequestMsgService) factory.create();
        String requestMsg="<Service><Service_Header><service_id>200502069999</service_id><trans_sn>sdfsdf</trans_sn><key_label>xx</key_label></Service_Header><Service_Body><request><id>1</id></request></Service_Body></Service>";
		String responseMsg = service4.getResponseMsg(requestMsg);
        System.out.println("响应报文："+responseMsg);
    }
    
}
