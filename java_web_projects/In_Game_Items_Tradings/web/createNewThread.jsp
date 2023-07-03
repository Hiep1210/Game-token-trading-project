<%-- 
    Document   : CreateNewThread
    Created on : Jul 3, 2023, 1:30:02 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CreateNewThreadController" method="post">
            Title: <input type="text" name="username"/><br/>
            Content: <input type="text" name="password"/><br/>
            Tag: <br/>
            <input type="radio" id="help" name="tag" value="Help">
            <label for="help">Help</label><br>
            <input type="radio" id="funny" name="tag" value="Funny">
            <label for="funny">Funny</label><br>
            <input type="radio" id="game" name="tag" value="Game">
            <label for="game">Game</label><br>
            <input type="submit" value="Submit"/><br/>
        </form><br/>
    </body>
</html>
