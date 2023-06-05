<%-- 
    Document   : ChangePassword
    Created on : Jun 5, 2023, 4:17:13 PM
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
        <form action="ChangePasswordController">
            OldPassword: <input type="text" name="oldpass"/><br/>
            NewPassword: <input type="text" name="newpass"/><br/>
            ConfirmPassword: <input type="text" name="cfpass"/><br/>
            <input type="submit" value="Submit">
            <div style="color: red">${mess1}</div>
            <div style="color: red">${mess2}</div>
        </form>
    </body>
</html>
