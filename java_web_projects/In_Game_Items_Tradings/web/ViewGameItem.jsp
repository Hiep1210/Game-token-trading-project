<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Game Items</title>
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
