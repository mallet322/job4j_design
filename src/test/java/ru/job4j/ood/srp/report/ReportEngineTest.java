package ru.job4j.ood.srp.report;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        String ln = System.lineSeparator();
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;").append(ln)
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";").append(ln);
        Assert.assertThat(engine.generate(em -> true), Matchers.is(expect.toString()));
    }

}
