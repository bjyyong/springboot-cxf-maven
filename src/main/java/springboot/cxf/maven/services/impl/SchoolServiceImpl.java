package springboot.cxf.maven.services.impl;

import org.springframework.stereotype.Service;

import springboot.cxf.maven.services.SchoolService;

import javax.jws.WebService;

@Service
@WebService(serviceName = "SchoolService", // 服务名
		targetNamespace = "http://impl.services.maven.cxf.springboot", // 实现类包名倒写
		endpointInterface = "springboot.cxf.maven.services.SchoolService") // 接口的全路径
public class SchoolServiceImpl implements SchoolService {

	@Override
	public String getSchoolName() {
		return "XX大学";
	}

}
