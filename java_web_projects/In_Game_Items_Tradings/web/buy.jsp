<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <!-- Link Icons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
              integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Link CSS -->
        <link rel="stylesheet" href="UI/css/style.css">
        <link rel="stylesheet" href="UI/css/buy.css">
    </head>

    <body>
        <c:set var="redirect" value="DisplayMarketItemsController"/>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg" id="navbar">
            <div class="container-fluid">
                <!-- Navbar Logo -->
                <a class="navbar-brand col-lg-3" href="DisplayMarketItemsController">
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
                                <a href="DisplayMarketItemsController">
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
                    <%--<c:out value="${pageContext.request.requestURI}"/>--%>
                    <div class="col-lg-4 nopadding navbar-user">
                        <div class="row nopadding">
                            <!-- User Notification -->
                            <c:if test="${(sessionScope.user != null)}">  

                                <div class="col-lg-3 navbar-user-notifi dropdown">
                                    <c:choose>
                                        <c:when test="${(requestScope.notificationList == null)}">
                                            <a class="btn" type="button" href="GetNotificationController?redirect=${redirect}">
                                                <i class="material-icons navbar-item-icon">notifications</i>
                                            </a>
                                        </c:when>
                                        <c:when test="${(requestScope.notificationList.size() eq 0) }">
                                            <!-- Dropdown toggler -->
                                            <a class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                               aria-expanded="false">
                                                <i class="material-icons navbar-item-icon">notifications</i>
                                            </a>
                                            <div class="dropdown-menu dropdown-menu-end">
                                                <a class="dropdown-item" href="#">You have 0 new notification </a>
                                            </div>
                                        </c:when>    
                                        <c:otherwise>
                                            <!-- Dropdown toggler -->
                                            <a class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                               aria-expanded="false">
                                                <i class="material-icons navbar-item-icon">notifications</i>
                                            </a>
                                            <div class="dropdown-menu dropdown-menu-end">
                                                <c:forEach var="notification" items="${requestScope.notificationList}">
                                                    <a class="dropdown-item" href="#">${notification.noti_content}</a>
                                                </c:forEach>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:if>
                            <!-- User Balance -->
                            <c:if test="${(sessionScope.user != null)}" >  
                                <div class="col-lg-6 navbar-user-balance nopadding">
                                    <!-- Balance amount -->
                                    <div class="navbar-user-balance-text">
                                        <h5>Your balance</h5>
                                        <h5>$ ${sessionScope.user.money}</h5>
                                    </div>
                                    <!-- Topup button -->
                                    <div class="navbar-user-balance-topup rounded-circle">
                                        <i class="material-icons navbar-item-icon">add</i>
                                    </div>
                                </div>
                            </c:if>
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
                                            <a class="dropdown-item" href="UserProfileController">User Profile</a>
                                            <a class="dropdown-item" href="#">Transaction History</a>
                                            <div class="dropdown-divider"></div>
                                            <a id="logout" class="dropdown-item" href="LogOutController">Log out</a>
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
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
        </nav>


        <!-- Main Content -->
        <div class="container-fluid main-content">
            <div class="row">
                <!-- Filter Section -->
                <div class="col-lg-2 flex-column p-4">
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
                    <div class="filter sidebar">
                        <div class="container">
                            <!-- Filter Type -->
                            <details class="sidebar-category">
                                <summary>Type</summary>
                                <ul class="nopadding d-flex flex-column">
                                    <div class="category-group">
                                        <input type="checkbox" name="knife" value="knife" id="knife">
                                        <label for="knife">Knife</label>
                                    </div>
                                    <div class="category-group">
                                        <input type="checkbox" name="glove" value="glove" id="glove">
                                        <label for="glove">Glove</label>
                                    </div>
                                    <div class="category-group">
                                        <input type="checkbox" name="gun" value="gun" id="gun">
                                        <label for="gun">Gun</label>
                                    </div>
                                </ul>
                            </details>
                            <!-- Filter Exterior -->
                            <details class="sidebar-category">
                                <summary>Exterior</summary>
                                <ul class="nopadding d-flex flex-column">
                                    <div class="category-group">
                                        <input type="checkbox" name="fn" value="fn" id="fn">
                                        <label for="knife">Factory New</label>
                                    </div>
                                    <div class="category-group">
                                        <input type="checkbox" name="mw" value="mw" id="mw">
                                        <label for="glove">Minimal Wear</label>
                                    </div>
                                    <div class="category-group">
                                        <input type="checkbox" name="ft" value="ft" id="ft">
                                        <label for="gun">Field-Tested</label>
                                    </div>
                                    <div class="category-group">
                                        <input type="checkbox" name="ww" value="ww" id="ww">
                                        <label for="gun">Well-Worn</label>
                                    </div>
                                    <div class="category-group">
                                        <input type="checkbox" name="bs" value="bs" id="bs">
                                        <label for="gun">Battle-Scarred</label>
                                    </div>
                                </ul>
                            </details>
                            <!-- Filter Rarity -->
                            <details class="sidebar-category">
                                <summary>Rarity</summary>
                                <ul class="nopadding d-flex flex-column">
                                    <div class="category-group">
                                        <input type="checkbox" name="grey" value="grey" id="grey">
                                        <label for="knife">Consumer Grade</label>
                                    </div>
                                    <div class="category-group">
                                        <input type="checkbox" name="blue" value="blue" id="blue">
                                        <label for="glove">Industrial Grade</label>
                                    </div>
                                    <div class="category-group">
                                        <input type="checkbox" name="purple" value="purple" id="purple">
                                        <label for="gun">Restricted</label>
                                    </div>
                                    <div class="category-group">
                                        <input type="checkbox" name="pink" value="pink" id="pink">
                                        <label for="gun">Classified</label>
                                    </div>
                                    <div class="category-group">
                                        <input type="checkbox" name="red" value="rd" id="red">
                                        <label for="gun">Covert</label>
                                    </div>
                                </ul>
                            </details>
                        </div>
                    </div>
                </div>
                <!-- Item List Section -->
                <div class="col-lg-10 p-4">
                    <div class="container">
                        <!-- Tool Bar -->
                        <div class="row">
                            <!-- Search Bar -->
                            <div class="col-lg-4 search-bar form">
                                <form action="">
                                    <div class="form-group">
                                        <i class="fa-solid fa-magnifying-glass pt-3 ps-3"></i>
                                        <input class="form-control ps-5" type="text" placeholder="Search...">
                                    </div>
                                </form>
                            </div>
                            <!-- Sort Button -->
                            <div class="col-lg-6">
                            </div>
                            <!-- View Cart -->
                            <div class="col-lg-2">
                            </div>
                        </div>
                        <!-- Item List -->
                        <div class="row" id="item-box">
                            <!-- Item Card -->
                            <c:forEach var ="market_items" items="${requestScope.market_list}">
                                <div class="col-lg-2 item-card mt-2 mb-2" id="item-card">
                                    <div class="card">
                                        <img src="UI/image/${market_items.getImg()}.png" alt ="wtf" class="card-img-top" >
                                        <div class="card-body">
                                            <h5 class="card-title item-card-price ps-1">$ 2000</h5>
                                            <a href="#" class="btn item-card-button">
                                                <i class="fa-solid fa-cart-shopping"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- Page Navigator -->
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${(sessionScope.user != null) && (sessionScope.user.role_id eq 2)}">   

            <!-- FOR INSERTING SERVER NOTIFICATION -->
            <form action='InsertNotificationController' method='post'>
                <input type="hidden" name="type" value="admin">
                <label for="Content">Content</label>
                <input type="text" name="content" id="title" placeholder="Content" required>
                <input style="color: white; font-weight: bold; margin-top: 10px" type='submit' name='action' value='Submit'>
            </form>  


        </c:if> 

        <script>
            function Redirect() {
                window.location.href = "GetNotificationController?redirect=DisplayMarketItemsController"
            }
        </script>


        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>

    </body>

</html>