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
            Title:  ${thread.ttitle}
            || Tag: ${thread.ttag} || Author: ${thread.tauthor}<br/>        
        </c:forEach>
    </body>
</html>
