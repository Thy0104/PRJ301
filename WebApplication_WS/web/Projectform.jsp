<%-- 
    Document   : bookform
    Created on : 27-Feb-2025, 10:33:32
    Author     : baothy2004
--%>

<%@page import="dto.UserDTO"%>
<%@page import="dto.ProjectDTO"%>
<%@page import="java.awt.print.Project"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Project Management</title>
        <style>
            * {
                box-sizing: border-box;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            }

            body {
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
                min-height: 100vh;
            }

            .page-content {
                padding: 40px 20px;
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: calc(100vh - 150px); /* Account for header and footer */
            }

            .form-container {
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                padding: 30px;
                width: 100%;
                max-width: 600px;
                margin: 0 auto;
            }

            h1 {
                color: #2c3e50;
                margin-top: 0;
                margin-bottom: 20px;
                text-align: center;
            }

            .form-group {
                margin-bottom: 15px;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: 600;
                color: #333;
            }

            input[type="text"],
            input[type="number"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 16px;
                transition: border-color 0.3s;
            }

            input[type="text"]:focus,
            input[type="number"]:focus {
                border-color: #3498db;
                outline: none;
                box-shadow: 0 0 5px rgba(52, 152, 219, 0.3);
            }

            .error-message {
                color: #e74c3c;
                font-size: 14px;
                margin-top: 5px;
            }

            .button-group {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }

            button, input[type="submit"], input[type="reset"] {
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                font-weight: 600;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            input[type="submit"] {
                background-color: #2ecc71;
                color: white;
                flex: 1;
                margin-right: 10px;
            }

            input[type="submit"]:hover {
                background-color: #27ae60;
            }

            input[type="reset"] {
                background-color: #e74c3c;
                color: white;
                flex: 1;
                margin-left: 10px;
            }

            input[type="reset"]:hover {
                background-color: #c0392b;
            }

            .error-container {
                background-color: #fff0f0;
                border-left: 4px solid #e74c3c;
                padding: 20px;
                border-radius: 4px;
                margin-bottom: 20px;
            }

            .error-container h1 {
                color: #e74c3c;
                margin-top: 0;
            }
            
            .back-link {
                display: block;
                text-align: center;
                margin-top: 20px;
                color: #3498db;
                text-decoration: none;
            }
            
            .back-link:hover {
                text-decoration: underline;
            }

            @media (max-width: 768px) {
                .form-container {
                    padding: 20px;
                }
                
                .button-group {
                    flex-direction: column;
                }
                
                input[type="submit"], input[type="reset"] {
                    margin: 5px 0;
                }
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <div class="page-content">
            <% if (session.getAttribute("user") != null) {
                    UserDTO user = (UserDTO) session.getAttribute("user");
                    if (user.getRoleID().equals("AD")) {
            %>
            <%
                ProjectDTO book = new ProjectDTO();
                try {
                    project = (ProjectDTO) request.getAttribute("project");
                } catch (Exception e) {
                    project = new ProjectDTO();
                }
                if (project == null) {
                    project = new ProjectDTO();
                }
                // get error information
                String txtProject_id_error = request.getAttribute("txtProject_id_error") + "";
                txtProject_id_error = txtProject_id_error.equals("null") ? "" : txtProject_id_error;
                String txtProject_name_error = request.getAttribute("txtProject_name_error") + "";
                txtProject_name_error = txtProject_name_error.equals("null") ? "" : txtProject_name_error;
            %>   
            <div class="form-container">
                <h1>Book Information</h1>
                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="add"/>

                    <div class="form-group">
                        <label for="txtProject_id">Project ID:</label>
                        <input type="text" id="txtProject_id" name="txtProject_id" value="<%=book.getProject_id()%>"/>
                        <% if (!txtProject_id_error.isEmpty()) {%>
                        <div class="error-message"><%=txtProject_id_error%></div>
                        <% }%>
                    </div>

                    <div class="form-group">
                        <label for="txtProject_name">Project name:</label>
                        <input type="text" id="txtTitle" name="txtProject_name" value="<%=book.getTitle()%>"/>
                        <% if (!txtProject_name_error.isEmpty()) {%>
                        <div class="error-message"><%=txtProject_name_error%></div>
                        <% }%>
                    </div>

                    <div class="form-group">
                    <label for="description">Description:</label>
                    <input type="text" id="description" name="description" required/>
                </div>

                <div class="form-group">
                    <label for="status">Status:</label>
                    <select id="status" name="status" required>
                        <option value="Pending">Pending</option>
                        <option value="Ongoing">Ongoing</option>
                        <option value="Completed">Completed</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="estimated_launch">Estimated Launch Date:</label>
                    <input type="date" id="estimated_launch" name="estimated_launch" required/>
                </div>

                    <div class="button-group">
                        <input type="submit" value="Save" />
                        <input type="reset" value="Reset"/>
                    </div>
                </form>
                
                <a href="MainController?action=search" class="back-link">Back to Project List</a>
            </div>
            <%} else {%>
            <div class="form-container error-container">
                <h1>403 Error</h1>
                <p>You do not have permission to access this content!</p>
                <a href="MainController?action=search" class="back-link">Back to Project List</a>
            </div>
            <%}
                } else {%>
            <div class="form-container error-container">
                <h1>Access Denied</h1>
                <p>Please log in to access this page.</p>
                <a href="login.jsp" class="back-link">Go to Login</a>
            </div>
            <%}%>
        </div>

        <jsp:include page="footer.jsp"/>
    </body>
</html>