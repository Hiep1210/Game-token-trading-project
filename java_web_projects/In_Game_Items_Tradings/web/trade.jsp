<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Trade page</title>
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
        <form action="CreateOfferController" method="post">

            <div class="container-fluid">
                <div class="row"> 
                    <div class="col-lg-5">
                        <div class="container-fluid">
                            <div class="row mt-2">
                                <!-- Search Bar -->
                                <div class="col-lg-6 search-bar form">
                                    <div class="form-group">
                                        <i class="fa-solid fa-magnifying-glass pt-3 ps-3"></i>
                                        <input oninput="filterByType()" id="search-input" class="form-control ps-5" type="text" placeholder="Search...">
                                    </div>
                                </div>
                                <!-- Filter By Type -->
                                <div class="col-lg-3 filter-type" style="">
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
                                <div class="col-lg-3">
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
                            <div class="row">
                                <c:forEach var ="gItem" items="${requestScope.gItem}" varStatus="currentStatus">
                                    <!-- Item Card -->
                                    <div class="item-card mb-3 col-lg-3"  id="item-card">
                                        <div class="card rarity-${gItem.rarity.toLowerCase()}">
                                            <img  data-bs-toggle="offcanvas" href="#offcanvas${gItem.id}" src="UI/image/${gItem.img}.png" class="img-fluid rounded" alt="...">
                                            <div class="card-body">
                                                <p>${gItem.type} | ${gItem.itemName} ${gItem.skinName}</p>
                                                <input id="itemno${gItem.id}" type="checkbox" value="${gItem.id}" name="offer" hidden="" onchange="handleCheckboxChange(this)"/>
                                                <label for="itemno${gItem.id}" class="btn item-card-button">
                                                    <i style="color:white" class="material-icons navbar-item-icon">compare_arrows</i>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Item Details -->
                                    <div class="offcanvas offcanvas-start" data-bs-theme="dark" tabindex="-1" id="offcanvas${gItem.id}"
                                         aria-labelledby="offcanvas">
                                        <div class="offcanvas-header">
                                            <h5 class="offcanvas-title" id="offcanvas">
                                                ${gItem.type} | ${gItem.skinName}
                                            </h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="offcanvas-body">
                                            <img class="img-fluid" src="UI/image/${gItem.getImg()}.png" alt="">
                                            <div class="d-flex justify-content-between mt-2">
                                                <p class="sell-info-select-name">Type: </p>
                                                <h5>${gItem.type}</h5>
                                            </div>
                                            <div class="d-flex justify-content-between mt-2">
                                                <p class="sell-info-select-name">Exterior:</p>
                                                <h5>${gItem.exterior}</h5>
                                            </div>
                                            <div class="d-flex justify-content-between mt-2">
                                                <p class="sell-info-select-name">Rarity:</p>
                                                <h5>${gItem.rarity}</h5>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                        </div>
                    </div>

                    <div class="col-lg-2 flex-column p-4">
                        <c:if test="${sessionScope.user != null}">
                            <form action="ViewYourTradeItem" method="post">
                                <button type="submit" class="btn item-card-button" style="height: 80px">
                                    <b>View your trade item</b>
                                </button>
                            </form>
                        </c:if>

                        <!-- Cart Info Section -->
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
                            <c:if test="${sessionScope.user !=null}">
                                <div style="margin-top: 30px">
                                    <form action="ViewTradeOffer" method="post">
                                        <button type="submit" class="btn item-card-button" style="height: 80px">
                                            <b>View All Trade Offer</b>
                                        </button>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-lg-5">
                        <div class="container-fluid">
                            <div class="row mt-2">
                                <!-- Search Bar -->
                                <div class="col-lg-6 search-bar form">
                                    <div class="form-group">
                                        <i class="fa-solid fa-magnifying-glass pt-3 ps-3"></i>
                                        <input oninput="filterByType()" id="search-input" class="form-control ps-5" type="text" placeholder="Search...">
                                    </div>
                                </div>
                                <!-- Filter By Type -->
                                <div class="col-lg-2 filter-type" style="">
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
                                <!-- Sort by Exterior -->
                                <div class="col-lg-2">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-danger">Exterior</button>
                                        <button type="button" class="btn btn-danger dropdown-toggle dropdown-toggle-split"
                                                data-bs-toggle="dropdown" aria-expanded="false">
                                            <span class="visually-hidden">Toggle Dropdown</span>
                                        </button>
                                        <ul class="dropdown-menu dropdown-menu-end">
                                            <li class="dropdown-item"><input type="checkbox" name="ex" value="Battle-Scarred" checked>Battle-Scarred</li>
                                            <li class="dropdown-item"><input type="checkbox" name="ex" value="Factory New" checked>Pistol</li>
                                            <li class="dropdown-item"><input type="checkbox" name="ex" value="Field-Tested" checked>Rifle</li>
                                            <li class="dropdown-item"><input type="checkbox" name="ex" value="Minimal Wear" checked>SMGs</li>
                                            <li class="dropdown-item"><input type="checkbox" name="ex" value="Well-Worn" checked>Heavy</li>
                                            <li>
                                                <hr class="dropdown-divider">
                                            </li>
                                            <li><a class="dropdown-item" style="color: rgb(87, 242, 135)" id="select-all"">Select All</a></li>
                                            <li><a class="dropdown-item" style="color: rgb(218, 100, 123)" id="reset-all">Reset</a></li>
                                            <li><a class="dropdown-item" style="color: rgb(128, 108, 245)" onclick="filterByType()">Save Filter</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <input type="submit" value="heyy"/>
                            <div class="row">

                                <c:forEach var ="gItem" items="${requestScope.gItem}" varStatus="currentStatus">
                                    <!-- Item Card -->
                                    <div class="item-card mb-3 col-lg-3"  id="item-card">
                                        <div class="card rarity-${gItem.rarity.toLowerCase()}">
                                            <img  data-bs-toggle="offcanvas" href="#offcanvas${gItem.id}" src="UI/image/${gItem.img}.png" class="img-fluid rounded" alt="...">
                                            <div class="card-body">
                                                <p>${gItem.type} | ${gItem.itemName} ${gItem.skinName}</p>
                                                <input id="itemno${gItem.id}right" type="checkbox" value="${gItem.id}" name="receive" hidden="" onchange="handleCheckboxChange(this)"/>
                                                <label for="itemno${gItem.id}right" class="btn item-card-button">
                                                    <i style="color:white" class="material-icons navbar-item-icon">compare_arrows</i>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Item Details -->
                                    <div class="offcanvas offcanvas-start" data-bs-theme="dark" tabindex="-1" id="offcanvas${gItem.id}"
                                         aria-labelledby="offcanvas">
                                        <div class="offcanvas-header">
                                            <h5 class="offcanvas-title" id="offcanvas">
                                                ${gItem.type} | ${gItem.skinName}
                                            </h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="offcanvas-body">
                                            <img class="img-fluid" src="UI/image/${gItem.getImg()}.png" alt="">
                                            <div class="d-flex justify-content-between mt-2">
                                                <p class="sell-info-select-name">Type: </p>
                                                <h5>${gItem.type}</h5>
                                            </div>
                                            <div class="d-flex justify-content-between mt-2">
                                                <p class="sell-info-select-name">Exterior:</p>
                                                <h5>${gItem.exterior}</h5>
                                            </div>
                                            <div class="d-flex justify-content-between mt-2">
                                                <p class="sell-info-select-name">Rarity:</p>
                                                <h5>${gItem.rarity}</h5>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>

                    </div>

                    <!-- Item details -->
                </div>
            </div>
        </form>
            <!-- Link Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
            </script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
            <script>
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
                function handleCheckboxChange(checkbox) {
                    if (checkbox.checked) {
                        // Create a new <h4> element
                        var h4Element = document.createElement("h4");
                        h4Element.textContent = "true";

                        // Replace the <i> element with the new <h4> element
                        var iElement = checkbox.parentElement.querySelector("i");
                        iElement.parentNode.replaceChild(h4Element, iElement);
                    }
                }
            </script>
    </body>

</html>
