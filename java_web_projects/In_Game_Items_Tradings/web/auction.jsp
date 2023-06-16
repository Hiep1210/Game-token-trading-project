<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Auction Page</title>
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
        <link rel="stylesheet" href="UI/css/styleBuy.css">
    </head>

    <body onload="countDown()">
        <c:set var="redirect" value="AuctionPageController"/>
        <%@include file="navbar.jsp" %>
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
                    <div class="filtexr sidebar">
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
                                <form action="SearchController" method = "post">
                                    <div class="form-group">
                                        <i class="fa-solid fa-magnifying-glass pt-3 ps-3"></i>
                                        <input type="text" name="page" value="${pageContext.request.servletPath}" hidden=""/>
                                        <input class="form-control ps-5" type="text" name ="search" placeholder="Enter the item or its skin's name or part of name">
                                        <input type="submit" hidden=""/>
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
                            <c:forEach var ="auction" items="${requestScope.auctionList}" varStatus="currentStatus">
                                <c:set var="gameItem" value="${auction.gameItem}"/>
                                <div class="col-lg-2 item-card mt-2 mb-2 dropdown" id="item-card">
                                    <div class="card dropdown-toggle" data-bs-toggle = "dropdown" aria-expanded="false">
                                        <img src="UI/image/${gameItem.getImg()}.png" alt ="displayfailed" class="card-img-top" >
                                        <div class="card-body">
                                            <h5 class="card-title item-card-price ps-1" >Start from :</h5>
                                            <h5 class="card-title item-card-price ps-1">$ ${auction.lowestBid}</h5>
                                            <h5 class="card-title ps-1" >Time left :</h5>
                                            <h5 class="card-title ps-1" id="countdown${currentStatus.index}"></h5>
                                            <a href="GetAuctionInfoController?auctionId=${auction.auctionId}" class="btn item-card-button">
                                                <i class="fa-solid fa-gavel"></i>
                                            </a>

                                        </div>
                                    </div>
                                    <div class="dropdown-menu dropdown-menu-end">
                                        <a class="dropdown-item" href="GetAuctionInfoController?auctionId=${auction.auctionId}">${gameItem.skin_name}</a>
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

        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>
        <script>
            function Redirect() {
                window.location.href = "GetNotificationController?redirect=DisplayMarketItemsController"
            }
        </script>
        <script>
            // Define the countdown details for each timer
            var countdowns = [
            <c:forEach var="auction"  items="${requestScope.auctionList}" varStatus="currentStatus">
            {name: "countdown${currentStatus.index}", endDate: "${auction.endingDate}"}
                <c:if test="${not currentStatus.last}">
            ,
                </c:if>
            </c:forEach>
            ];


            // Update all countdowns
            function updateCountdowns() {
                countdowns.forEach(function (countdown) {
                    var endDate = new Date(countdown.endDate);

                    var now = new Date();
                    var distance = endDate.getTime() - now.getTime();

                    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
                    // Display the countdown in the HTML element for when acution time time less than 1 minute 
                    if (days < 0 && hours < 0 & minutes < 0) {
                        var countdownElement = document.getElementById(countdown.name);
                        countdownElement.innerHTML = seconds + "s";
                        // Display the countdown in the HTML element for when acution time less than 1 hour 
                    } else if (days < 0 && hours < 0) {
                        var countdownElement = document.getElementById(countdown.name);
                        countdownElement.innerHTML = minutes + "m " + seconds + "s";
                        // Display the countdown in the HTML element for when acution time less than 1 day 
                    } else if (days < 0) {
                        var countdownElement = document.getElementById(countdown.name);
                        countdownElement.innerHTML = hours + "h " + minutes + " m";
                    } else {
                        // Display the countdown in the HTML element for when auction time left is more than 1 day
                        var countdownElement = document.getElementById(countdown.name);
                        countdownElement.innerHTML = days + "d " + hours + "h ";
                    }


                    // If the countdown is finished, update the HTML element
                    if (distance < 0) {
                        countdownElement.innerHTML = countdown.name + ": EXPIRED";
                    }
                });
            }

            // Update countdowns every 1 second
            var countdownInterval = setInterval(updateCountdowns, 1000);

            // Call updateCountdowns once immediately to display the initial countdowns
            updateCountdowns();
        </script>

    </body>

</html>
