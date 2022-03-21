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

			Node valueElement = doc.importNode(builder.parse(new InputSource(new StringReader("""
					<defs>
					    <filter
					       style="color-interpolation-filters:sRGB;"
					       inkscape:label="Drop Shadow"
					       id="shadow"
					       x="-0.097"
					       y="-0.097"
					       width="1.204"
					       height="1.204">
					      <feFlood
					         flood-opacity="0.603922"
					         flood-color="rgb(0,0,0)"
					         result="flood"
					         id="feFlood1703" />
					      <feComposite
					         in="flood"
					         in2="SourceGraphic"
					         operator="in"
					         result="composite1"
					         id="feComposite1705" />
					      <feGaussianBlur
					         in="composite1"
					         stdDeviation="3"
					         result="blur"
					         id="feGaussianBlur1707" />
					      <feOffset
					         dx="1"
					         dy="1"
					         result="offset"
					         id="feOffset1709" />
					      <feComposite
					         in="SourceGraphic"
					         in2="offset"
					         operator="over"
					         result="composite2"
					         id="feComposite1711" />
					    </filter>
					  </defs>
					"""))).getDocumentElement(), true);
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
			Transformer transformer = transformerFactory.newTransformer(new StreamSource(new StringReader("""
									<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
									  <xsl:output indent="yes"/>
									  <xsl:strip-space elements="*"/>
									
									  <xsl:template match="@*|node()">
										<xsl:copy>
										  <xsl:apply-templates select="@*|node()"/>
										</xsl:copy>
									  </xsl:template>
									
									</xsl:stylesheet>
					""")));
			DOMSource source = new DOMSource(doc);
			FileWriter writer = new FileWriter("src/main/resources/org/teamapps/icon/shape/" + icon.getName());
			StreamResult result = new StreamResult(writer);
			transformer.transform(source, result);
		}
	}

}
