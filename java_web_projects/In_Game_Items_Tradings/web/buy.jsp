<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buy Page</title>
    <!-- Link Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <!-- Link Icons -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <!-- Link CSS -->
    <link rel="stylesheet" href="UI/css/style.css">
</head>

<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <!-- Navbar Logo -->
            <a class="navbar-brand col-lg-3" href="#">
                <img src="UI/image/market_csmoney_2.png" alt="Bootstrap" width="200px">
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
                    </div>
                </div>
            </div>
    </nav>
    
    <!-- Main Content -->
    <div class="container-fluid main-content">
        <!-- Filter Section -->
        <div class="col-md-2 flex-column">
            <!-- Cart Info Section -->
            <div class="cart-info">
                <button class="buy-button">
                    <h5>Buy now</h5>
                </button>
                <div class="cart-total">
                    <h5>Cart total</h5>
                    <h5>$ 2000</h5>
                </div>
            </div>   
            <!-- Filter Selection Section -->
            <div class="filter">
                <ul></ul>
            </div>
        </div>

        <div class="col-md-10"></div>
    </div>
     <c:forEach var = "notification" items="${requestScope.notiList}">
         <p>${notification.noti_content}</p>
         <p>${notification.date}</p>
         <img src="${notification.img}" alt="${notification.img} picture">
         </c:forEach>
    
    <!-- Link Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>
</body>

</html>