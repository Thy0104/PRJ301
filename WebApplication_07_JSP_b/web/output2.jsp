<%-- 
    Document   : output2
    Created on : 10-Feb-2025, 11:22:55
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
        <%! int n; %>
        <%
            if(n%2==0){
                %>
                <%=n%> là số chẵn!</b>
                <%
            }else{
                %>
                <%=n%> là số lẻ!</b>
                <%
            }
        %>
    </body>
</html>
