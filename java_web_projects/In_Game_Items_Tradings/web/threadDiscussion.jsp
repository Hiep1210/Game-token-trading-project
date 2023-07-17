<%-- 
    Document   : threadDisccusion
    Created on : Jul 4, 2023, 4:07:12 PM
    Author     : ACER
--%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Discussion Page</title>
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
        
        <h4 class="card-text mb-1">${t.ttitle}</br></h4>
        <h5 class="card-text mb-1">${t.tcontent}</h5>
         <form action ="InsertCommentController" method="post">
                    <input type="hidden" value="${t.id}" name="threadid"/>
                    <input type="hidden" value="${comment.id}" name="cid"/>
                    <input type="hidden" value="${userlist.id}" name="uid"/>
                <textarea type="text" name="ccontent" rows="4">${value}</textarea>
                <button type="submit" name="action" value="insert">Post Comment</button>
                </form>
        <c:forEach var="comment" items="${commentlist}">
            <form action="UpdateCommentController" method="post">
            <input type="hidden" value="${t.id}" name="threadid"/>
            <input type="hidden" value="${comment.id}" name="cid"/>
            <input type="hidden" value="${comment.user_id}" name="uid"/>
            <p class="card-text mb-1" >${userlist[comment.user_id].username}: </p>
                <input class="form-label" type="text"  value="${comment.ccontent}" name="ccontent"/>    
                <c:if test="${sessionScope.user.id eq comment.user_id}">
                <button type="submit">Update</button>
                </form>
                <form action="DeleteCommentController" method="post">
                    <input type="hidden" value="${t.id}" name="threadid"/>
                    <input type="hidden" value="${comment.id}" name="cid"/>
                <button type="submit">Delete</button>
                </form>
        </c:if>
               <br/>
        </c:forEach>               
    </body>
</html>
