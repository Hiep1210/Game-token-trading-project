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

    <%-- Display table of game items --%>
    <table>
        <tr>
            <th>ID</th>
            <th>Skin Name</th>
            <th>Item Name</th>
            <th>Type</th>
            <th>Rarity</th>
            <th>Exterior</th>
            <th>Image</th>
            <th></th>
            <th></th>
        </tr>
        
        <c:forEach var="gameItem" items="${gameItems}">
            <tr>
                <td>${gameItem.id}</td>
                <td>${gameItem.skinName}</td>
                <td>${gameItem.itemName}</td>
                <td>${gameItem.type}</td>
                <td>${gameItem.rarity}</td>
                <td>${gameItem.exterior}</td>
                <td>${gameItem.img}</td>
                
                <!-- Add more columns as per your database schema -->
            </tr>
        </c:forEach>
        
    </table>

    <%-- Link to add a new game item --%>
    <br>
    <a href="addGameItem.jsp">Add New Game Item</a>

</body>
</html>
