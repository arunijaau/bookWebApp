<%-- 
    Document   : index
    Created on : Sep 19, 2017, 8:00:19 PM
    Author     : Aruni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Web Application</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Pick a Task</h1>
        <ul>
            <li><a href="authorController?action=list">View all Authors</a></li>
            <li><a href="authorController?action=add">Add Author(s)</a></li>
            
           <!--
            <li><a href="authorController?action=edit">Edit Author</a></li>
            <li><a href="authorController?action=remove">Remove Author(s)</a></li>
            
           -->
        </ul>
    </body>
</html>
