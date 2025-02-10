<%-- 
    Document   : example01
    Created on : 10-Feb-2025, 09:58:50
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
        <%! int a = 9; %>
        
        <%
            double b;
            b = Math.sqrt(a);
        %>
        
        Kết quả: sqrt(<%=a%>) = <span style="color: red"><%=b%></span>
    </body>
</html>
