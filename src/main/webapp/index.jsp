<%-- 
    Document   : index
    Created on : Sep 19, 2017, 8:00:19 PM
    Author     : Aruni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Web Application</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <sec:authorize access="hasAnyRole('ROLE_MGR','ROLE_USER')">
            Welcome Back: <sec:authentication property="principal.username"></sec:authentication> 
        </sec:authorize>
            
        <h1>Pick a Task</h1>
        <ul>
            <li><a href="authorController?action=list">View all Authors</a></li>
            <li><a href="authorController?action=add">Add Author(s)</a></li>
            <li><a href="bookController?action=list">View all Books</a></li>
           <!--
            <li><a href="authorController?action=edit">Edit Author</a></li>
            <li><a href="authorController?action=remove">Remove Author(s)</a></li>
            
           -->
        </ul>
        
        <sec:authorize access="hasAnyRole('ROLE_MGR')">
        <h1>For managers only</h1>
        </sec:authorize>
        
        <sec:authorize access="hasAnyRole('ROLE_MGR','ROLE_USER')">
            Logged in as: <sec:authentication property="principal.username"></sec:authentication> ::
            <a href='<%= this.getServletContext().getContextPath() + "/j_spring_security_logout"%>'>Log Me Out</a>
        </sec:authorize>
    </body>
</html>
