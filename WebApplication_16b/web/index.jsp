<%-- 
    Document   : index
    Created on : 13-Mar-2025, 11:32:57
    Author     : baothy2004
--%>

<%@page import="dto.BookDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Catalog - Book Management Sytem</title>
        <link rel="stylesheet" href="assets/css/index.css"
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="page-content">
            <div class="catalog-header">
                <h1>Book Catalog</h1>
                <div class="search-container">
                    <form action="MainController" method="GET">
                        <input type="hidden" name="action" value="listBooks">
                        <input type="text" name="searchTerm" placeholder="Search by title,author..." value="${param.searchTerm}">
                        <button type="submit" class="search-button">Search</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
