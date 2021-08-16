package org.example.strategy;

import org.example.Constants;
import org.example.Parser;

import java.io.File;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class XmlParser implements Parser {
    public String parser(File file) throws Exception {
        return "我是XML解析方式";
    }

    public String getType() {
        return Constants.XML_PARSER;
    }
}
