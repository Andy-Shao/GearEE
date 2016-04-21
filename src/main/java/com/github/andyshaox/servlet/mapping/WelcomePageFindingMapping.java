package com.github.andyshaox.servlet.mapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.github.andyshao.data.structure.Bitree;
import com.github.andyshao.lang.StringOperation;
import com.github.andyshao.proxy.DynamicPF;
import com.github.andyshao.proxy.ProxyFactory;
import com.github.andyshao.reflect.MethodOperation;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 25, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class WelcomePageFindingMapping implements FindingMapping {
    static class MySAXHandler extends DefaultHandler {
        private boolean isTarget = false;
        private final List<String> memory;

        public MySAXHandler(List<String> memory) {
            this.memory = memory;
        }

        @Override
        public void characters(char[] ch , int start , int length) throws SAXException {
            if (this.isTarget) this.memory.add(new String(ch , start , length));
            super.characters(ch , start , length);
        }

        @Override
        public void endElement(String uri , String localName , String qName) throws SAXException {
            if (qName.equals("welcome-file")) this.isTarget = false;
            super.endElement(uri , localName , qName);
        }

        @Override
        public void startElement(String uri , String localName , String qName , Attributes attributes) throws SAXException {
            if (qName.equals("welcome-file")) this.isTarget = true;
            super.startElement(uri , localName , qName , attributes);
        }

    }

    private FindingMapping target = (conf , req , resp , bitree) -> null;
    private String webXmlPath = "/WEB-INF/web.xml";

    List<String> findWelcomeList(ServletConfig config) {
        List<String> result = new ArrayList<>();
        SAXParserFactory saxfac = SAXParserFactory.newInstance();
        try (InputStream is = config.getServletContext().getResourceAsStream(this.webXmlPath)) {
            SAXParser saxParser = saxfac.newSAXParser();
            saxParser.parse(is , new MySAXHandler(result));
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Mapping search(ServletConfig config , HttpServletRequest request , HttpServletResponse response , Bitree<Mapping> bitree) throws ServletException , IOException {
        Mapping mapping = this.target.search(config , request , response , bitree);
        String url = StringOperation.replaceFirst(request.getRequestURI() , request.getContextPath() , "");
        if (url.equals("") || url.equals("/")) for (String str : this.findWelcomeList(config))
            if (mapping == null) {
                String methodName = ProxyFactory.buildMethodKey(MethodOperation.getMethod(HttpServletRequest.class , "getRequestURI"));
                DynamicPF<HttpServletRequest> pf = (target , method , args) -> {
                    if (methodName.equals(ProxyFactory.buildMethodKey(method))) return request.getRequestURI() + str;
                    return method.invoke(request , args);
                };
                mapping = this.target.search(config , pf.getProxy(request) , response , bitree);
            } else break;
        return mapping;
    }

    public void setTarget(FindingMapping target) {
        this.target = target;
    }

    public void setWebXmlPath(String webXmlPath) {
        this.webXmlPath = webXmlPath;
    }
}
