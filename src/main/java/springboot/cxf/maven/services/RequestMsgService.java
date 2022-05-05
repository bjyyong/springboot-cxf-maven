package springboot.cxf.maven.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://services.maven.cxf.springboot") // 接口包名倒着写
public interface RequestMsgService {

	@WebMethod
	public String getResponseMsg(@WebParam(name = "xmlStr")String xmlStr);
}
