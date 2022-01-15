package ru.job4j.ood.srp.report;

import java.util.function.Predicate;

public class ReportAccountingDepartment implements Report {

    private final Store store;

    public ReportAccountingDepartment(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {

        StringBuilder report = new StringBuilder();
        report.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            report.append(System.lineSeparator()).append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(getSalaryInRubles(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return report.toString();
    }

    private Double getSalaryInRubles(double sal) {
        return sal * 76.23;
    }
}
