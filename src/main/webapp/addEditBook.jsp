<%-- 
    Document   : addEditBook
    Created on : Nov 16, 2017, 5:54:33 PM
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
        <title>Add or Edit Book</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Add/Edit Book</h1>
        
        <form name="addEditBookForm" id="addEditBookForm" method="POST" action="bookController?action=${action}">
            <div>Book Title: <input type="text" value="${book.title}" name="title"></div>
            <input type="hidden" value="${book.bookId}" name="id">
            <div>
            ISBN: <input type="text" value="${book.isbn}" name="isbn">
            </div>
            <div>
            Author: <select name="author">
                        <c:forEach var="a" items="${authors}">
                            <c:if test="${book.authorId.authorId == a.authorId}">
                                <option value="${a.authorId}" selected="selected">${a.authorName}</option>
                            </c:if>
                             <c:if test="${book.authorId.authorId != a.authorId}">
                                <option value="${a.authorId}">${a.authorName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
            </div>
            <div>
            <input type="submit" name= "submit" value="Add/Edit"> 
            </div>
        </form>
        
    </body>
</html>
