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
       <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Thread Page</title>
        <!-- Link Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!-- Link Icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <!-- Link Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
              integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Link CSS -->
        <link rel="stylesheet" href="UI/css/style.css">
        <link rel="stylesheet" href="UI/css/styleBuy.css">
        <link rel="stylesheet" href="UI/css/styleInput.css"/>
        <link rel="stylesheet" href="UI/css/styleItemBox.css"/>
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
