<%-- 
    Document   : authorList
    Created on : Sep 19, 2017, 8:35:54 PM
    Author     : Aruni
--%>

<%@page import="edu.wctc.distjava.jaau.bookwebapp.controller.AuthorController"%>
<%@page import="java.util.List"%>
<%@page import="edu.wctc.distjava.jaau.bookwebapp.model.Author"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author List</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <p style="color: red;">${errMessage}</p>

        <h1>Author List</h1>

        <table border="1">

            <c:forEach var="a" items="${authorList}">
                <tr>
                    <td>${a.authorId}</td>
                    <td><a href="authorController?action=edit&id=${a.authorId}">${a.authorName}</a></td>
                    <td><fmt:formatDate pattern = "yyyy-MM-dd" value="${a.dateAdded}"/></td>
                    <td><input type='button' value='Remove' onclick="location.href = 'authorController?action=<%=AuthorController.DELETE_ACTION%>&id=${a.authorId}'"/></td>                    
                </tr>                
            </c:forEach>
                
        </table>
        <input type='button' value='Add' onclick="location.href = 'authorController?action=add'">
         
        
    </body>
</html>
