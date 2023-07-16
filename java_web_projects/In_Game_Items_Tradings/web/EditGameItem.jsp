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
    <style>
    /* Style for the form labels */
    form label {
        color: white;
    }
    
    h1{
        color: white;
    }
    
</style>
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
            <form action="EditGameItemController" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="skin-name">Skin Name:</label>
                    <input type="text" class="form-control" id="skin-name" name="skinName" value="${gameItem.skinName}" required>
                </div>
                <div class="form-group">
                    <label for="item-name">Item Name:</label>
                    <input type="text" class="form-control" id="item-name" name="itemName" value="${gameItem.itemName}" required>
                </div>
                <div class="form-group">
                    <label for="type">Type:</label>
                    <select class="form-control" id="type" name="type" required>
                        <option value="knife" ${gameItem.type == 'Knife' ? 'selected' : ''}>Knife</option>
                        <option value="pistol" ${gameItem.type == 'Pistol' ? 'selected' : ''}>Pistol</option>
                        <option value="rifle" ${gameItem.type == 'Rifle' ? 'selected' : ''}>Rifle</option>
                        <option value="smg" ${gameItem.type == 'SMGs' ? 'selected' : ''}>SMGs</option>
                        <option value="heavy" ${gameItem.type == 'Heavy' ? 'selected' : ''}>Heavy</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="rarity">Rarity:</label>
                    <select class="form-control" id="rarity" name="rarity" required>
                        <option value="Consumer" ${gameItem.rarity == 'Consumer' ? 'selected' : ''}>Consumer</option>
                        <option value="Industrial" ${gameItem.rarity == 'Industrial' ? 'selected' : ''}>Industrial</option>
                        <option value="Mil-spec" ${gameItem.rarity == 'Mil-spec' ? 'selected' : ''}>Mil-spec</option>
                        <option value="Restricted" ${gameItem.rarity == 'Restricted' ? 'selected' : ''}>Restricted</option>
                        <option value="Classified" ${gameItem.rarity == 'Classified' ? 'selected' : ''}>Classified</option>
                        <option value="Covert" ${gameItem.rarity == 'Covert' ? 'selected' : ''}>Covert</option>
                    </select>
                </div>
                
                <div class="mb-3">
                        <label for="image" class="form-label">Image</label>
                        <input type="file" accept="image/*" name="image" id="image" class="form-control" required>
                    </div>
                <button type="submit" class="btn btn-primary">Update Game Item</button>
            </form>

        </div>
    </div>
</div>

</body>
</html>
