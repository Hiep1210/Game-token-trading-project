<%-- 
    Document   : requestList
    Created on : Jun 5, 2023, 12:02:45 AM
    Author     : VICTUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Page</title>
        <!-- Link Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!-- Link Icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <!-- Link CSS -->
        <link rel="stylesheet" href="UI/css/style.css">
        <link rel="stylesheet" href="UI/css/userProfile.css">
        <link rel="stylesheet" href="UI/css/admin.css">
        <style>
            .table {
                color: white;
            }
            p {
                color: white;
            }
        </style>
    </head>

    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg" id="navbar">
            <div class="container-fluid">
                <!-- Navbar Logo -->
                <a class="navbar-brand col-lg-3" href="#">
                    <img src="UI/image/newLogo.png" alt="siteLogo" width="100px">
                </a>
                <!-- Navbar Toggler Button -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarToggler"
                        aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <!-- Navbar Toggler -->
                <div class="collapse navbar-collapse col-lg-9" id="navbarToggler">
                    <!-- Navbar Item Box -->
                    <div class="navbar-item-box col-lg-8">
                        <div class="row nopadding">
                            <!-- Trade Button -->
                            <div class="col-lg-3 navbar-item nopadding">
                                <a href="">
                                    <i class="material-icons navbar-item-icon">compare_arrows</i>
                                </a>
                                <h5>Trade</h5>
                            </div>
                            <!-- Sell Button -->
                            <div class="col-lg-3 navbar-item nopadding">
                                <a href="">
                                    <i class="material-icons navbar-item-icon">sell</i>
                                </a>
                                <h5>Sell</h5>
                            </div>
                            <!-- Buy Button -->
                            <div class="col-lg-3 navbar-item nopadding">
                                <a href="">
                                    <i class="material-icons navbar-item-icon">shopping_cart</i>
                                </a>
                                <h5>Buy</h5>
                            </div>
                            <!-- Auction Button -->
                            <div class="col-lg-3 navbar-item nopadding">
                                <a href="">
                                    <i class="material-icons navbar-item-icon">gavel</i>
                                </a>
                                <h5>Auction</h5>
                            </div>
                        </div>
                    </div>
                    <!-- Navbar User  -->
                    <div class="col-lg-4 nopadding navbar-user">
                        <div class="row nopadding">
                            <!-- User Notification -->
                            <div class="col-lg-3 navbar-user-notifi dropdown">
                                <!-- Dropdown toggler -->
                                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                        aria-expanded="false">
                                    <i class="material-icons navbar-item-icon">notifications</i>
                                </button>
                            </div>
                            <!-- User Balance -->
                            <div class="col-lg-6 navbar-user-balance nopadding">
                                <!-- Balance amount -->
                                <div class="navbar-user-balance-text">
                                    <h5>Your balance</h5>
                                    <h5>$ 10000</h5>
                                </div>
                                <!-- Topup button -->
                                <div class="navbar-user-balance-topup rounded-circle">
                                    <i class="material-icons navbar-item-icon">add</i>
                                </div>
                            </div>
                            <!-- User Profile -->
                            <c:choose>
                                <c:when test="${sessionScope.user != null}">
                                    <div class="col-lg-3 navbar-user-profile dropdown">
                                        <!-- Dropdown toggler -->
                                        <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                                aria-expanded="false">
                                            <img class="img-fluid rounded-circle" src="UI/image/user_profile.jpg" alt="">
                                        </button>
                                        <!-- Dropdown menu -->
                                        <div class="dropdown-menu dropdown-menu-end">
                                            <a class="dropdown-item" href="userProfile.html">User Profile</a>
                                            <a class="dropdown-item" href="#">Transaction History</a>
                                            <div class="dropdown-divider"></div>
                                            <a id="logout" class="dropdown-item" href="#">Log out</a>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="col-lg-3 navbar-user-profile dropdown">
                                        <!-- Dropdown toggler -->
                                        <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                                aria-expanded="false">
                                            <img class="img-fluid rounded-circle" src="UI/image/user_profile1.jpg" alt="">
                                        </button>
                                        <!-- Dropdown menu -->
                                        <div class="dropdown-menu dropdown-menu-end">
                                            <a class="dropdown-item" href="loginGameAccount.jsp?request_id=1">Sign Up</a>
                                            <a class="dropdown-item" href="LoginUsername.jsp">Log In</a>
                                            <div class="dropdown-divider"></div>
                                            <a id="logout" class="dropdown-item" href="#">Log out</a>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
        </nav>

        <div class="container-fluid main-content">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-lg-3 sidebar">
                    <div class="container">
                        <!-- Sidebar  -->
                        <details class="sidebar-category">
                            <summary>Announcement</summary>
                            <ul class="nopadding">
                                <a href="sendAnnouncement.html">
                                    <li>Send Announcement</li>
                                </a>
                            </ul>
                        </details>
                        <!-- Sidebar Request -->
                        <details class="sidebar-category">
                            <summary>Requests</summary>
                            <ul class="nopadding">
                                <a href="retrieveRequest.html">
                                    <li>Topup Request</li>
                                </a>
                            </ul>
                        </details>
                    </div>
                </div>
                <!-- Sidebar Page Info -->
                <div class="col-lg-9 page-info">
                    <div class="container">
                        <c:choose>
                            <c:when test="${(requestScope.paymentRequestList != null) }">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Amount</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Picture</th>
                                            <th scope="col">Action</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var = "paymentRequest" items="${requestScope.paymentRequestList}">
                                        <form action='ProcessPaymentRequestController' method='post'>
                                            <input type="hidden" name="paymentRequestId" value="${paymentRequest.id}">
                                            <input type="hidden" name="type" value="payment">
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>${paymentRequest.money}</td>
                                                <td> ${paymentRequest.date}</td>
                                                <td><img src="UI/image/${paymentRequest.img}" alt="invoice picture" width="400" 
                                                    height="500"></td>
                                                <td>
                                                    Accept<input type="radio" name="decision" value="accept">
                                                    Reject<input type="radio" name="decision" value="reject">
                                                </td>
                                                <td><input type='submit' name='action' value='Accept'></td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>   
                            <c:otherwise>
                                <p>Currently 0 payment request to process</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>
    </body>

</html>
