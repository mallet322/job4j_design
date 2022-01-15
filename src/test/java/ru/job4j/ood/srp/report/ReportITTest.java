package ru.job4j.ood.srp.report;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class ReportITTest {

    @Test
    public void whenGenerateHTMLReportForITDepartment() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportIT(store);
        String ln = System.lineSeparator();
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>").append(ln)
                .append("<html lang=\"en\">").append(ln)
                .append("<head>").append(ln)
                .append("<title>Report for IT department</title>").append(ln)
                .append("</head>").append(ln)
                .append("<body>").append(ln)
                .append("<table>").append(ln)
                .append("<tr>").append(ln)
                .append("<th>Name</th>").append(ln)
                .append("<th>Hired</th>").append(ln)
                .append("<th>Fired</th>").append(ln)
                .append("<th>Salary</th>").append(ln)
                .append("</tr>").append(ln)
                .append("<tr>").append(ln)
                .append("<th>").append(worker.getName()).append("</th>").append(ln)
                .append("<th>").append(worker.getHired()).append("</th>").append(ln)
                .append("<th>").append(worker.getFired()).append("</th>").append(ln)
                .append("<th>").append(worker.getSalary()).append("</th>").append(ln)
                .append("</tr>").append(ln)
                .append("</table>").append(ln)
                .append("</body>").append(ln)
                .append("</html>");
        Assert.assertThat(report.generate(em -> true), Matchers.is(expect.toString()));
    }
}