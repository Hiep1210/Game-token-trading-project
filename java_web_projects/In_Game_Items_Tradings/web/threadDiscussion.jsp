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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog</title>
    </head>
    <body>
        
        <h1>${t.ttitle}</br></h1>
        <h3>${t.tcontent}</h3>
         <form action ="InsertCommentController" method="post">
                    <input type="hidden" value="${t.id}" name="threadid"/>
                    <input type="hidden" value="${comment.id}" name="cid"/>
                    <input type="hidden" value="${userlist.id}" name="uid"/>
                <textarea type="text" name="ccontent" rows="4">${value}</textarea>
                <button type="submit" name="action" value="insert">Post Comment</button>
                </form>
        <form action="UpdateCommentController" method="post">
        <c:forEach var="comment" items="${commentlist}">
            <input type="hidden" value="${t.id}" name="threadid"/>
            <input type="hidden" value="${comment.id}" name="cid"/>
            <input type="hidden" value="${userlist.id}" name="uid"/>
                ${userlist[comment.user_id].username}:
                <input type="textbox"  value="${comment.ccontent}" name="ccontent"/>
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
