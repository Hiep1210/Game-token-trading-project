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
        <form action="verifyCode">
            <div class="form-group">
                        <label for="cfcode" class="form-label">Confirm Code</label>
                        <input id="cfcode" name="cfcode" type="text" placeholder="EX:123456" class="form-control">
                        <span class="form-message"></span>
                    </div>
            <div class="form-message">${requestScope.AlertC}</div>
        </form>
    </body>
</html>
