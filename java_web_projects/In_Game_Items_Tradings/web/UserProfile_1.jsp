<%-- 
    Document   : UserProfile
    Created on : Jun 5, 2023, 3:10:51 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Username:${sessionScope.user.username}</h1>
        <h2>Money :${sessionScope.user.money}</h2>
<!--        <h3>Email:${sessionScope.game.email}</h3>
        <h4>Gender:${sessionScope.game.gender}</h4>
        <h5>DoB:${sessionScope.game.DoB}</h5>-->
        <form action ="logout" >
            <input type="submit" value="Log out">
            <a href="ChangePassword.jsp">Change Password </a>
        </form>
        <div style="color: blue">${mess3}</div>      
    </body>
</html>
