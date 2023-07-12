<%-- 
    Document   : CreateNewThread
    Created on : Jul 3, 2023, 1:30:02 PM
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
         <c:choose>
            <c:when test="${user==null}">
                access denied<br/>
                <a href="login.jsp">
                    Back to login
                </a><br/><!-- comment -->
                <a href="signup.jsp">
                    Not having an account 
                </a>            
            </c:when>
            <c:otherwise>
        <div>${requestScope.nice}</div>
        <form action="CreateNewThreadController">
            Title: <input type="text" name="title"/><br/>
            Content: <input type="text" name="content"/><br/>
            Tag: <br/>
            <input type="radio" id="help" name="tag" value="Help">
            <label for="help">Help</label><br>
            <input type="radio" id="funny" name="tag" value="Funny">
            <label for="funny">Funny</label><br>
            <input type="radio" id="game" name="tag" value="Game">
            <label for="game">Game</label><br>
            <input type="radio" id="troll" name="tag" value="Troll">
            <label for="troll">Troll</label><br>
            <input type="submit" value="Submit"/><br/>
        </form>
        <a href="ThreadController">
            Go back to Blog
        </a>
                    </c:otherwise>
        </c:choose>
    </body>
</html>
