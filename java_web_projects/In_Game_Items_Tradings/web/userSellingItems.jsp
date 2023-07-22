<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User's Sellings</title>
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
            <div class="container-fluid w-50">
                <h1 class="text-white fw-bold">Items on Market:</h1>
                <div class="row" id="sell-card-box">
                    <c:forEach var ="sellingList" items="${requestScope.sellingList}" varStatus="currentStatus">
                        <!-- Item Card -->
                        <div class="sell-card mb-3" id="sell-card">
                            <div class="row g-0">
                                <div class="col-md-2">
                                    <img src="UI/image/${sellingList.getImg()}.png" class="img-fluid rounded" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title mb-2">${sellingList.getType()} | ${sellingList.getItemName()} ${sellingList.getSkinName()} (${sellingList.getExterior()})
                                        </h5>
                                        <p class="card-text mb-1">Selling price: ${sellingList.getPrice()}</p>
                                        <p class="card-text mb-1">Time Left: <span id="countdownSelling${currentStatus.index}"></span></p>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="row nopadding">
                                        <div class="mx-1 my-3">
                                            <a onclick="deleteUserMarketItems('${sellingList.getId()}');" class="btn btn-danger w-100">
                                                <i class="fa-solid fa-trash"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <h1 class="text-white fw-bold">Items waiting for Admin approval:</h1>
                <div class="row" id="sell-card-box">
                    <c:forEach var ="processList" items="${requestScope.processList}" varStatus="currentStatus">
                        <!-- Item Card -->
                        <div class="sell-card mb-3" id="sell-card">
                            <div class="row g-0">
                                <div class="col-md-2">
                                    <img src="UI/image/${processList.getImg()}.png" class="img-fluid rounded" alt="...">
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title mb-2">${processList.getType()} | ${processList.getItemName()} ${processList.getSkinName()} (${processList.getExterior()})
                                        </h5>
                                        <p class="card-text mb-1">Selling price: ${processList.getPrice()}</p>
                                        <p class="card-text mb-1">Time Left: <span id="countdownProcessing${currentStatus.index}"></span></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
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
            <c:forEach var="sellingListItem" items="${requestScope.sellingList}" varStatus="currentStatus">
            {name: "countdownSelling${currentStatus.index}", endDate: "${sellingListItem.getEnddate()}"}
            ,
            </c:forEach>
            <c:forEach var="processList" items="${requestScope.processList}" varStatus="currentStatus">
            {name: "countdownProcessing${currentStatus.index}", endDate: "${processList.getEnddate()}"}
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

        <script>
            function deleteUserMarketItems(itemId) {
                var userConfirmed = confirm('Are you sure you want to delete this item from the market?');
                if (userConfirmed) {
                    $.ajax({
                        url: '/In_Game_Items_Trading/DeleteUserMarketItems',
                        method: 'POST',
                        data: {
                            itemId: itemId
                        },
                        success: function () {
                            window.location.reload();
                        }
                    });
                }
            }
        </script>
    </body>

</html>
