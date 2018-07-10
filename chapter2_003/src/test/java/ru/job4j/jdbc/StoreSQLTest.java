package ru.job4j.jdbc;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class StoreSQLTest {

    @Test
    public void when50then1275() throws TransformerException, IOException, SAXException, ParserConfigurationException {
        File from = new File("src//main//resources//second.xml");
        File dest = new File("src//main//resources//thierd.xml");
        File schema = new File("src//main//resources//schema.xsl");
        StoreSQL sql = new StoreSQL(new Config());
        sql.generate(50);
        StoreXML xml = new StoreXML(from);
        xml.save(sql.getEntry());
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(from, dest, schema);
        XMLParser parser = new XMLParser();
        long result = parser.getSum(dest);
        assertThat(result, is(1275L));
    }

}