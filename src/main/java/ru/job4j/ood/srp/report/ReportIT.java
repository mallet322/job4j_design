package ru.job4j.ood.srp.report;

import java.util.function.Predicate;

public class ReportIT implements Report {

    private final Store store;

    public ReportIT(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder html = new StringBuilder();
        String ln = System.lineSeparator();
        html.append("<!DOCTYPE html>").append(ln);
        html.append("<html lang=\"en\">").append(ln);
        html.append("<head>").append(ln);
        html.append("<title>Report for IT department</title>").append(ln);
        html.append("</head>").append(ln);
        html.append("<body>").append(ln);
        html.append("<table>").append(ln);
        html.append("<tr>").append(ln);
        html.append("<th>Name</th>").append(ln);
        html.append("<th>Hired</th>").append(ln);
        html.append("<th>Fired</th>").append(ln);
        html.append("<th>Salary</th>").append(ln);
        html.append("</tr>").append(ln);
        html.append("<tr>").append(ln);
        for (Employee employee : store.findBy(filter)) {
            html.append("<th>").append(employee.getName()).append("</th>").append(ln)
                    .append("<th>").append(employee.getHired()).append("</th>").append(ln)
                    .append("<th>").append(employee.getFired()).append("</th>").append(ln)
                    .append("<th>").append(employee.getSalary()).append("</th>").append(ln);
        }
        html.append("</tr>").append(ln);
        html.append("</table>").append(ln);
        html.append("</body>").append(ln);
        html.append("</html>");
        return html.toString();
    }

}
