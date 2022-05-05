package springboot.cxf.maven.services.impl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import springboot.cxf.maven.services.MsgService;
import springboot.cxf.maven.util.XMLHandler;

@Service
@WebService(serviceName = "MsgService", // 服务名
		targetNamespace = "http://impl.services.maven.cxf.springboot", // 实现类包名倒写
		endpointInterface = "springboot.cxf.maven.services.MsgService") // 接口的全路径
public class MsgServiceImpl implements MsgService {

	@Override
	public String getResponseMsg(String xmlStr) {
        List<Map<String, Object>> list = XMLHandler.parseXML(xmlStr);
        return list.toString();
	}

}
