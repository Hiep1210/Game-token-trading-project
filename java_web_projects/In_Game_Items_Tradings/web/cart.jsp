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
    </head>

    <body>
        <c:set var="redirect" value="BuyPageController"/>
        <%@include file="navbar.jsp" %>
        <!-- Main Content -->
        <div class="container-fluid">
            <h1 class="card-title mb-4" id="cart-size">Your Cart Item(s): ${requestScope.clist.size()} items!</h1>
            <c:if test="${requestScope.message != null}">
                <h2>${requestScope.message}</h2>
            </c:if>
            <c:set var="total" value="0"/>
            <div class="container-fluid w-75">
                <div class="row" id="sell-card-box">
                    <c:forEach var ="cartlist" items="${requestScope.clist}" varStatus="currentStatus">
                        <c:set var="total" value="${cartlist.price + total}"/>
                        <!-- Item Card -->
                        <div class="sell-card mb-3" id="sell-card">
                            <div class="row g-0">
                                <div class="col-md-2">
                                    <img src="UI/image/${cartlist.getImg()}.png" class="img-fluid rounded" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title mb-2">${cartlist.getType()} | ${cartlist.getItemName()} ${cartlist.getSkinName()} (${cartlist.getExterior()})
                                        </h5>
                                        <p class="card-text mb-1">Selling price: ${cartlist.getPrice()}</p>
                                        <p class="card-text mb-1">Time Left: <p id="countdown${currentStatus.index}"></p></p>
                                          
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="row nopadding">
                                        <div class="mx-1 my-3">
                                            <form action="ProcessCartController" method="post" onsubmit="return confirm('Are you sure you want to buy only this item from cart? ')">
                                                <input type="text" name="cartId" value="${cartlist.id}" hidden=""/>
                                                <button onclick="showGameAccInput(${cartlist.id})" type="submit" class="btn item-card-button ">
                                                    <h5 class="card-title ps-1">Buy</h5>
                                                </button>
                                                <input id="gameAccInput${cartlist.id}" class="form-control hidden" type="text" name="gameAccountName" placeholder="Game account name ..." required>
                                            </form>
                                        </div>
                                        <div class="mx-1">
                                            <form action="DeleteCartController" method="post" onsubmit="return confirm('Are you sure you want to remove this item from cart? ')">
                                                <input type="text" name="id" value="${cartlist.id}" hidden=""/>
                                                <button type="submit"  class="btn item-card-button red-button">
                                                    <h5 class="card-title ps-1">Remove</h5>
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <c:if test="${requestScope.clist !=null}">
                <div class="row">
                    <h4 class="card-title text-end">The total amount in your cart: ${total} $</h4>
                    <form class="d-flex justify-content-end" action="ProcessCartController" method="post" onsubmit="return confirm('Are you sure you want to buy all item(s) from cart? ')">
                        <input class="form-control w-25 me-3" type="text"  placeholder="Game acount name..."name="gameAccountName" required="">    
                        <button type="submit" class="btn item-card-button w-25">
                            <h5 class="card-title item-card-price ps-1">Buy All</h5>
                        </button>
                    </form>
                </div>
            </c:if>
        </div>

        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>
        <script>
            function showGameAccInput(cartItemId) {
                var element = document.getElementById("gameAccInput" + cartItemId);
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
            <c:forEach var="cart" items="${requestScope.clist}" varStatus="currentStatus">
            {name: "countdown${currentStatus.index}", endDate: "${cart.enddate}"}
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
