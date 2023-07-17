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
        <link rel="stylesheet" href="UI/css/styleBuy.css">
        <link rel="stylesheet" href="UI/css/styleInput.css"/>
        <link rel="stylesheet" href="UI/css/styleItemBox.css"/>
    </head>

    <body>
        <c:set var="redirect" value="BuyPageController"/>
        <%@include file="navbar.jsp" %>
        <div class="container-fluid main-content">
            <div class="row">
                <!-- Filter Section -->
                <div class="col-lg-2 flex-column p-4">
                    <!-- Cart Info Section -->
                    <div class="cart-info">
                        <button class="buy-button">
                            <h5><a href="ViewCartController?id=${sessionScope.user.id}">View Your Cart</a></h5>
                        </button>
                    </div>
                    <!-- Filter Selection Section -->
                    <div class="filter sidebar">
                        <div class="container">
                            <form action="SortBuyController" method="post">
                                <!-- Filter Type -->
                                <details class="sidebar-category">
                                    <summary>Type</summary>
                                    <ul class="nopadding d-flex flex-column">
                                        <div class="category-group">
                                            <input type="checkbox" name="type" value="knife" id="knife">
                                            <label for="knife">Knife</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="type" value="glove" id="glove">
                                            <label for="glove">Glove</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="type" value="pistol" id="gun">
                                            <label for="gun">Pistol</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="type" value="Heavy" id="gun">
                                            <label for="gun">Heavy</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="type" value="Rifle" id="gun">
                                            <label for="gun">Rifle</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="type" value="SMGs" id="gun">
                                            <label for="gun">SMGs</label>
                                        </div>
                                    </ul>
                                </details>
                                <!-- Filter Exterior -->
                                <details class="sidebar-category">
                                    <summary>Exterior</summary>
                                    <ul class="nopadding d-flex flex-column">
                                        <div class="category-group">
                                            <input type="checkbox" name="exterior" value="Factory New" id="fn">
                                            <label for="knife">Factory New</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="exterior" value="Minimal Wear" id="mw">
                                            <label for="glove">Minimal Wear</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="exterior" value="Field-Tested" id="ft">
                                            <label for="gun">Field-Tested</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="exterior" value="Well-Worn" id="ww">
                                            <label for="gun">Well-Worn</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="exterior" value="Battle-Scarred" id="bs">
                                            <label for="gun">Battle-Scarred</label>
                                        </div>
                                    </ul>
                                </details>
                                <!-- Filter Rarity -->
                                <details class="sidebar-category">
                                    <summary>Rarity</summary>
                                    <ul class="nopadding d-flex flex-column">
                                        <div class="category-group">
                                            <input type="checkbox" name="rarity" value="Consumer Grade" id="grey">
                                            <label for="knife">Consumer Grade</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="rarity" value="Industrial Grade" id="blue">
                                            <label for="glove">Industrial Grade</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="rarity" value="Restricted" id="purple">
                                            <label for="gun">Restricted</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="rarity" value="Classified" id="pink">
                                            <label for="gun">Classified</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="checkbox" name="rarity" value="Covert" id="red">
                                            <label for="gun">Covert</label>
                                        </div>
                                    </ul>
                                </details>
                                <details class="sidebar-category">
                                    <summary>Price</summary>
                                    <ul class="nopadding d-flex flex-column">
                                        <div class="category-group">
                                            <input type="radio" name="priceorder" value="asc" id="grey">
                                            <label for="sort">Sort Price Ascending</label>
                                        </div>
                                        <div class="category-group">
                                            <input type="radio" name="priceorder" value="desc" id="blue">
                                            <label for="sort">Sort Price Descending</label>
                                    </ul>
                                </details>
                                <button type="submit" class="btn item-card-button">
                                    Find
                                </button>
                            </form>
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
                                <form action="SearchBuyController" method = "post">
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
                            <c:if test="${requestScope.marketlist.size() == 0}">
                                <h1 style="text-align: center; color: grey">Found 0 result</h1>
                            </c:if>
                            <c:forEach var ="market_items" items="${requestScope.marketlist}" varStatus="currentStatus">
                                <!-- Item Card -->
                                <div class="col-lg-2 item-card mt-2 mb-2 " id="item-card" data-bs-toggle="offcanvas" href="#offcanvas${market_items.id}">
                                    <div class="card rarity-${market_items.getRarity().toLowerCase()}"">
                                        <img src="UI/image/${market_items.getImg()}.png" alt ="displayfailed" class="card-img-top" >
                                        <div class="card-body">
                                            <h5 class="card-title item-card-price ps-1">$ ${market_items.price}</h5>
                                        </div>
                                    </div>
                                </div>

                                <!-- Item Details -->
                                <div class="offcanvas offcanvas-start" data-bs-theme="dark" tabindex="-1" id="offcanvas${market_items.id}"
                                     aria-labelledby="offcanvas">
                                    <div class="offcanvas-header">
                                        <h5 class="offcanvas-title" id="offcanvas">
                                            ${market_items.type} | ${market_items.skinName}
                                        </h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="offcanvas-body">
                                        <img class="img-fluid" src="UI/image/${market_items.getImg()}.png" alt="">
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Exterior:</p>
                                            <h5>${market_items.exterior}</h5>
                                        </div>
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Rarity:</p>
                                            <h5>${market_items.rarity}</h5>
                                        </div>
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Time Left:</p>
                                            <h5 id="countdown${currentStatus.index}"></h5>
                                        </div>
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Sell Price:</p>
                                            <h5>$ ${market_items.price}</h5>
                                        </div>
                                            <c:if test="${sessionScope.user != null }">
                                            <div class="summit-button mt-2">
                                                <c:if test="${sessionScope.user.id != market_items.userid }">
                                                <button class="btn item-card-button"  onclick="addCart(${market_items.id},${sessionScope.user.id})" id="button-cart${market_items.id}">
                                                    <i class="fa-solid fa-cart-shopping"></i>
                                                </button>
                                                </c:if>
                                            </div>
                                        </c:if>
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
        <script>
            //load page before display message
            window.onload = function () {
                if (${requestScope.message != null}) {
                    document.title = "GIT";
                    alert('${requestScope.message}');
                }
            };
        </script>
        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script>
            function addCart(market, buyer) {
                console.log(market + "+" + buyer)
                $.ajax({
                    url: "/In_Game_Items_Trading/AddCartController",
                    type: 'POST',
                    data: {
                        marketid: market,
                        buyerid: buyer
                    },
                    success: function (data) {
                        var button = document.getElementById("button-cart" + market);
                        button.innerHTML = data;
                    }
                });
            }
        </script>

        <script>
            // Define the countdown details for each timer
            var countdowns = [
            <c:forEach var="market" items="${requestScope.marketlist}" varStatus="currentStatus">
            {name: "countdown${currentStatus.index}", endDate: "${market.enddate}"}
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

                        var countdownElement = document.getElementById(countdown.name);
                        countdownElement.innerHTML = days + "d " + hours + "h " + minutes + "m " + seconds + "s ";

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