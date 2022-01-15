package ru.job4j.ood.srp.report;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class ReportHRTest {

    @Test
    public void whenGenerateReportForHRDepartment() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Petr", now, now, 510);
        Employee worker2 = new Employee("Roman", now, now, 1);
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        Report report = new ReportHR(store);
        String ln = System.lineSeparator();
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;").append(ln)
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";").append(ln)
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";").append(ln)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";");
        Assert.assertThat(report.generate(em -> true), Matchers.is(expect.toString()));
    }
}