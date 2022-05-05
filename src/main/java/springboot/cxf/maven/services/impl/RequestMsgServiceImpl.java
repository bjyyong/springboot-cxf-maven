package springboot.cxf.maven.services.impl;

import java.io.StringReader;

import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import springboot.cxf.maven.services.RequestMsgService;
import springboot.cxf.maven.util.XMLHandler;

@Service
@WebService(serviceName = "RequestMsgService", // 服务名
		targetNamespace = "http://impl.services.maven.cxf.springboot", // 实现类包名倒写
		endpointInterface = "springboot.cxf.maven.services.RequestMsgService") // 接口的全路径
public class RequestMsgServiceImpl implements RequestMsgService {

	@Override
	public String getResponseMsg(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringReader sr = new StringReader(xmlStr);
            InputSource is = new InputSource(sr);
            Document doc = builder.parse(is);
            //
            Element rootElement = doc.getDocumentElement();
            //
            Node serviceHeader = rootElement.getElementsByTagName("Service_Header").item(0);
            //
            Element service_response = doc.createElement("service_response");
            Element status = doc.createElement("status");
            status.setTextContent("FAIL");
            Element code = doc.createElement("S0000000");
            code.setTextContent("sss");
            Element desc = doc.createElement("desc");
            desc.setTextContent("sdf");
            service_response.appendChild(status);
            service_response.appendChild(code);
            service_response.appendChild(desc);
            ((Element)serviceHeader).appendChild(service_response);
            
            //
            Node serviceBody = rootElement.getElementsByTagName("Service_Body").item(0);
            //
            Element response = doc.createElement("response");
            Element id = doc.createElement("id");
            id.setTextContent("1");
            Element name = doc.createElement("name");
            name.setTextContent("sss");
            Element age = doc.createElement("age");
            age.setTextContent("12");
            response.appendChild(id);
            response.appendChild(name);
            response.appendChild(age);
            ((Element)serviceBody).appendChild(response);
            
            return XMLHandler.toStringFromDoc(doc);
            		
        }catch(Exception e){
            e.printStackTrace();
        }
		return null;
    }

}
