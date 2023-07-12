<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <script src="UI/js/formValidate.js"></script>
</head>
<body>
    <h1>View Game Items</h1>
    <div class="container-fluid main-content">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-lg-3 sidebar">
                    <%@include file="sidebar.jsp" %>
                </div>
                <!-- Page Info -->
                <div class="col-lg-9 page-info">
                    <div class="container">
                        <c:choose>
                            <c:when test="${(requestScope.paymentRequestList != null) }">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Skin Name</th>
                                            <th scope="col">Item Name</th>
                                            <th scope="col">Type</th>
                                            <th scope="col">Rarity</th>
                                            <th scope="col">image</th>
                                            <th scope="col"></th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var = "gameItem" items="${requestScope.gameItemList}">
                                        <form action='ViewGameItemsController' method='post'>
                                            <input type="hidden" name="gameItemId" value="${gameItem.id}">
                                            
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>${gameItem.skinName}</td>
                                                <td>${gameItem.itemName}</td>
                                                <td>${gameItem.type}</td>
                                                <td>${gameItem.rarity}</td>
                                                <td><img src="UI/image/${gameItem.img}" alt="invoice picture" width="400" 
                                                         height="500"></td>
                                                <td>
                                                    <td><a href="EditGameItem.jsp">Edit</a></td>
                                                    <td><a href="DeleteGameItem">Delete</a></td>
                                                </td>
                                                <td><input type='submit' name='action' value='Accept'></td>
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
                    <a href="addGameItem.jsp">Add New Game Item</a>
                </div>
            </div>
        </div>

    <%-- Display table of game items --%>
   

    <%-- Link to add a new game item --%>
    

</body>
</html>
