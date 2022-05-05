package springboot.cxf.maven.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://services.maven.cxf.springboot") // 接口包名倒着写
public interface MsgService {
	
	
    //获取xml格式的数据
    /**
     * 根据传递的条件获取相应信息
     * xml的格式规范
     * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
     *     <root>
     *         <user>
     *             <id>1</id>
     *             <name>张三</name>
     *             <sex>男</sex>
     *         </user>
     *     </root>
     * 这里的WebParam必须指定，否则调用的时候返回null
     * @return
     */
	@WebMethod
	public String getResponseMsg(@WebParam(name = "xmlStr")String xmlStr);


}
