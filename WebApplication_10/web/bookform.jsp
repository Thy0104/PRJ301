<%-- 
    Document   : bookform
    Created on : 27-Feb-2025, 10:33:32
    Author     : baothy2004
--%>

<%@page import="dto.BookDTO"%>
<%@page import="java.awt.print.Book" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Management</title>
        <style>
            *{
                box-sizing: border-box;
                font-family: 'Segoe UI',Tahoma,Geneva, Verdana, sans-serif;
            }
            
            body{
                background-color: #f5f5f5;
                margin: 0;
                padding: 20px;
                display: flex;
                justify-content: center;
                align-items:  center;
                min-height: 100vh;
            }
            
            .container{
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                padding: 30px;
                width: 100%;
                max-width: 600px;
            }
            
            h1{
                color: #2c3e50;
                margin-top: 0;
                margin-bottom: 20px;
                text-align:  center;
            }
            
            .form-group{
                margin-bottom: 15px;
            }
            
            label{
                display: block;
                margin-bottom: 5px;
                font-weight: 600;
                color: #333;
            }
            
            input[type="text"],
            input[type="number"]{
                width: 100%;
                padding: 10px;
                border: 1px solid  #ddd;
                border-radius: 4px;
                font-size: 16px;
                transition: border-color 0.3s;
            }
            
            input[type="text"]:focus,
            input[type="number"]:focus{
                border-color: #3498db;
                outline: none;
                box-shadow: 0 0 5px rgba(52, 152, 219, 3);
            }
            
            .error-message{
                color: #e74c3C;
                font-size: 14px;
                margin-top: 20px;
            }
            
            .button-group{
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }
            
            button, input[type="submit"], input[type="reset"]{
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                font-weight: 600px;
                cursor: pointer;
                transition: background-color 0.3s;
            }
            
            input[type="submit"]{
                background-color: #2ecc71;
                color: white;
                flex: 1;
                margin-right: 10px;
            }
            
            input[type="submit"]:hover{
                background-color: #27ae60;
            }
            
            input[type="reset"]{
                background-color: #e74c3c;
                color: white;
                flex: 1;
                margin-left: 10px;
            }
            
            input[type="reset"]:hover{
                background-color: #c0392b;
            }
        </style>
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
            <h1>Book Information</h1>
            <form action="MainController" method="post">
                <input type="hidden" name="action" value="add"/>
                
                <div class="form-group">
                    <label for="txtBookID">Book ID:</label>
                    <input type="text" id="txtBookID" value="<%=book.getBookID()%>"/>
                    <% if(!txtTitle_error.isEmpty()){%>
                    <div class="error-message"><%=txtTitle_error%></div>
                    <% } %>
                </div>
                
                <div class="form-group">
                    <label for="txtTitle">Title:</label>
                    <input type="text" id="txtTitle" value="<%=book.getTitle()%>"/>
                    <% if(!txtTitle_error.isEmpty()){%>
                    <div class="error-message"><%=txtTitle_error%></div>
                    <% } %>
                </div>
                
                <div>
                    <label for="txtAuthor">Author:</label>
                    <input type="text" id="txtAuthor" value="<%=book.getAuthor()%>"/>
                </div>
                
                <div>
                    <label for="txtPublishYear">Publish Year:</label>
                    <input type="text" id="txtPublishYear" value="<%=book.getPublishYear()%>"/>
                </div>
                
                <div>
                    <label for="txtPrice">Price:</label>
                    <input type="text" id="txtPrice" value="<%=book.getPrice()%>"/>
                </div>
                
                <div class="form-group">
                    <label for="txtQuantity">Quantity:</label>
                    <input type="text" id="txtQuantity" value="<%=book.getQuantity()%>"/>
                    <% if(!txtQuantity_error.isEmpty()){%>
                    <div class="error-message"><%=txtQuantity_error%></div>
                    <% } %>
                </div>
                
                <div class="button-group">
                    <input type="Submit" value="Save"/>
                    <input type="Reset" value="Reset"/>
                </div>            
        </form>
        </div>
    </body>
</html>
