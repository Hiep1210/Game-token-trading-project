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
        <form action="SearchThreadController" method="get">
            <input type="text" name="search"/>
            <button type="submit">Search</button>
        </form>
        <form action="SearchThreadByTag" method="get">
            <label for="tag">Choose a tag:</label>
            <select name="tag" id="tag">
                <option value=""></option>
                <option value="Help">Help</option>
                <option value="Funny">Funny</option>
                <option value="Game">Game</option>
                <option value="Troll">Troll</option>
               </select>
            <button type="submit">Search</button>
        </form>
        <c:forEach var="thread" items="${threadlist}">       
            <form action="ThreadDiscussionController" method="post">
            <input name="threadid" value="${thread.id}" type="hidden">   
            Title:  ${thread.ttitle}
            || Tag: ${thread.ttag} || Author: ${thread.tauthor}<br/>
            <button type="submit">Go to discuss</button>
            </form>         
        </c:forEach>
        <form action="ManageThreadController" method="post">
            <c:set var="userid" value="${requestScope.user}"/>
            <input name="userid" value="${user.id}" type="hidden">
            <button type="submit">Manage your thread</button>
        </form>
            <div>
            <a href="createNewThread.jsp">
                Create New Thread
            </a>
        </div>
    </body>
</html>
