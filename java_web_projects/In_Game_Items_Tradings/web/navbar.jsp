<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navbar -->
<nav class="autohide navbar navbar-expand-lg" id="navbar">
    <div class="container-fluid">
        <!-- Navbar Logo -->
        <a class="navbar-brand col-lg-3" href="BuyPageController">
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
                    <div class="col-lg-2 navbar-item nopadding">
                        <a href="">
                            <i class="material-icons navbar-item-icon">compare_arrows</i>
                        </a>
                        <h5>Trade</h5>
                    </div>
                    <!-- Sell Button -->
                    <div class="col-lg-2 navbar-item nopadding">
                        <a href="SellPageController">
                            <i class="material-icons navbar-item-icon">sell</i>
                        </a>
                        <h5>Sell</h5>
                    </div>
                    <!-- Buy Button -->
                    <div class="col-lg-2 navbar-item nopadding">
                        <a href="BuyPageController">
                            <i class="material-icons navbar-item-icon">shopping_cart</i>
                        </a>
                        <h5>Buy</h5>
                    </div>
                    <!-- Auction Button -->
                    <div class="col-lg-2 navbar-item nopadding">
                        <a href="AuctionPageController">
                            <i class="material-icons navbar-item-icon">gavel</i>
                        </a>
                        <h5>Auction</h5>
                    </div>
                   <!-- Blog Button -->
                    <div class="col-lg-2 navbar-item nopadding">
                        <a href="ThreadController">
                            <i class="material-icons navbar-item-icon">compare_arrows</i>
                        </a>
                        <h5>Blog</h5>
                    </div>
                </div>
            </div>
            <!-- Navbar User  -->
            <%--<c:out value="${pageContext.request.requestURI}"/>--%>
            <div class="col-lg-4 nopadding navbar-user">
                <div class="row nopadding">
                    <!-- User Notification -->
                    <div class="col-lg-3 navbar-user-notifi dropdown">
                        <c:if test="${(sessionScope.user != null)}">  
                                    <!-- Dropdown toggler -->
                                    <a class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                       aria-expanded="false" onclick="getNotification()">
                                        <i class="material-icons navbar-item-icon">notifications</i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-end" id = "noti-list">
                                        <a class="dropdown-item" href="#">You have 0 new notification </a>
                                    </div>
                        </c:if>
                    </div>
                    <!-- User Balance -->
                    <div class="col-lg-6 navbar-user-balance nopadding">
                        <c:if test="${(sessionScope.user != null)}" >  
                            <!-- Balance amount -->
                            <div class="navbar-user-balance-text">
                                <h5>Your balance</h5>
                                <h5>$ ${sessionScope.user.money}</h5>
                            </div>
                            <!-- Topup button -->
                            <div class="navbar-user-balance-topup rounded-circle">
                                <i class="material-icons navbar-item-icon">add</i>
                            </div>
                        </c:if>
                    </div>
                    <!-- User Profile -->
                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                            <div class="col-lg-3 navbar-user-profile dropdown">
                                <!-- Dropdown toggler -->
                                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown"
                                        aria-expanded="false">
                                    <img class="img-fluid rounded-circle" src="UI/image/profile_pics/${sessionScope.user.getAvatar()}" alt=""style="width: 60px; height: 60px; object-fit: contain">
                                </button>
                                <!-- Dropdown menu -->
                                <div class="dropdown-menu dropdown-menu-end">
                                    <a class="dropdown-item" href="UserProfileController">User Profile</a>
                                    <a class="dropdown-item" href="#">Transaction History</a>
                                    <a class="dropdown-item" href="ViewCartController">View Your Cart</a>
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
                                    <div class="rounded-circle">
                                        <img class="img-fluid rounded-circle" src="UI/image/user_profile1.jpg" alt="">
                                    </div>
                                </button>
                                <!-- Dropdown menu -->
                                <div class="dropdown-menu dropdown-menu-end">
                                    <a class="dropdown-item" href="signup.jsp?request_id=1">Sign Up</a>
                                    <a class="dropdown-item" href="login.jsp">Log In</a>
                                    <div class="dropdown-divider"></div>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
</nav>
            <script>
            function getNotification() {
            $.ajax({
                url: "/In_Game_Items_Trading/GetNotificationController",
                type: 'GET',
                data: {
                },
                success: function(data){
                    var list = document.getElementById("noti-list");
                    list.innerHTML = data;
                }
            });
    }
        </script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js">
    </script>