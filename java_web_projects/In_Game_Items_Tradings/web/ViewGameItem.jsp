<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Game Items List</title>
        <!-- Link Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!-- Link Icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
              integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Link CSS -->
        <link rel="stylesheet" href="UI/css/style.css">
        <link rel="stylesheet" href="UI/css/userProfile.css">
        <link rel="stylesheet" href="UI/css/style.css">
        <link rel="stylesheet" href="UI/css/styleInput.css">
        <link rel="stylesheet" href="UI/css/styleItemBox.css">
        <link rel="stylesheet" href="UI/css/styleSell.css">
        <script src="UI/js/formValidate.js"></script>
</head>
<body>

    <div class="container-fluid main-content">
        
            <div class="row">
                <%@include file="navbar.jsp" %>
                <!-- Sidebar -->
                <div class="col-lg-3 sidebar">
                    <%@include file="sidebar.jsp" %>
                </div>
                <!-- Page Info -->
                <div class="col-lg-9 page-info">
                    <h1>View Game Items</h1>
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
                        
                        <div class="col-lg-6">
                            <a href="addGameItem.jsp">Add New Game Item</a>
                        </div>
                    </div>
</div>
                    <div class="container">
                        <c:choose>
                            <c:when test="${(requestScope.sellList != null) }">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            
                                            <th scope="col">Skin Name</th>
                                            <th scope="col">Item Name</th>
                                            <th scope="col">Type</th>
                                            <th scope="col">Rarity</th>
                                            <th scope="col">image</th>
                                            <th scope="col">Action</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var = "sellList" items="${requestScope.sellList}">
                                        <form action='ViewGameItemController' method='post'>
                                                                                        
                                            <tr>
                                                
                                                <td>${sellList.gameItems.skinName}</td>
                                                <td>${sellList.gameItems.itemName}</td>
                                                <td>${sellList.gameItems.type}</td>
                                                <td>${sellList.gameItems.rarity}</td>
                                                <td><img src="UI/image/${sellList.gameItems.img}.png" alt="invoice picture" width="200" 
                                                         height="300"></td>
                                                <td>
                                                    <td><a href="EditGameItem.jsp">Edit</a></td>
                                                    <td><a href="DeleteGameItem">Delete</a></td>
                                                </td>
                                                
                                            </tr>   
                                        </form>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>   
                            <c:otherwise>
                                <p>Currently 0 payment request to process</p>
                            </c:otherwise>
                        </c:choose>
                               
                    </div>
                    
                </div>
            </div>
        </div>

    <%-- Display table of game items --%>
   

    <%-- Link to add a new game item --%>
    

</body>
</html>
