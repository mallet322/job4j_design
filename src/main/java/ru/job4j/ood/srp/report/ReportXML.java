package ru.job4j.ood.srp.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportXML implements Report {

    private static final Logger LOG = LoggerFactory.getLogger(ReportXML.class);

    private final Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var emp = new Employees(store.findBy(filter));
        String xml = "";
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter strWriter = new StringWriter()) {
                marshaller.marshal(emp, strWriter);
                xml = strWriter.getBuffer().toString();
            }
        } catch (Exception e) {
            LOG.error("Generate error:", e);
        }
        return xml;
    }

}
