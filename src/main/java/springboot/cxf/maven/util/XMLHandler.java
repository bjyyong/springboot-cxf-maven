package springboot.cxf.maven.util;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成xml格式的文件 解释xml格式的字符串
 */
public class XMLHandler {
	/**
	 * 创建xml
	 * 
	 * @return
	 */
	public static String createXML() {
		String xmlStr = "";
		// doc 的工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			document.setXmlVersion("1.0");
			//
			Element root = document.createElement("root");
			document.appendChild(root);
			//
			Element user = document.createElement("user");
			//
			Element id = document.createElement("id");
			id.setTextContent("1");
			user.appendChild(id);
			//
			Element name = document.createElement("name");
			name.setTextContent("张三");
			user.appendChild(name);
			//
			Element sex = document.createElement("sex");
			sex.setTextContent("男");
			user.appendChild(sex);
			//
			root.appendChild(user);
			//
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transFormer = transFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);

			// export string
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			transFormer.transform(domSource, new StreamResult(bos));
			xmlStr = bos.toString();
			System.out.println("输出结果" + xmlStr);
			// 通过输出流输出
			File file = new File("C:/work/user.xml");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			StreamResult xmlResult = new StreamResult(out);
			transFormer.transform(domSource, xmlResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlStr;
	}

	/*
	 * public static void main(String[] args) { createXML(); }
	 */
	/**
	 * 转化xml格式的字符串 <?xml version="1.0" encoding="UTF-8" standalone="no"?> <root>
	 * <user> <id>1</id> <name>张三</name> <sex>男</sex> </user> </root>
	 */
	public static List<Map<String, Object>> parseXML(String xmlStr) {
		List<Map<String, Object>> list = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			StringReader sr = new StringReader(xmlStr);
			InputSource is = new InputSource(sr);
			Document doc = builder.parse(is);
			//
			Element rootElement = doc.getDocumentElement();
			//
			NodeList users = rootElement.getElementsByTagName("user");
			//
			list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < users.getLength(); i++) {
				Node node = users.item(i);
				NodeList childs = node.getChildNodes();
				Map<String, Object> map = new HashMap<String, Object>();
				for (int j = 0; j < childs.getLength(); j++) {
					Node childNode = childs.item(j);
					String nodeName = childNode.getNodeName();
					String value = childNode.getTextContent();
					map.put(nodeName, value);

				}
				list.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * public static void main(String[] args) { String
	 * xmlStr="<?xml version='1.0' encoding='UTF-8' standalone='no'?><root><user><id>1</id><name>张三</name><sex>男</sex></user></root>"
	 * ; List<Map<String, Object>> list = parseXML(xmlStr);
	 * System.out.println("======"+list.toString()); }
	 */

	/*
	 * 把dom文件转换为xml字符串
	 */
	public static String toStringFromDoc(Document document) {
		String result = null;

		if (document != null) {
			StringWriter strWtr = new StringWriter();
			StreamResult strResult = new StreamResult(strWtr);
			TransformerFactory tfac = TransformerFactory.newInstance();
			try {
				javax.xml.transform.Transformer t = tfac.newTransformer();
				t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				t.setOutputProperty(OutputKeys.INDENT, "yes");
				t.setOutputProperty(OutputKeys.METHOD, "xml"); // xml, html,
				// text
				t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
				t.transform(new DOMSource(document.getDocumentElement()), strResult);
			} catch (Exception e) {
				System.err.println("XML.toString(Document): " + e);
			}
			result = strResult.getWriter().toString();
			try {
				strWtr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static String toStringFromDoc1(Document document) {
		String result = null;
		BufferedOutputStream bos = null;
		try {
			/**
			 * 解决DocType问题
			 */
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			DocumentType doctype = document.getDoctype();
			if (doctype != null) {
				String systemId = doctype.getSystemId();
				String publicId = doctype.getPublicId();
				if (systemId != null) {
					transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, systemId);
					transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, publicId);
				}
			}

			DOMSource source = new DOMSource(document);
			StringWriter strWtr = new StringWriter();
			StreamResult stream = new StreamResult(strWtr);
			transformer.transform(source, stream);
			result = stream.getWriter().toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}