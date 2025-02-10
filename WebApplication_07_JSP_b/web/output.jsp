<%-- 
    Document   : output
    Created on : 10-Feb-2025, 11:05:36
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
            int n = (int)request.getAttribute("n");
        %>   
        
        %>             
                <h3>Bảng cửu chương </h3> <%=n%>
                <%
                for (int j=1;j<=10;j++){
                    %>
                    <%=n%> * <%=j%> = <%=(n*j)%>  <br/>
                    <%
                }
                %>                                           
            
    </body>
</html>
