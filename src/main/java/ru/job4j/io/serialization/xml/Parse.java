package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parse {

    private static final Logger LOG = LoggerFactory.getLogger(Parse.class);

    public static String serializeToXml(Person person, String fileName) {
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter strWriter = new StringWriter();
                 PrintWriter fileWriter = new PrintWriter(new BufferedOutputStream(new FileOutputStream(fileName)))) {
                marshaller.marshal(person, strWriter);
                xml = strWriter.getBuffer().toString();
                fileWriter.println(xml);
            }
        } catch (Exception e) {
            LOG.error("Error:", e);
        }
        return xml;
    }

    public static Person deserializeXml(String xml) {
        Person person = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            try (StringReader reader = new StringReader(xml)) {
                person = (Person) unmarshaller.unmarshal(reader);
            }
        } catch (Exception e) {
            LOG.error("Error:", e);
        }
        return person;
    }

}
