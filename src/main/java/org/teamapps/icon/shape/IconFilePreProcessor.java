package org.teamapps.icon.shape;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

public class IconFilePreProcessor {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException, XPathExpressionException {
		File[] icons = new File("icons").listFiles((dir, name) -> name.endsWith(".svg"));
		for (File icon : icons) {
			DocumentBuilderFactory factory =
					DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(icon);

			Node valueElement = doc.importNode(builder.parse(new InputSource(new StringReader("<defs>\n" +
																							  "    <filter\n" +
																							  "       style=\"color-interpolation-filters:sRGB;\"\n" +
																							  "       inkscape:label=\"Drop Shadow\"\n" +
																							  "       id=\"shadow\"\n" +
																							  "       x=\"-0.097\"\n" +
																							  "       y=\"-0.097\"\n" +
																							  "       width=\"1.204\"\n" +
																							  "       height=\"1.204\">\n" +
																							  "      <feFlood\n" +
																							  "         flood-opacity=\"0.603922\"\n" +
																							  "         flood-color=\"rgb(0,0,0)\"\n" +
																							  "         result=\"flood\"\n" +
																							  "         id=\"feFlood1703\" />\n" +
																							  "      <feComposite\n" +
																							  "         in=\"flood\"\n" +
																							  "         in2=\"SourceGraphic\"\n" +
																							  "         operator=\"in\"\n" +
																							  "         result=\"composite1\"\n" +
																							  "         id=\"feComposite1705\" />\n" +
																							  "      <feGaussianBlur\n" +
																							  "         in=\"composite1\"\n" +
																							  "         stdDeviation=\"3\"\n" +
																							  "         result=\"blur\"\n" +
																							  "         id=\"feGaussianBlur1707\" />\n" +
																							  "      <feOffset\n" +
																							  "         dx=\"1\"\n" +
																							  "         dy=\"1\"\n" +
																							  "         result=\"offset\"\n" +
																							  "         id=\"feOffset1709\" />\n" +
																							  "      <feComposite\n" +
																							  "         in=\"SourceGraphic\"\n" +
																							  "         in2=\"offset\"\n" +
																							  "         operator=\"over\"\n" +
																							  "         result=\"composite2\"\n" +
																							  "         id=\"feComposite1711\" />\n" +
																							  "    </filter>\n" +
																							  "</defs>\n"))).getDocumentElement(), true);
			doc.getDocumentElement().appendChild(valueElement);

			var nodeList = (NodeList) XPathFactory.newInstance().newXPath().compile("//*").evaluate(doc, XPathConstants.NODESET);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node item = nodeList.item(i);
				if (item.getNodeName().equals("sodipodi:namedview")){
					item.getParentNode().removeChild(item);
				}
				NamedNodeMap attributes = item.getAttributes();
				for (int j = 0; j < attributes.getLength(); j++) {
					Attr attribute = (Attr) attributes.item(j);
					if (attribute.getNodeName().equals("xmlns:inkscape") || attribute.getNodeName().startsWith("xmlns:sodipodi")
							|| attribute.getNodeName().startsWith("inkscape:") || attribute.getNodeName().startsWith("sodipodi:")){
						((Element) item).removeAttributeNode(attribute);
						j--;
					}
				}
			}

			var node = (Element) XPathFactory.newInstance().newXPath().compile("//*[@id='shape']").evaluate(doc, XPathConstants.NODE);
			node.setAttribute("style", "fill:@fillColor@;stroke-width:@strokeWidth@;filter:@filter@;stroke:@strokeColor@;transform-origin:center;transform:@transform@");




			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(new StringReader("<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" +
																										  "  <xsl:output indent=\"yes\"/>\n" +
																										  "  <xsl:strip-space elements=\"*\"/>\n" +
																										  "\n" +
																										  "  <xsl:template match=\"@*|node()\">\n" +
																										  "	<xsl:copy>\n" +
																										  "	  <xsl:apply-templates select=\"@*|node()\"/>\n" +
																										  "	</xsl:copy>\n" +
																										  "  </xsl:template>\n" +
																										  "\n" +
																										  "</xsl:stylesheet>\n")));
			DOMSource source = new DOMSource(doc);
			FileWriter writer = new FileWriter("src/main/resources/org/teamapps/icon/shape/" + icon.getName());
			StreamResult result = new StreamResult(writer);
			transformer.transform(source, result);
		}
	}

}
