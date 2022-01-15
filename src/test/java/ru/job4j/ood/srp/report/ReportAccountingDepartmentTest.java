package ru.job4j.ood.srp.report;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class ReportAccountingDepartmentTest {

    @Test
    public void whenGenerateReportForHRDepartment() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report report = new ReportAccountingDepartment(store);
        double salary = worker.getSalary() * 76.23;
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(salary).append(";")
                .append(System.lineSeparator());
        Assert.assertThat(report.generate(em -> true), Matchers.is(expect.toString()));
    }

}