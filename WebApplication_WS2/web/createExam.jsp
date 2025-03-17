<%-- 
    Document   : createExam
    Created on : 17-Mar-2025, 08:10:29
    Author     : baothy2004
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List, dto.ExamCategoryDTO, dao.ExamCategoriesDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Exam</title>
        <link rel="stylesheet" href="assets/css/createExam.css">
    </head>
    <body>
        <div class="header-container">
            <jsp:include page="header.jsp"/>
        </div>

        <%
            ExamCategoriesDAO categoryDAO = new ExamCategoriesDAO();
            List<ExamCategoryDTO> categories = categoryDAO.getAllCategories();
        %>

        <div class="container">
            <h2>Create Exam</h2>
            <form action="CreateExamServlet" method="post">
                <div class="form-group">
                    <label for="title">Title:</label> 
                    <input type="text" id="title" name="title" required>
                </div>

                <div class="form-group">
                    <label for="subject">Subject:</label> 
                    <input type="text" id="subject" name="subject" required>
                </div>

                <div class="form-group">
                    <label for="categoryID">Category:</label>
                    <select id="categoryID" name="categoryID">
                        <% for (ExamCategoryDTO category : categories) { %>
                            <option value="<%= category.getCategoryID() %>"><%= category.getCategoryName() %></option>
                        <% } %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="totalMarks">Total Marks:</label> 
                    <input type="number" id="totalMarks" name="totalMarks" required>
                </div>

                <div class="form-group">
                    <label for="duration">Duration (min):</label> 
                    <input type="number" id="duration" name="duration" required>
                </div>

                <input type="submit" value="Create Exam" class="btn">
            </form>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>
