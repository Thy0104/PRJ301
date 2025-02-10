<%-- 
    Document   : example03
    Created on : 10-Feb-2025, 10:20:53
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
        <%! int a = 100; %>
        <%
            if(a%2==0){
                %>
                <%=a%> là số chẵn!</b>
                <%
            }else{
                %>
                <%=a%> là số lẻ!</b>
                <%
            }
        %>
    </body>
</html>
