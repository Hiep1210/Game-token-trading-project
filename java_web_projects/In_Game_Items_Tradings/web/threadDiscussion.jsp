<%-- 
    Document   : threadDisccusion
    Created on : Jul 4, 2023, 4:07:12 PM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="name" value="${requestScope.user}"/>  
        <c:forEach var="comment" items="${commentlist}">
            ${user[Comment.user_id].username}: 
            ${comment.ccontent}  
        </c:forEach>
 <
    </body>
</html>
