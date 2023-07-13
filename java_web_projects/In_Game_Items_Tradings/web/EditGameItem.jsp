<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Game Item</title>
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
            <h1>Edit Game Item</h1>

            <%-- Add your edit form here --%>
            <form action="UpdateGameItemController" method="post">
                <div class="form-group">
                    <label for="skin-name">Skin Name:</label>
                    <input type="text" class="form-control" id="skin-name" name="skinName" value="${sellList.gameItems.skinName}" required>
                </div>
                <div class="form-group">
                    <label for="item-name">Item Name:</label>
                    <input type="text" class="form-control" id="item-name" name="itemName" value="${sellList.gameItems.itemName}" required>
                </div>
                <div class="form-group">
                    <label for="type">Type:</label>
                    <select class="form-control" id="type" name="type" required>
                        <option value="knife" ${sellList.gameItems.type == 'knife' ? 'selected' : ''}>Knife</option>
                        <option value="pistol" ${sellList.gameItems.type == 'pistol' ? 'selected' : ''}>Pistol</option>
                        <option value="rifle" ${sellList.gameItems.type == 'rifle' ? 'selected' : ''}>Rifle</option>
                        <option value="smg" ${sellList.gameItems.type == 'smg' ? 'selected' : ''}>SMGs</option>
                        <option value="heavy" ${sellList.gameItems.type == 'heavy' ? 'selected' : ''}>Heavy</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="rarity">Rarity:</label>
                    <input type="text" class="form-control" id="rarity" name="rarity" value="${sellList.gameItems.rarity}" required>
                </div>
                <div class="form-group">
                    <label for="image">Image:</label>
                    <input type="text" class="form-control" id="image" name="image" value="${sellList.gameItems.img}" required>
                </div>
                <button type="submit" class="btn btn-primary">Update Game Item</button>
            </form>

        </div>
    </div>
</div>

</body>
</html>
