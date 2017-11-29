<%-- 
    Document   : bookList
    Created on : Nov 16, 2017, 5:39:38 PM
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
        <title>Book List</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <p style="color: red;">${errMessage}</p>

        <h1>Book List</h1>

        <table border="1">
            
            <c:forEach var="b" items="${bookList}">
                <tr>
                    <td>${b.bookId}</td>
                    <td><a href="bookController?action=edit&id=${b.bookId}">${b.title}</a></td>
                    <td>${b.isbn}</td>
                    <td>${b.authorId.authorName}</td>  
                    <td>
                        <form method="POST" action="bookController?action=remove&id=${b.bookId}">
                        <input type='submit' value='Remove'/></form>
                    </td> 
                </tr>                
            </c:forEach>
                
        </table>
        <input type='button' value='Add' onclick="location.href = 'bookController?action=add'">
        
    </body>
</html>
