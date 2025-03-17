<%-- 
    Document   : takeExam
    Created on : 17-Mar-2025, 08:05:17
    Author     : baothy2004
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, dto.QuestionDTO, dao.QuestionDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Take Exam</title>
        <link rel="stylesheet" href="assets/css/exam.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <div class="container">
            <h2>Take Exam</h2>
            
            <%
                String examIDParam = request.getParameter("examID");
                int examID = -1;
                List<QuestionDTO> questions = null;

                if (examIDParam != null && !examIDParam.isEmpty()) {
                    try {
                        examID = Integer.parseInt(examIDParam);
                        QuestionDAO questionDAO = new QuestionDAO();
                        questions = questionDAO.getQuestionsByExam(examID);
                    } catch (NumberFormatException e) {
                        examID = -1;
                    }
                }

                if (examID == -1 || questions == null || questions.isEmpty()) {
            %>
                <p style="color: red;">Invalid or missing Exam ID. Please go back and select an exam.</p>
                <a href="examList.jsp" class="btn">Back to Exams</a>
            <%
                } else {
            %>
                <form action="SubmitExamServlet" method="post">
                    <input type="hidden" name="examID" value="<%= examID %>">
                    <% int questionNumber = 1; %>
                    <% for (QuestionDTO q : questions) { %>
                        <div class="question">
                            <p><strong>Question <%= questionNumber++ %>:</strong> <%= q.getQuestionText() %></p>
                            <label><input type="radio" name="q<%= q.getQuestionID() %>" value="A" required> <%= q.getOptionA() %></label><br>
                            <label><input type="radio" name="q<%= q.getQuestionID() %>" value="B"> <%= q.getOptionB() %></label><br>
                            <label><input type="radio" name="q<%= q.getQuestionID() %>" value="C"> <%= q.getOptionC() %></label><br>
                            <label><input type="radio" name="q<%= q.getQuestionID() %>" value="D"> <%= q.getOptionD() %></label><br>
                        </div>
                    <% } %>
                    <button type="submit" class="btn">Submit Exam</button>
                </form>
            <%
                }
            %>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>