<%-- 
    Document   : threadmanage
    Created on : Jul 10, 2023, 5:05:11 PM
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
        <c:forEach var="thread" items="${userthread}">
            <form action="GetThreadInforController" method="post">
            <input type="hidden" name="tid" value="${thread.id}"/>
            Title: ${thread.ttitle}
            || Tag: ${thread.ttag} || Author: ${thread.tauthor}<br/>
            <button type="submit">Update</button>
            </form>
            <form action="DeleteThreadController" method="post">
                <input type="hidden" name="tid" value="${thread.id}"/>
                <button type="submit">Delete</button>
            </form>
        </c:forEach>
    </body>
</html>
