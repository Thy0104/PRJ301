<%-- 
    Document   : invalid
    Created on : 05-Mar-2025, 11:17:45
    Author     : baothy2004
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div style="min-height: 500px; padding: 10px">
        <h1>
            <font color="red">
            Invalid username or password!
            </font>
        </h1>
        <a href="login.jsp">Click here to try again</a>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
