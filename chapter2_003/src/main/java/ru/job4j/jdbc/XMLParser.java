package ru.job4j.jdbc;

import org.apache.log4j.BasicConfigurator;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * XMLParser
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.07.10
 */
public class XMLParser {

    private long sum = 0;

    /**
     * Возвращаем сумму
     *
     * @param file XML файл
     * @return сумма
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public long getSum(File file) throws ParserConfigurationException, SAXException, IOException {
        BasicConfigurator.configure();
        this.sum = 0;
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = parserFactory.newSAXParser();
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String namespace, String localName, String qName, Attributes attr) {
                if (qName.equals("entry")) {
                    sum += Integer.parseInt(attr.getValue("FIELD"));
                }
            }

        };
        saxParser.parse(file, handler);
        return this.sum;
    }
}
