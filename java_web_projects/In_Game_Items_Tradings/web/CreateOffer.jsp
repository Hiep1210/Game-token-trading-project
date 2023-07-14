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
        <!-- Main Content -->
        <form action="CreateOfferController" method="post">
        <div class="container-fluid">
            <div class="container-fluid w-75">
                <div class="row" id="trade" >
                    <!-- Item Card -->
                    <div class="col-md-6 offer">
                        <h4 class="card-text mb-1 text-center" >You Offer</h4>
                        <c:forEach var="offer" items="${requestScope.offer}" >
                            <input type="hidden" value="${offer.id}" name="offer"/>
                            <div class="sell-card mb-3" id="sell-card">
                                <div class="row g-0">
                                    <div class="col-md-2">
                                        <img src="UI/image/${offer.getImg()}.png" class="img-fluid rounded" alt="...">
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card-body">
                                            <h5 class="card-title mb-2">${offer.getType()} | ${offer.getItemName()} ${offer.getSkinName()} (${offer.getExterior()})
                                            </h5>
                                            <p class="card-text mb-1">Selling price: </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="col-md-6 receive">
                        <h4 class="card-text mb-1 text-center">You Receive</h4>
                        <c:forEach var="rec" items="${requestScope.rec}">
                            <input type="hidden" value="${rec.id}" name="receive"/>
                            <div class="sell-card mb-3" id="sell-card">
                                <div class="row g-0">
                                    <div class="col-md-2">
                                        <img src="UI/image/${rec.getImg()}.png" class="img-fluid rounded" alt="...">
                                    </div>
                                    <div class="col-md-6">
                                        <div class="card-body">
                                            <h5 class="card-title mb-2">${rec.getType()} | ${rec.getItemName()} ${rec.getSkinName()} (${rec.getExterior()})
                                            </h5>
                                            <p class="card-text mb-1">Selling price: </p>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="d-flex  align-items-center mt-2">
                        <p class="sell-info-select-name">Set your sell time</p>
                        <input class="mb-3 day" type="radio" name ="sellTime" value="1" checked="">
                        <label class="mb-3 ">1 Day</label>
                        <input class="mb-3 day" type="radio" name ="sellTime" value="2">
                        <label class="mb-3 ">2 Day</label>
                        <input class="mb-3 day" type="radio" name ="sellTime" value="3">
                        <label class="mb-3 ">3 Day</label>                        
                    </div>
                    <div>
                        <div class="d-flex align-items-center">
                            <label for="gacc">Game Account Name: </label>
                            <input style="margin-left:15px; background-color: rgb(28, 26, 36); color: wheat" type="text" placeholder="Your game account name" name="gAcc" required=""/>
                            <button type="submit" class="btn item-card-button" style="height: 80px; width: auto; margin-left: auto">
                                <b>Create Offer</b>
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        </form>
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

    </body>

</html>
