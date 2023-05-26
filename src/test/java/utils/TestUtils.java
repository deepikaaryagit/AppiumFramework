package utils;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestUtils {

	public static final long WAIT = 30;

	public static HashMap<String, String> parseStringXML(InputStream file) {
		HashMap<String, String> stringMap = new HashMap<>();
		try {
			// Create a DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Parse the XML file and create a Document object
			Document document = builder.parse(file);
			Element rootElement = document.getDocumentElement();
			NodeList nList = document.getElementsByTagName("string");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringMap;

	}

}
