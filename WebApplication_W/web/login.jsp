<%-- 
    Document   : login
    Created on : 10-Mar-2025, 07:23:36
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
        <div class="login-container">
            <div class="login-form">
                <h2 class="form-title">Đăng nhập</h2>
                <form action="MainCotroller" method="post">
                    <input type="hidden" name="action" value="login"/>
                    
                    <div class="form-group">
                        <label for="userId">Tên đăng nhập</label>
                        <input type="text" id="Username" name="txtUsername" required />
                    </div>

                    <div class="form-group">
                        <label for="password">Mật khẩu</label>
                        <input type="password" id="Password" name="txtPassword" required />
                    </div>

                    <button type="submit" class="submit-btn">Đăng nhập</button>
                    
                    <%
                       String message = request.getAttribute("message")+"";
                    %>
                    <%=message.equals("null")?"":message%>
                </form>
            </div>
        </div>
    </body>
</html>
