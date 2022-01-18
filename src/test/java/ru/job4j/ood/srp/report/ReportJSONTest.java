package ru.job4j.ood.srp.report;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReportJSONTest {

    @Test
    public void whenGenerateReportToJSON() {
        MemStore store = new MemStore();
        Calendar now = new GregorianCalendar(2022, Calendar.JANUARY, 18);
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportJSON(store);
        String expected = "[{"
                + "\"name\":\"Ivan\","
                + "\"hired\":{"
                + "\"year\":2022,"
                + "\"month\":0,"
                + "\"dayOfMonth\":18,"
                + "\"hourOfDay\":0,"
                + "\"minute\":0,"
                + "\"second\":0},"
                + "\"fired\":{"
                + "\"year\":2022,"
                + "\"month\":0,"
                + "\"dayOfMonth\":18,"
                + "\"hourOfDay\":0,"
                + "\"minute\":0,"
                + "\"second\":0},"
                + "\"salary\":100.0"
                + "}]";
        String actual = report.generate(emp -> true);
        Assert.assertEquals(expected, actual);
    }

}