<%-- 
    Document   : userProfile
    Created on : Jun 5, 2023, 10:36:58 AM
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
        <title>User Profile</title>
        <!-- Link Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!-- Link Icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
              integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Link CSS -->
        <link rel="stylesheet" href="UI/css/style.css">
        <link rel="stylesheet" href="UI/css/userProfile.css">
        <script src="UI/js/formValidate.js"></script>
    </head>

    <body>
        <c:set var="redirect" value="createAution.jsp"/>
        <%@include file="navbar.jsp" %>
        <c:if test ="${sessionScope.user != null}">

            <div class="container-fluid main-content">
                <div class="row">
                    <!-- Sidebar -->
                    <div class="col-lg-3 sidebar">
                        <%@include file="sidebar.jsp" %>
                    </div>
                    <div class="col-lg-6 user-information">
                        <div class="container">
                            <div class="form">
                                <!-- Screen Header -->
                                <header class="header text-center">Create auction</header>
                                    <c:if test="${not empty requestScope.errorMessage}">
                                    <p>${requestScope.errorMessage}</p>
                                </c:if>  
                                <!-- Main Form -->
                                <form  id="auction-form"action='CreateAuctionController' method='post'>
                                    <input type="hidden" value="1" name="itemId">
                                    <div class="form-group">
                                        <label for="lowestBid" class="form-label">Enter the starting bid for your auction</label>
                                        <input type="number" step="0.01" min="0.01"id="lowestBid" name="lowestBid" class="form-control"  placeholder="Insert starting bid" required>
                                        <span class="form-message"></span>
                                    </div>
                                    <div class="form-group">
                                        <label class="form-label">Choose auction duration:</label>
                                        1 <input type="radio"  name="auctionDuration" value="1" required >
                                        3<input type="radio"  name="auctionDuration" value="3">
                                        5<input type="radio"  name="auctionDuration" value="5">
                                        10<input type="radio" name="auctionDuration" value="10">
                                    </div>
                                    <div class="form-group">
                                        <label for="gameAccountName" class="form-label">Enter the game account name that has your item</label>
                                        <input type="text" id="gameAccountName" name="gameAccountName" class="form-control"  placeholder="Insert game account name" required>
                                        <span class="form-message"></span>
                                    </div>
                                </form>
                                <!-- Form Buttons -->
                                <div class="row">
                                    <!-- Button Spacer -->
                                    <div class="col-lg-9">
                                    </div>
                                    <!-- Summit button -->
                                    <div class="col-lg-3">
                                        <div class="summit-button mt-2">
                                            <button type="submit" name="action" form="auction-form" value="Submit">Confirm</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test ="${sessionScope.user == null}">
            <h3>Please log in to use this feature!</h3>
        </c:if>
        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>

    </body>

</html>



