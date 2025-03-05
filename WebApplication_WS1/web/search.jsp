<%-- 
    Document   : search
    Created on : 05-Mar-2025, 11:02:38
    Author     : baothy2004
--%>

<%@page import="dto.ProjectDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Project Management</title>
    <style>
        .project-table {
            width: 100%;
            border-collapse: collapse;
            margin: 25px 0;
            font-size: 14px;
            font-family: Arial, sans-serif;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
        .project-table thead th {
            background-color: #009879;
            color: #ffffff;
            text-align: left;
            font-weight: bold;
            padding: 12px 15px;
        }
        .project-table tbody tr {
            border-bottom: 1px solid #dddddd;
        }
        .search-section {
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            align-items: center;
        }
        .search-input {
            flex-grow: 1;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
            margin-right: 10px;
        }
        .search-btn {
            background-color: #009879;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
        }
        .add-btn {
            display: inline-block;
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <%@include file="header.jsp" %>
    <div style="min-height: 500px; padding: 10px">
        <% if (session.getAttribute("user") != null) { %>
        <div class="search-section">
            <form action="MainController">
                <input type="hidden" name="action" value="search"/>
                <input type="text" name="searchTerm" class="search-input" placeholder="Enter project name or ID..."/>
                <input type="submit" value="Search" class="search-btn"/>
            </form>
        </div>
        <a href="projectform.jsp" class="add-btn">Add New Project</a>
        <%
            if (request.getAttribute("projects") != null) {
                List<ProjectDTO> projects = (List<ProjectDTO>) request.getAttribute("projects");
        %>
        <table class="project-table">
            <thead>
                <tr>
                    <th>Project ID</th>
                    <th>Project Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Estimated Launch</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for (ProjectDTO p : projects) { %>
                <tr>
                    <td><%= p.getProject_id()%></td>
                    <td><%= p.getProject_name() %></td>
                    <td><%= p.getDescription() %></td>
                    <td><%= p.getStatus() %></td>
                    <td><%= p.getEstimated_launch()%></td>
                    <td>
                        <a href="MainController?action=delete&id=<%= p.getProject_id() %>">
                            <img src="assets/images/icons8-delete-64.png" style="height: 25px"/>
                        </a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } %>
        <% } else { %>
        <p>You do not have permission to access this content.</p>
        <% } %>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
