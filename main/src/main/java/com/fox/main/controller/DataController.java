package com.fox.main.controller;

import com.fox.main.model.CRM;
import com.fox.main.model.Case;
import com.fox.main.model.DataFetcher;
import com.fox.main.model.ReducedCases;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@EnableWebMvc
@SuppressWarnings("unused")
public class DataController {
    private String formatDuration(long duration) {
        return String.format("%ddays", duration / (3600 * 24));
    }

    private String createHeaders(List<String> headers) {
        StringBuilder html = new StringBuilder()
                .append("<tr>");
        headers.forEach(header -> html.append("<th>").append(header).append("</th>"));
        html.append("</tr>");
        return html.toString();
    }

    private String createCell(Object data) {
        return "<td>" + data.toString() + "</td>";
    }

    private String createCases(List<Case> cases) {
        StringBuilder html = new StringBuilder();
        cases.forEach(caseObject -> {
                    String timeToClose = "-";
                    String timeSinceModified = "-";
                    try {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                        long duration;
                        if (caseObject.status == Case.CaseStatus.CLOSED) {
                            duration = simpleDateFormat.parse(caseObject.modificationDate).getTime() -
                                    simpleDateFormat.parse(caseObject.creationDate).getTime();
                            timeToClose = formatDuration(duration);
                        } else {
                            duration = new Timestamp(System.currentTimeMillis()).getTime() -
                                    simpleDateFormat.parse(caseObject.modificationDate).getTime();
                            timeSinceModified = formatDuration(duration);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    html.append("<tr>")
                            .append(createCell(caseObject.caseId))
                            .append(createCell(caseObject.creationDate))
                            .append(createCell(caseObject.status))
                            .append(createCell(caseObject.customerId))
                            .append(createCell(timeToClose))
                            .append(createCell(timeSinceModified));
                }
        );
        return html.toString();
    }

    private String createReducedCases(List<ReducedCases> data) {
        StringBuilder html = new StringBuilder();
        data.forEach((reducedCase) -> {
            html.append("<tr>")
                    .append("<td>")
                    .append(reducedCase.errorCode)
                    .append("</td>")
                    .append("<td>")
                    .append(reducedCase.provider)
                    .append("</td>")
                    .append("<td>");
            reducedCase.products.forEach(product -> html.append("<div>")
                    .append(product)
                    .append("</div>")
            );
            html.append("</td>")
                    .append("<td>")
                    .append(reducedCase.openCasesCount)
                    .append("</td>")
                    .append("<td>")
                    .append("<table>")
                    .append(createHeaders(List.of(
                            "Case ID",
                            "Created at",
                            "Status",
                            "Customer ID",
                            "Time to close",
                            "Time since modified")))
            .append(createCases(reducedCase.cases));
            html.append("</table>")
                    .append("</td>")
                    .append("</tr>");
        });
        return html.toString();
    }

    @RequestMapping(path = "/myAggregatedHub", method = RequestMethod.GET)
    @SuppressWarnings("unused")
    public String getAggregatedCases() {
        List<ReducedCases> data = DataFetcher.fetch(List.of(CRM.BANANA, CRM.STRAWBERRY));
        return "<html>" +
                "<body>" +
                "<table style=\"width:100%\">" +
                createHeaders(List.of(
                        "Error code",
                        "Provider",
                        "Product",
                        "Number of support cases",
                        "List of all support cases")) +
                createReducedCases(data) +
                "</table>" +
                "</body>" +
                "</html>";
    }
}