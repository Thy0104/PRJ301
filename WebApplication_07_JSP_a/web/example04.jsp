<%-- 
    Document   : example04
    Created on : 10-Feb-2025, 10:31:16
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
        <%
            for( int i=2;i<=9;i++){
                %>
                <hr/>
                <h3>Bảng cửu chương </h3> <%=i%>
                <%
                for (int j=1;j<=10;j++){
                    %>
                    <%=i%> * <%=j%> = <%=(i*j)%>  </br>
                    %>
                }
                %>                                           
            }
        %>
    </body>
</html>
