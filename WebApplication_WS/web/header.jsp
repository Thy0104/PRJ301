<%-- 
    Document   : header
    Created on : 05-Mar-2025, 09:52:45
    Author     : baothy2004
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%                    if (session.getAttribute("user") != null) {
                        UserDTO userHeader = (UserDTO) session.getAttribute("user");

        %>
        <div class="user-section">
            <span class="welcome-text">Xin chào, <span class="user-name"><%=userHeader.getName()%></span>!</span>
            <form action="MainController" method="post" style="margin: 0;">
            <input type="hidden" name="action" value="logout"/>
            <input type="submit" value="Đăng xuất" class="logout-btn"/>
            </form>
        </div>
        <%}%>
    </body>
</html>
