<%-- 
    Document   : updateThread
    Created on : Jul 15, 2023, 3:56:44 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UpdateThread Page</title>
    </head>
    <body>
        <div>${requestScope.oi}</div>
        <form action="UpdateThreadController" method="post">
            <input type="hidden" name="tid" value="${t.id}"/>
            Title:
            <input type="text" name="ttitle" value="${t.ttitle}"/><br/>
            Content:
            <input type="text" name="tcontent" value="${t.tcontent}"/><br/>
            Tag: <br/>
            <input type="radio" id="help" name="tag" value="Help">
            <label for="help">Help</label><br>
            <input type="radio" id="funny" name="tag" value="Funny">
            <label for="funny">Funny</label><br>
            <input type="radio" id="game" name="tag" value="Game">
            <label for="game">Game</label><br>
            <input type="radio" id="troll" name="tag" value="Troll">
            <label for="troll">Troll</label><br>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
