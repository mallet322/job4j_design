package ru.job4j.ood.srp.report;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {

    private final Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder report = new StringBuilder();
        report.append("Name; Salary;");
        List<Employee> employees = getSortingEmployeesList(filter);
        for (Employee employee : employees) {
            report.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return report.toString();
    }

    private List<Employee> getSortingEmployeesList(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        Collections.sort(employees);
        return employees;
    }

}
