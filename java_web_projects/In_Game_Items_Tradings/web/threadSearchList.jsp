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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thread Search Page</title>
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
            <p class="card-text mb-1" >Title:  ${sl.ttitle}
                || Tag: ${sl.ttag} || Author: ${sl.tauthor}</p>
            <button type="submit">Go to discuss</button>
            </form>
    </c:forEach>
    </body>
</html>
