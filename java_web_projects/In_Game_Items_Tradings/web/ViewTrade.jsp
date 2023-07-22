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
        <link rel="stylesheet" href="UI/css/styleInput.css"/>
        <link rel="stylesheet" href="UI/css/styleItemBox.css"/>
        <style>
            h4, label, p{
                color: white
            }
        </style>
    </head>

    <body>
        <c:set var="redirect" value="BuyPageController"/>
        <%@include file="navbar.jsp" %>
        <script>
            //load page before display message
            window.onload = function () {
                if (${requestScope.mess != null}) {
                    document.title = "GIT";
                    alert('${requestScope.mess}');
                }
            };
        </script>
        <!-- Main Content -->
        <div class="container-fluid">
            <div class="container-fluid w-75">
                <c:forEach var="trade"  items="${requestScope.trade}" varStatus="currentStatus">
                    <form id="del" onsubmit="return confirm('Are You sure you want to delete offer')" action="DeleteOfferController" method="post"  >
                        <input type="hidden" value="${trade.id}" name="trad"/>
                    </form>
                    <form action="ProcessTradeController" method="post">
                        <input type="hidden" value="${trade.id}" name="trade"/>
                        <input type="hidden" value="${trade.creator.id}" name="sender"/>
                        <div class="row mb-3" id="trade" >
                            <!-- Item Card -->
                            <div class="col-md-6 offer">
                                <h4 class="card-text mb-1 text-center" >You Receive</h4>
                                <c:forEach var="offer" items="${requestScope.offer[trade.id]}" >
                                    <input type="hidden" value="${offer.id}" name="offer"/>
                                    <div class="sell-card mb-3" id="sell-card">
                                        <div class="row g-0">
                                            <div class="col-md-2">
                                                <img src="UI/image/${offer.give.getImg()}.png" class="img-fluid rounded" alt="...">
                                            </div>
                                            <div class="col-md-6">
                                                <div class="card-body">
                                                    <h5 class="card-title mb-2">${offer.give.getType()} | ${offer.give.getItemName()} ${offer.give.getSkinName()} (${offer.give.getExterior()})
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="col-md-6 receive">
                                <h4 class="card-text mb-1 text-center">You Offer</h4>
                                <c:forEach var="rec" items="${requestScope.rec[trade.id]}">
                                    <input type="hidden" value="${rec.id}" name="receive"/>
                                    <div class="sell-card mb-3" id="sell-card">
                                        <div class="row g-0">
                                            <div class="col-md-2">
                                                <img src="UI/image/${rec.rec.getImg()}.png" class="img-fluid rounded" alt="...">
                                            </div>
                                            <div class="col-md-6">
                                                <div class="card-body">
                                                    <h5 class="card-title mb-2">${rec.rec.getType()} | ${rec.rec.getItemName()} ${rec.rec.getSkinName()} (${rec.rec.getExterior()})
                                                    </h5>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div>
                                <p>From: ${trade.gAcc}</p>
                                <p class="card-text mb-1">Time Left: <span id="countdown${currentStatus.index}"></span></p>
                                <div class="d-flex align-items-center">
                                    <c:if test="${sessionScope.user.id == trade.creator.id}">
                                        <button type="submit"  form="del" class="btn item-card-button" style="height: 80px; width: auto; margin-left: auto; background-color: red">
                                            <b>Delete Offer</b>
                                        </button>
                                    </c:if>
                                    <c:if test="${sessionScope.user.id != trade.creator.id}">
                                        <input class="hidden" id="gameAccInput${trade.id}" style="margin-left:15px; background-color: rgb(28, 26, 36); color: wheat" type="text" placeholder="Your game account name" name="gAcc" required="" />
                                        <button type="submit" onclick="showGameAccInput(${trade.id})" class="btn item-card-button" style="height: 80px; width: auto; margin-left: auto">
                                            <b>Accept Offer</b>
                                        </button>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </div>
        </div>
        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script>
            function showGameAccInput(trade)

            {
                var element = document.getElementById("gameAccInput" + trade);
                if (element) {
                    var input = Array.from(element.classList);
                    if (input.includes("hidden")) {
                        element.classList.remove("hidden");
                        console.log(element.classList);
                    } else {
                        element.classList.add("hidden");
                        console.log(element.classList);
                    }
                }
            }
        </script>
        <script>
            // Define the countdown details for each timer
            var countdowns = [
            <c:forEach var="cart" items="${requestScope.trade}" varStatus="currentStatus">
            {name: "countdown${currentStatus.index}", endDate: "${cart.end}"}
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
