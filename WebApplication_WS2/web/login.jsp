<%-- 
    Document   : login
    Created on : 17-Mar-2025, 02:03:55
    Author     : baothy2004
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="assets/css/login.css">
    </head>
    <body>
    <h2>Login</h2>
    <form action="LoginServlet" method="post">
        <label>Username:</label> <input type="text" name="username" required><br>
        <label>Password:</label> <input type="password" name="password" required><br>
        <input type="submit" value="Login">
    </form>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
</body>
</html>
