package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlReadTest {
    static class MyXMLHandelr extends DefaultHandler {
        private boolean isTarget = false;

        @Override
        public void characters(char[] ch , int start , int length) throws SAXException {
            if (this.isTarget) Assert.assertThat(new String(ch, start , length) ,
                Matchers.is("index.html"));
            super.characters(ch , start , length);
        }

        @Override
        public void endElement(String uri , String localName , String qName) throws SAXException {
            if (qName.equals("welcome-file")) this.isTarget = false;
            super.endElement(uri , localName , qName);
        }

        @Override
        public void startElement(String uri , String localName , String qName , Attributes attributes)
            throws SAXException {
            if (qName.equals("welcome-file")) this.isTarget = true;
            super.startElement(uri , localName , qName , attributes);
        }

    }

    @Test
    public void test() throws IOException , ParserConfigurationException , SAXException {
        SAXParserFactory saxfac = SAXParserFactory.newInstance();
        try (InputStream is = ClassLoader.getSystemResourceAsStream("com/github/andyshaox/servlet/mapping/web.xml")) {
            SAXParser saxParser = saxfac.newSAXParser();
            saxParser.parse(is , new MyXMLHandelr());
        }
    }
}
