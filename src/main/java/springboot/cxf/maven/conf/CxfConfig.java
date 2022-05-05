package springboot.cxf.maven.conf;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springboot.cxf.maven.services.MsgService;
import springboot.cxf.maven.services.RequestMsgService;
import springboot.cxf.maven.services.SchoolService;
import springboot.cxf.maven.services.StudentService;

/**
 * Cxf 配置 ,等同于写XML文件，参照官网的demo
 */
@Configuration
public class CxfConfig {

	@Autowired
	private Bus bus; //必须有的

	@Autowired
	private StudentService studentService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private MsgService msgService;
	@Autowired
	private RequestMsgService requestMsgService;
	
	/**
	 * studentService
	 * 
	 * @return
	 */
	@Bean
	public Endpoint studentServiceEndpoint() {//发布studentService
		EndpointImpl studentServiceEndpoint = new EndpointImpl(bus, studentService);
		studentServiceEndpoint.publish("/studentService");
		return studentServiceEndpoint;
	}

	/**
	 * schoolService
	 * 
	 * @return
	 */
	@Bean
	public Endpoint schoolServiceEndpoint() {
		EndpointImpl schoolServiceEndpoint = new EndpointImpl(bus, schoolService);
		schoolServiceEndpoint.publish("/schoolService");
		return schoolServiceEndpoint;
	}
	
	/**
	 * msgService
	 * 
	 * @return
	 */
	@Bean
	public Endpoint msgServiceEndpoint() {
		EndpointImpl msgServiceEndpoint = new EndpointImpl(bus, msgService);
		msgServiceEndpoint.publish("/msgService");
		return msgServiceEndpoint;
	}
	
	/**
	 * msgService
	 * 
	 * @return
	 */
	@Bean
	public Endpoint requestMsgServiceEndpoint() {
		EndpointImpl requestMsgServiceEndpoint = new EndpointImpl(bus, requestMsgService);
		requestMsgServiceEndpoint.publish("/requestMsgService");
		return requestMsgServiceEndpoint;
	}
	
}
