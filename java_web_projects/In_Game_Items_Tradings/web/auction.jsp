<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Auction page</title>
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
        <%@include file="navbar.jsp" %>
        <!-- Main Content -->
        <c:if test="${requestScope.errorMessage != null}">
            <h2 style="color: plum">${requestScope.errorMessage}</h2>
        </c:if>
        <div class="container-fluid">
            <div class="row"> 
                <div class="col-lg-5">
                    <div class="container-fluid">
                        <div class="row">
                            <c:forEach var ="auction" items="${requestScope.joinedAuctionList}" varStatus="currentStatus">
                                <c:set var="gameItem" value="${auction.gameItem}"/>
                                <!-- Item Card -->
                                <div class="sell-card mb-3"  id="item-card">
                                    <div class="row g-0">
                                        <div class="col-md-2">
                                            <img src="UI/image/${gameItem.img}.png" class="img-fluid rounded" alt="...">
                                        </div>
                                        <div class="col-md-7">
                                            <div class="card-body">
                                                <h5 class="card-title mb-2"> ${gameItem.itemName} | ${gameItem.skinName}</h5>
                                                <c:if test="${sessionScope.user.id == auction.bidList.get(0).bidderId}">
                                                    <p class="card-text mb-1">Current highest bid: <span  style="color: greenyellow"> ${auction.bidList.get(0).amount} $ was placed by you.</span></p>
                                                </c:if>

                                                <c:if test="${sessionScope.user.id != auction.bidList.get(0).bidderId}">
                                                    <p class="card-text mb-1">Current highest bid: <span style="color: red" > ${auction.bidList.get(0).amount} $ </span></p>
                                                </c:if>    
                                                <p class="card-text mb-1">Time Left: <span id="countdownJoined${currentStatus.index}"></span></p>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="row nopadding">
                                                <div class="mx-1"  data-bs-toggle="offcanvas" href="#offcanvas${auction.auctionId}">
                                                    <button class="btn item-card-button red-button">
                                                        <i class="fa-solid fa-gavel"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="col-lg-2 flex-column p-4">
                    <div class="col-lg-12 search-bar form">
                        <form action="SearchAuctionController" method = "post">
                            <div class="form-group">
                                <i class="fa-solid fa-magnifying-glass pt-3 ps-3"></i>
                                <input class="form-control ps-5" type="text" name ="search" placeholder="Enter the item or its skin's name or part of name">
                                <input type="submit" hidden=""/>
                            </div>
                        </form>
                    </div>
                    <!-- Cart Info Section -->
                    <div class="cart-info">
                        <c:if test="${sessionScope.user.id != null}">
                            <button class="buy-button">
                                <h5><a href="ViewAuctionCreatedController">View Your Auctions</a></h5>
                            </button> 
                        </c:if>
                    </div>
                    <!-- Filter Selection Section -->
                    <div class="filter sidebar">
                        <div class="container">
                            <form action="SortAuctionController" method="post">
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

                <div class="col-lg-5">
                    <div class="container-fluid">
                        <div class="row" id="item-box">
                            <c:forEach var ="auction" items="${requestScope.notJoinedAuctionList}" varStatus="currentStatus">
                                <c:set var="gameItem" value="${auction.gameItem}"/>
                                <!-- Item Card -->
                                <div class="sell-card mb-3" id="sell-card">
                                    <div class="row g-0">
                                        <div class="col-md-2">
                                            <img src="UI/image/${gameItem.img}.png" class="img-fluid rounded" alt="...">
                                        </div>
                                        <div class="col-md-7">
                                            <div class="card-body">
                                                <h5 class="card-title mb-2">${gameItem.itemName} | ${gameItem.skinName}</h5>
                                                <p class="card-text mb-1">Current bid:
                                                    <c:choose>
                                                        <%-- if no on has bid on this auction --%>
                                                        <c:when test="${empty auction.bidList}">
                                                            ${auction.lowestBid}
                                                        </c:when>
                                                        <%-- if someone has bid this auction --%>
                                                        <c:otherwise>          
                                                            ${auction.bidList.get(0).amount}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                                <p class="card-text mb-1">Time Left: <span id="countdownNotJoined${currentStatus.index}"></span></p>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="row nopadding">
                                                <div class="mx-1"  data-bs-toggle="offcanvas" href="#offcanvas${auction.auctionId}">
                                                    <button class="btn item-card-button red-button">
                                                        <i class="fa-solid fa-gavel"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <!-- Item details -->
                <div class="col-lg-10 p-4">
                    <div class="container">
                        <!-- Item List -->
                        <div class="row" id="item-box">
                            <c:forEach var ="auction" items="${requestScope.joinedAuctionList}" varStatus="currentStatus">
                                <c:set var="gameItem" value="${auction.gameItem}"/>
                                <!-- Item Details -->
                                <div class="offcanvas offcanvas-start" data-bs-theme="dark" tabindex="-1" id="offcanvas${auction.auctionId}"
                                     aria-labelledby="offcanvas">
                                    <div class="offcanvas-header">
                                        <h5 class="offcanvas-title" id="offcanvas">
                                            ${gameItem.type} | ${gameItem.skinName}
                                        </h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="offcanvas-body">
                                        <img class="img-fluid" src="UI/image/${gameItem.img}.png" alt="">
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Exterior:</p>
                                            <h5>${gameItem.exterior}</h5>
                                        </div>
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Rarity:</p>
                                            <h5>${gameItem.rarity}</h5>
                                        </div>
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Time Left:</p>
                                            <h5 id="sideCountdownJoined${currentStatus.index}"></h5>
                                        </div>
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Number of bidders:</p>
                                            ${auction.bidList.size()}
                                        </div>
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Current highest bid:</p>
                                            ${auction.bidList.get(0).amount} $
                                        </div>
                                        <c:if test="${sessionScope.user != null}">

                                            <c:choose>
                                                <%-- if user is highest bidder set min value to bid is 0 --%>
                                                <c:when test="${sessionScope.user.id == auction.bidList.get(0).bidderId }">
                                                    <c:set var="min" value="1"/>

                                                    <c:set var="bidId" value="${auction.bidList.get(0).bidId}"/>
                                                    <c:set var="gameAccountName" value="${auction.bidList.get(0).gameAccountName}"/>
                                                    <div class="d-flex justify-content-between mt-2">
                                                        <p class="sell-info-select-name">Your current bid :</p>
                                                        ${auction.bidList.get(0).amount} $
                                                    </div>
                                                    <div class="d-flex justify-content-between mt-2">
                                                        <p class="sell-info-select-name">Game account used:</p>
                                                        ${auction.bidList.get(0).gameAccountName}
                                                    </div>
                                                    <h3>You are the current highest bidder, the amount you need to add to your bid is : 0 $</h3>
                                                </c:when>
                                                <%-- if user is not highest bidder set min value to bid is highest current bidder - his bid + 1--%>
                                                <c:otherwise>          
                                                    <c:forEach var ="userBid" items="${auction.bidList}">
                                                        <c:if test="${userBid.bidderId == sessionScope.user.id}">
                                                            <c:set var="min" value="${auction.bidList.get(0).amount - userBid.amount + 1}"/>
                                                            <c:set var="bidId" value="${userBid.bidId}"/>
                                                            <c:set var="gameAccountName" value="${userBid.gameAccountName}"/>
                                                            <div class="d-flex justify-content-between mt-2">
                                                                <p class="sell-info-select-name">Your current bid :</p>
                                                                ${userBid.amount} $
                                                            </div>
                                                            <div class="d-flex justify-content-between mt-2">
                                                                <p class="sell-info-select-name">Game account used to bid:</p>
                                                                ${userBid.gameAccountName}
                                                            </div>
                                                        </c:if>
                                                    </c:forEach>
                                                    <h3>The amount you need to add to your bid is : ${min}$</h3>
                                                </c:otherwise>
                                            </c:choose>
                                            <form action="InsertBidController" method="post">
                                                <input type="hidden" name="bidId" value="${bidId}">
                                                <input type="hidden" name="gameAccountName" value="${gameAccountName}">
                                                <input type="hidden" name="auctionId" value="${auction.auctionId}">
                                                <input type="number" step="any" min="${min}" value="${min}" name="bidAmount">
                                                <div class="summit-button mt-2">
                                                    <button type="submit" name="action" value="Bid"><i class="fa-solid fa-gavel"></i>Bid for this item</button>
                                                </div>
                                            </form>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>

                            <c:forEach var ="auction" items="${requestScope.notJoinedAuctionList}" varStatus="currentStatus">
                                <c:set var="gameItem" value="${auction.gameItem}"/>
                                <!-- Item Details -->
                                <div class="offcanvas offcanvas-start" data-bs-theme="dark" tabindex="-1" id="offcanvas${auction.auctionId}"
                                     aria-labelledby="offcanvas">
                                    <div class="offcanvas-header">
                                        <h5 class="offcanvas-title" id="offcanvas">
                                            ${gameItem.type} | ${gameItem.skinName}
                                        </h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                                aria-label="Close"></button>
                                    </div>
                                    <div class="offcanvas-body">
                                        <img class="img-fluid" src="UI/image/${gameItem.img}.png" alt="">
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Exterior:</p>
                                            <h5>${gameItem.exterior}</h5>
                                        </div>
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Rarity:</p>
                                            <h5>${gameItem.rarity}</h5>
                                        </div>
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Time Left:</p>
                                            <h5 id="sideCountdownNotJoined${currentStatus.index}"></h5>
                                        </div>
                                        <div class="d-flex justify-content-between mt-2">
                                            <p class="sell-info-select-name">Number of bidders :</p>
                                            ${auction.bidList.size()}
                                        </div>     
                                        <c:if test="${sessionScope.user != null}">

                                            <c:choose>
                                                <%-- if no on has bid on this auction --%>
                                                <c:when test="${empty auction.bidList}">
                                                    <div class="d-flex justify-content-between mt-2">
                                                        <p class="sell-info-select-name">Starting bid price :</p>
                                                        ${auction.lowestBid} $
                                                    </div>  
                                                    <c:set var="min" value="${auction.lowestBid}"/>
                                                    <h5>Be the first one to bid!</h5>
                                                </c:when>
                                                <%-- if someone has bid this auction --%>
                                                <c:otherwise>
                                                    <div class="d-flex justify-content-between mt-2">
                                                        <p class="sell-info-select-name">Current highest bid :</p>
                                                        ${auction.bidList.get(0).amount} $
                                                    </div>  
                                                    <h3>The amount you need to add to your bid is : ${auction.bidList.get(0).amount + 1}$</h3>
                                                    <c:set var="min" value="${auction.bidList.get(0).amount + 1}"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <form action="InsertBidController" method="post">
                                                <input type="hidden" name="auctionId" value="${auction.auctionId}">      
                                                <input type="number" step="any" min="${min}" value="${min}" name="bidAmount">
                                                <input class="form-contro" type="text"  placeholder="Game acount name..."name="gameAccountName" required="">                          
                                                <div class="summit-button mt-2">
                                                    <button type="submit" name="action" value="Bid"><i class="fa-solid fa-gavel"></i>Bid for this item</button>
                                                </div>
                                            </form>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- Page Navigator -->
                    </div>
                </div>
            </div>
        </div>

        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

        <script>
            // Define the countdown details for each timer
            var countdowns = [
            <c:forEach var="auction"  items="${requestScope.joinedAuctionList}" varStatus="currentStatus">
            {name: "countdownJoined${currentStatus.index}", endDate: "${auction.endingDate}"},
            {name: "sideCountdownJoined${currentStatus.index}", endDate: "${auction.endingDate}"
            }
            ,
            </c:forEach>
            <c:forEach var="auction"  items="${requestScope.notJoinedAuctionList}" varStatus="currentStatus">
            {name: "countdownNotJoined${currentStatus.index}", endDate: "${auction.endingDate}"
            }
            ,
            {name: "sideCountdownNotJoined${currentStatus.index}", endDate: "${auction.endingDate}"}
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
