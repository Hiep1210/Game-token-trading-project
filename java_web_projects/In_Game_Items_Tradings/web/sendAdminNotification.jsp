<%-- 
    Document   : topUpRequest
    Created on : Jun 5, 2023, 12:29:53 AM
    Author     : VICTUS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Send server notification</title>
        <!-- Link Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!-- Link Icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <!-- Link CSS -->
        <link rel="stylesheet" href="UI/css/style.css">
        <link rel="stylesheet" href="UI/css/styleForm.css">
    </head>

    <body>
        <div class="container nopadding">

            <!-- Form -->
            <div class="login form">
                <!-- Screen Header -->
                <header>Top-up</header>
                <!-- Main Form -->
                <c:if test="${(sessionScope.user != null) && (sessionScope.user.role_id eq 2)}">   
                    <form id="topup-form" action='InsertNotificationController' method='post'>
                        <input type="hidden" name="type" value="admin">
                        <div class="form-group">
                            <label for="content" class="form-label">Enter message</label>
                            <input type="text" id="content" name="content" class="form-control"  placeholder="Attention all users..." required>
                            <span class="form-message"></span>
                        </div>
                    </form>
                </c:if> 
               
                <!-- Form Buttons -->
                <div class="row">
                    <!-- Button Spacer -->
                    <div class="col-lg-9">
                    </div>
                    <!-- Summit button -->
                    <div class="col-lg-3">
                        <div class="summit-button">
                            <button type="submit" name="action" value="Submit" form="topup-form">Confirm</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>
