<%-- 
    Document   : bookform
    Created on : 27-Feb-2025, 10:33:32
    Author     : baothy2004
--%>

<%@page import="dto.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Management</title>
    </head>
    
    <body>
        <%
            BookDTO book = new BookDTO();
            try {
                    book = (BookDTO) request.getAttribute("book");
                } catch (Exception e) {
                    book = new BookDTO();
                }
            if (book == null){
                book = new BookDTO();
            }
            //get error information
            String txtBookID_error = request.getAttribute("txtBookID_error")+"";
            txtBookID_error = txtBookID_error.equals("null")?"": txtBookID_error;
            String txtTitle_error = request.getAttribute("txtTitle_error")+"";
            txtTitle_error = txtTitle_error.equals("null")?"": txtTitle_error;
            String txtQuantity_error = request.getAttribute("txtQuantity_error")+"";
            txtQuantity_error = txtQuantity_error.equals("null")?"": txtQuantity_error;
            
        %>
        <div class="container">
            <form action="MainController" method="post">
                <div class="form-group">
                    <label for="txtBookID">Book ID:</label>
                    <input type="text" id="txtBookID" value="<%=book.getBookID()%>"/>
                    <% if(!txtTitle_error.isEmpty()){%>
                </div>
            
            <input type="Submit" value="Save"/>
            <input type="Reset" value="Reset"/>
        </form>
        </div>
    </body>
</html>
