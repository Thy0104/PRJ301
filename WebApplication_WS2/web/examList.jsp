<%-- 
    Document   : examList
    Created on : 17-Mar-2025, 02:08:29
    Author     : baothy2004
--%>

<%@page import="dto.QuestionDTO"%>
<%@page import="dao.QuestionDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, dto.ExamDTO, dao.ExamDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <%
            ExamDAO examDAO = new ExamDAO();
            List<ExamDTO> exams = examDAO.getAllExams();
        %>

        <h2>Exam List</h2>
        <table border="1">
            <tr>
                <th>Title</th>
                <th>Subject</th>
                <th>Marks</th>
                <th>Duration</th>
                <th>Action</th>
            </tr>
            <% for (ExamDTO exam : exams) { %>
                <tr>
                    <td><%= exam.getExamTitle() %></td>
                    <td><%= exam.getSubject() %></td>
                    <td><%= exam.getTotalMarks() %></td>
                    <td><%= exam.getDuration() %> min</td>
                    <td><a href="examDetails.jsp?examID=<%= exam.getExamID() %>">View</a></td>
                </tr>
            <% } %>
        </table>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
