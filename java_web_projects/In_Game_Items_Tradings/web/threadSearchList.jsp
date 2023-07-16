<%-- 
    Document   : threadSearchList
    Created on : Jul 16, 2023, 3:43:08 PM
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
    <c:forEach var="sl" items="${list}">
        <form action="ThreadDiscussionController" method="post">
            <input name="threadid" value="${sl.id}" type="hidden">   
            Title:  ${sl.ttitle}
            || Tag: ${sl.ttag} || Author: ${sl.tauthor}<br/>
            <button type="submit">Go to discuss</button>
            </form>
    </c:forEach>
    </body>
</html>
