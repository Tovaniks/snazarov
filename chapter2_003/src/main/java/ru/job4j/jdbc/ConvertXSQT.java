package ru.job4j.jdbc;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;


/**
 * ConvertXSQT
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.07.10
 */
public class ConvertXSQT {

    private Files files = new Files();

    /**
     * Переводим XML из одного формата в другой
     *
     * @param source исходник
     * @param dest   конечный файл
     * @param schema шаблон конечного файла
     * @throws TransformerException
     */
    public void convert(File source, File dest, File schema) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(new ByteArrayInputStream(files.getDataFromFile(schema.getPath()).getBytes())));
        transformer.transform(new StreamSource(new ByteArrayInputStream(files.getDataFromFile(source.getPath()).getBytes())), new StreamResult(dest));
    }
}
