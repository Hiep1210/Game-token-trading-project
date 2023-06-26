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
        <link rel="stylesheet" href="UI/css/styleInput.css">
        <link rel="stylesheet" href="UI/css/styleItemBox.css">
        <link rel="stylesheet" href="UI/css/styleSell.css">
    </head>

    <body>
        <%@include file="navbar.jsp" %>

        <!-- Page Main Content -->
        <div class="container-fluid">
            <div class="row">
                <!-- Item List -->
                <div class="col-lg-8">
                    <!-- Tool bar -->
                    <div class="row mt-2">
                        <!-- Search Bar -->
                        <div class="col-lg-3 search-bar form">
                            <div class="form-group">
                                <i class="fa-solid fa-magnifying-glass pt-3 ps-3"></i>
                                <input oninput="filterByType()" id="search-input" class="form-control ps-5" type="text" placeholder="Search...">
                            </div>
                        </div>
                        <!-- Filter By Type -->
                        <div class="col-lg-1 filter-type">
                            <div class="btn-group">
                                <button type="button" class="btn btn-danger">Type</button>
                                <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    <span class="visually-hidden">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end">
                                    <li class="dropdown-item"><input type="checkbox" name="knife" value="knife" checked>Knife</li>
                                    <li class="dropdown-item"><input type="checkbox" name="pistol" value="pistol" checked>Pistol</li>
                                    <li class="dropdown-item"><input type="checkbox" name="rifle" value="rifle" checked>Rifle</li>
                                    <li class="dropdown-item"><input type="checkbox" name="smg" value="smg" checked>SMGs</li>
                                    <li class="dropdown-item"><input type="checkbox" name="heavy" value="heavy" checked>Heavy</li>
                                    <li>
                                        <hr class="dropdown-divider">
                                    </li>
                                    <li><a class="dropdown-item" style="color: rgb(87, 242, 135)" id="select-all"">Select All</a></li>
                                    <li><a class="dropdown-item" style="color: rgb(218, 100, 123)" id="reset-all">Reset</a></li>
                                    <li><a class="dropdown-item" style="color: rgb(128, 108, 245)" onclick="filterByType()">Save Filter</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- Sort by Rarity -->
                        <div class="col-lg-2">
                            <div class="btn-group">
                                <button type="button" class="btn btn-danger" id="rarity-sort">Rarity</button>
                                <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split"
                                        data-bs-toggle="dropdown" aria-expanded="false">
                                    <span class="visually-hidden">Toggle Dropdown</span>
                                </button>
                                <ul class="dropdown-menu dropdown-menu-end">
                                    <li class="dropdown-item" onclick="filterByType('asc', this)">Rarest First</li>
                                    <li class="dropdown-item" onclick="filterByType('desc', this)">Common First</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- Item List -->
                    <div class="row" id="list-content">
                        <c:forEach var ="sellList" items="${requestScope.sellList}">
                            <!-- Item Card -->
                            <div class="col-lg-2 item-card mt-2 mb-2" id="item-card" data-bs-toggle="offcanvas" href="#offcanvas${sellList.trimedSkinName}">
                                <div class="card rarity-${sellList.gameItems.rarity.toLowerCase()}" data-bs-toggle = "dropdown" aria-expanded="false">
                                    <img src="UI/image/${sellList.gameItems.img}.png" alt ="displayfailed" class="card-img-top">
                                    <div class="card-body">
                                        <p>${sellList.gameItems.type} | ${sellList.gameItems.itemName} ${sellList.gameItems.skinName}</p>
                                        <a href="#" class="btn item-card-button">
                                            <i class="fa-solid fa-cart-shopping"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <!-- Item Details -->
                            <div class="offcanvas offcanvas-start" data-bs-theme="dark" tabindex="-1" id="offcanvas${sellList.trimedSkinName}"
                                 aria-labelledby="offcanvas${sellList.gameItems.img}">
                                <div class="offcanvas-header">
                                    <h5 class="offcanvas-title" id="offcanvas">
                                        ${sellList.gameItems.type} | ${sellList.gameItems.itemName} ${sellList.gameItems.skinName}
                                    </h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                            aria-label="Close"></button>
                                </div>
                                <div class="offcanvas-body">
                                    <img class="img-fluid" src="UI/image/${sellList.gameItems.img}.png" alt="">
                                    <div class="d-flex justify-content-between mt-2">
                                        <p class="sell-info-select-name">Extrior:</p>
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
                                    </div>
                                    <div class="d-flex justify-content-between mt-2">
                                        <p class="sell-info-select-name">Rarity:</p>
                                        <h5>${sellList.gameItems.rarity}</h5>
                                    </div>
                                    <div class="d-flex justify-content-between align-items-center mt-2">
                                        <p class="sell-info-select-name">Sell time:</p>
                                        <input class="mb-3" type="radio" name ="sellTime">
                                        <label class="mb-3">1 Day</label>
                                        <input class="mb-3" type="radio" name ="sellTime">
                                        <label class="mb-3">2 Day</label>
                                        <input class="mb-3" type="radio" name ="sellTime">
                                        <label class="mb-3">3 Day</label>
                                    </div>
                                    <div class="d-flex justify-content-between mt-2">
                                        <p class="sell-info-select-name">Sell Price:</p>
                                        <div class="form-group">
                                            <input type="number" name="page" value="${pageContext.request.servletPath}" hidden=""/>
                                            <input class="form-control" type="text" name ="search" placeholder="Enter the price">
                                            <input type="submit" hidden=""/>
                                        </div>
                                    </div>
                                    <c:if test="${sessionScope.user != null}">
                                        <div class="summit-button mt-2">
                                            <button class="btn item-card-button"  onclick="addSellList(${sell_items.id},${sessionScope.user.id})" id="button-cart${sell_items.id}">
                                                <i class="fa-solid fa-cart-shopping"></i>
                                            </button>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>    
                    </div>
                    <div>
                        <button onclick="loadMore()" class="btn btn-danger" id="load-button">Load More</button>
                    </div>
                </div>
                <!-- Sell List -->
                <div class="col-lg-4">
                    <form class="form">
                        <div class="sell-header">
                            <h1 class="form-label">Sell List</h1>
                        </div>
                        <div class="container">
                            <div class="row" id="sell-card-box">
                                <!-- Item Card -->
                                <div class="sell-card mb-3" id="sell-card">
                                    <div class="row g-0">
                                        <div class="col-md-4">
                                            <img src="UI/image/Bayonet_Fade.png" class="img-fluid rounded" alt="...">
                                        </div>
                                        <div class="col-md-8">
                                            <div class="card-body">
                                                <h5 class="card-title mb-2">Butterfly Knife | Gamma Doppler (Factory New)
                                                </h5>
                                                <p class="card-text mb-1">Selling price: $2000</p>
                                                <p class="card-text">You get: $1800</p>
                                            </div>
                                        </div>
                                        <button class="btn item-card-button mt-2">
                                            <i class="fa-solid fa-cart-shopping"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex justify-content-between">
                                <span>Total:</span>
                                <span>$6000</span>
                            </div>

                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>

    <!-- Link Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
    </script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js">
    </script>

    <script>
        function getAmount() {
            return document.getElementsByClassName("item-card").length;
        }

        function loadMore() {
            var amount = getAmount();
            console.log(amount);
            $.ajax({
                url: "/In_Game_Items_Trading/load",
                type: 'GET',
                data: {
                    exist: amount
                },
                success: function (data) {
                    var row = document.getElementById("list-content");
                    row.innerHTML += data;
                    if (data.length === 0) {
                        document.getElementById("load-button").style.display = "none";
                    }
                }
            });
        }
    </script>

    <script>
        // Create an empty array to store the checked values
        var checkedValues = ['knife', 'pistol', 'rifle', 'smgs', 'heavy'];

        var selectAllButton = document.getElementById('select-all');
        var resetButton = document.getElementById('reset-all');

        // Get all the checkbox elements on the page
        var checkboxes = document.querySelectorAll('input[type="checkbox"]');

        // Add event listener to each checkbox
        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener('change', function () {
                // Check if the checkbox is checked
                if (this.checked) {
                    // Push the value into the array
                    checkedValues.push(this.value);
                } else {
                    // Remove the value from the array if unchecked
                    var index = checkedValues.indexOf(this.value);
                    if (index !== -1) {
                        checkedValues.splice(index, 1);
                    }
                }
                // Log the updated array
                console.log(checkedValues);
            });
        });

        selectAllButton.addEventListener('click', function () {
            checkedValues = [];
            checkboxes.forEach(function (checkbox) {
                checkbox.checked = true;
                checkedValues.push(checkbox.value);
            });
            console.log(checkedValues);
        });

        resetButton.addEventListener('click', function () {
            checkboxes.forEach(function (checkbox) {
                checkbox.checked = false;
            });
            checkedValues = [];
            console.log(checkedValues);
        });

        function filterByType(order) {
            var searchName = document.getElementById("search-input").value;
            console.log(searchName);
            $.ajax({
                url: "/In_Game_Items_Trading/filterSell",
                type: "GET",
                //use when passing array
                traditional: true,
                data: {
                    types: checkedValues,
                    txt: searchName,
                    order: order
                },
                success: function (data) {
                    var row = document.getElementById("list-content");
                    row.innerHTML = data;
                    document.getElementById("load-button").style.display = "none";
                },
                error: function () {
                    console.log('Error occurred during AJAX request');
                }
            });
        }
    </script>
    <script>
        function addSellList(sell, seller) {
            console.log(sell + "+" + seller);
            $.ajax({
                url: "/In_Game_Items_Trading/SellCreateController",
                type: 'POST',
                data: {
                    sellid: sell,
                    sellerid: seller
                },
                success: function (data) {
                    var button = document.getElementById("button-cart" + market);
                    button.innerHTML = data;
                }
            });
        }
    </script>
</body>

</html>
