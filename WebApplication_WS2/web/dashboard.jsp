<%-- 
    Document   : dashboard
    Created on : 17-Mar-2025, 02:06:07
    Author     : baothy2004
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.UserDTO" %>
<%
    UserDTO user = (UserDTO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="assets/css/dashboard.css">
</head>
<body>
    <h2>Welcome, <%= user.getName() %>!</h2>
    <p>Role: <%= user.getRole() %></p>

    <ul>
        <li><a href="examList.jsp">View Exams</a></li>
        <% if ("Instructor".equals(user.getRole())) { %>
            <li><a href="createExam.jsp">Create Exam</a></li>
        <% } %>
        <li><a href="LogoutServlet">Logout</a></li>
    </ul>
</body>
</html>
