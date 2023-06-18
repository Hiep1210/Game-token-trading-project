<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
import {LocalDateTime} from '@js-joda/root/packages/core/src/LocalDateTime.js'
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

    <body onload="calc()">
        <c:set var="redirect" value="AuctionPageController?auctionId=${requestScope.auction.auctionId}"/>
        <%@include file="navbar.jsp" %>
        <!-- Main Content -->
        <div class="container-fluid main-content">
            <div class="row">
                <!-- Auction info Section -->
                <div class="col-lg-10 p-4">
                    <div class="container">
                        <!-- Auction info -->

                        <c:if test ="${requestScope.auction != null}">

                            <c:if test="${(!requestScope.isEnded)}">
                                <p>The auction is active!</p>
                            </c:if>
                            <c:if test="${requestScope.isEnded}">
                                <p>The auction has ended</p>
                            </c:if>

                                <c:if test="${not empty requestScope.errorMessage}">
                                    <p>${requestScope.errorMessage}</p>
                                </c:if>  
                                
                            <c:set var="gameItem" value="${requestScope.auction.gameItem}"/>
                            <%-- Show item information --%>
                            <img src="UI/image/${gameItem.img}.png" alt ="displayfailed" >
                            ${gameItem.skinName}        
                            ${gameItem.itemName}        
                            ${gameItem.type}        
                            ${gameItem.rarity}        
                            ${gameItem.exterior}        
                            <%-- let selller see this info, because is something that is initialised by him --%>
                            <c:if test="${requestScope.isSeller}">
                                <h5>Starting bid: ${auction.lowestBid} $ </h5>
                            </c:if>

                            <h5>Time left :<span id="countdown" ></span> </h5> 
                            
                            <h5>Number of bidders : <span>${requestScope.bidList.size()}</span> </h5>
                            <%-- showing appropriate messages if the auction has ended --%>
                            <c:if test="${requestScope.isEnded}">
                                <h5>The auction has ended. (Ending date ${auction.endingDate})</h5>
                                <c:if test="${not empty bidList}">
                                    <%-- if the final bidder is the current user show him a you won text --%>
                                    <c:if test="${requestScope.isHighestBidder}">
                                        <h5>You won the auction, final bid (<span>${bidList[0].amount} $</span>) was placed by you.</h5>
                                    </c:if>
                                    <%-- the viewer is not the one who buy it, so just show a simple message --%>
                                    <c:if test="${not requestScope.isHighestBidder}">
                                        <h5>Final bid: <span>${bidList[0].amount} $</span></h5>
                                    </c:if>
                                </c:if>
                            </c:if>



                            <%-- while the auction is running show data about bids --%>
                            <c:if test="${(not isEnded)}">
                                <%--show text for when auction has atleast 1 bid --%>
                                <c:if test="${not empty bidList}">
                                    <%-- first show the latest bid --%>
                                    <%-- if the user is the highest bidder --%>
                                    <c:if test="${sessionScope.user.id == biddersList[0].id}">
                                        <h5>Current highest bid (<span>${bidList[0].amount} $ </span>) was placed by you.</h5>
                                    </c:if>
                                    <%-- if the user is not the highest bidder --%>
                                    <c:if test="${sessionScope.user.id != biddersList[0].id}">
                                        <h5>Current highest bid <span>${bidList[0].amount} $ </span></h5>
                                    </c:if>

                                    <%-- let registered users to bid --%>
                                    <c:if test="${not requestScope.isSeller and not empty sessionScope.user}">
                                        <c:choose>
                                            <%-- if user is highest bidder set min value to bid is 0 --%>
                                            <c:when test="${requestScope.isHighestBidder}">
                                                <c:set var="min" value="1"/>
                                                <h3>You are the current highest bidder, the amount you need to add to your bid is : 0 $</h3>
                                            </c:when>
                                            <%-- if user is not highest bidder set min value to bid is highest current bidder - his bid + 1--%>
                                            <c:when test="${not requestScope.isHighestBidder}">
                                                <c:set var="min" value="${bidList[0].amount - requestScope.userBid.amount + 1}"/>
                                                <h3>The amount you need to add to your bid to win is : ${min} $</h3>
                                            </c:when>
                                            <%-- if user has not bid yet on this auction set min bid to highest bidder + 1 --%>
                                            <c:otherwise>
                                                <c:set var="min" value="${bidList[0].amount + 1}"/>
                                                <h3>The amount you need to bid to win is : ${min} $</h3>
                                            </c:otherwise>
                                        </c:choose>
                                        <form action="InsertBidController" method="post">
                                            <input type="hidden" name="auctionId" value="${requestScope.auction.auctionId}">
                                            <input type="hidden" name="bidId" value="${requestScope.userBid.bidId}">
                                            <input type="number" step="any" min="${min}" value="${min}" name="bidAmount">
                                            <button type="submit" name="action" value="Bid">Bid for this item</button>
                                        </form>
                                    </c:if>
                                </c:if>

                                <%-- show text for when no one has bid this auction --%>
                                <c:if test="${empty bidList}">
                                    <%-- if no on has bid on this auction --%>
                                    <c:if test="${not requestScope.isSeller and not empty sessionScope.user}">
                                        <h5>No bids placed yet.</h5>
                                        <form action="InsertBidController" method="post">
                                            <input type="number" step="any" min="${requestScope.auction.lowestBid}" value="${requestScope.auction.lowestBid}" name="bidAmount">
                                            <input type="hidden" name="auctionId" value="${requestScope.auction.auctionId}">
                                            <button class="button-primary" type="submit" name="action" value="bidAuction">Make the first bid</button>
                                        </form>
                                    </c:if>

                                    <c:if test="${requestScope.isSeller or empty user.id}">
                                        <h5>Auction has no bids yet.</h5>
                                    </c:if>
                                </c:if>
                            </c:if>

                        </c:if> 
                        <c:if test ="${(requestScope.auction == null)}">
                            <h3>Auction does not exist or is deleted!</h3>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <script>
            // Define the countdown details for each timer
            var countdowns = [
                {name: "countdown", endDate: "${auction.endingDate}"}
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

                    // Display the countdown in the HTML element
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
