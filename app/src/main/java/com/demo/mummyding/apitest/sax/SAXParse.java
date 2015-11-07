package com.demo.mummyding.apitest.sax;

import com.demo.mummyding.apitest.model.PolicyBean;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by mummyding on 15-11-7.
 */
public class SAXParse {
    public static List<PolicyBean> items;
    public static List<PolicyBean> parse(InputStream is) throws ParserConfigurationException, SAXException, IOException {
        XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        SAXHandler saxHandler = new SAXHandler();
        xmlReader.setContentHandler(saxHandler);
        xmlReader.parse(new InputSource(is));
        items = saxHandler.getItems();
        return items;
    }
}
