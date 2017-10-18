<%-- 
    Document   : addEditAuthor
    Created on : Oct 17, 2017, 5:42:31 PM
    Author     : Aruni
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add or Edit Author</title>
    </head>
    <body>
        <h1>Add/Edit Author</h1>
        
        <form name="addEditAuthorForm" id="addEditAuthorForm" method="POST" action="authorController?action=addEdit">
            Author Name: <input type="text" value="${author.authorName}" name="name">
            <br>
            Date Added: <input type="text" value="${author.dateAdded}" name="dateAdded">
            <br>
            <input type="submit" name= "submit" value="Add/Edit"> 
        </form>
        
    </body>
</html>
