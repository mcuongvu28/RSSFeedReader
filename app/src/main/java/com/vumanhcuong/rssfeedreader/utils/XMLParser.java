package com.vumanhcuong.rssfeedreader.utils;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XMLParser {
    private static XMLParser sInstance = null;

    private XMLParser() {
    }

    public static XMLParser getInstance() {
        if (sInstance == null) {
            sInstance = new XMLParser();
        }
        return sInstance;
    }

    public Document getDocument(String xml, String encoding) {
        Document document;
        try {
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            is.setEncoding(encoding);

            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage(), e);
            return null;
        }
        return document;
    }

    public String getString(Element item, String name) {
        NodeList nodes = item.getElementsByTagName(name);
        return this.getTextNodeValue(nodes.item(0));
    }

    private final String getTextNodeValue(Node node) {
        if (node == null || !node.hasChildNodes()) return "";

        Node child = node.getFirstChild();
        while (child != null) {
            short type = child.getNodeType();
            if (type == Node.TEXT_NODE || type == Node.CDATA_SECTION_NODE) {
                return child.getNodeValue();
            }
            child.getNextSibling();
        }
        return "";
    }
}
