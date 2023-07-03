<%-- 
    Document   : thread
    Created on : Jun 28, 2023, 4:35:16 PM
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
        <div>
            <c:forEach var="thread_list" items="${requestScope.threadlist}">
                <div>
                    <h5> ${thread_list.ttitle}</h5>
                    <h5> ${thread_list.tcontent}</h5>
                    <h5> ${thread_list.ttag}</h5>
                    <h5> ${thread_list.ticon}</h5>
                </div>
            </c:forEach>
        </div>
        <div>
            <a href="createNewThread.jsp">
                Create New Thread
            </a>
        </div>
    </body>
</html>
