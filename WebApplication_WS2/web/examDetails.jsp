<%-- 
    Document   : examDetails
    Created on : 17-Mar-2025, 08:02:40
    Author     : baothy2004
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dto.ExamDTO, dao.ExamDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Details</title>
        <link rel="stylesheet" href="assets/css/exam.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="container">
            <h2>Exam Details</h2>

            <%
                String examIDParam = request.getParameter("examID");
                int examID = -1;
                ExamDTO exam = null;

                if (examIDParam != null && !examIDParam.isEmpty()) {
                    try {
                        examID = Integer.parseInt(examIDParam);
                        ExamDAO examDAO = new ExamDAO();
                        exam = examDAO.getExamByID(examID);
                    } catch (NumberFormatException e) {
                        examID = -1;
                    }
                }

                if (exam == null) {
            %>
                <p style="color: red;">Invalid or missing Exam ID. Please go back and select an exam.</p>
                <a href="examList.jsp" class="btn">Back to Exams</a>
            <%
                } else {
            %>
                <p><strong>Title:</strong> <%= exam.getExamTitle() %></p>
                <p><strong>Subject:</strong> <%= exam.getSubject() %></p>
                <p><strong>Total Marks:</strong> <%= exam.getTotalMarks() %></p>
                <p><strong>Duration:</strong> <%= exam.getDuration() %> min</p>
                <a href="takeExam.jsp?examID=<%= exam.getExamID() %>" class="btn">Take Exam</a>
            <%
                }
            %>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>