<%-- 
    Document   : confirmCode
    Created on : Jun 13, 2023, 11:37:37 AM
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
        <form action="VerifyCode">
            Confirm Code: <input type="text" name="cfcode"/><br/>
            <input type="submit" value="Submit">
            <div class="form-message">${requestScope.AlertC}</div>
        </form>
    </body>
</html>
