<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Game Item</title>
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
            <h1>Add Game Item</h1>
            <div class="container">
                <form action="AddGameItemController" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="skinName" class="form-label">Skin Name</label>
                        <input type="text" name="skinName" id="skinName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="itemName" class="form-label">Item Name</label>
                        <input type="text" name="itemName" id="itemName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="type" class="form-label">Type</label>
                        <select name="type" id="type" class="form-select" required>
                            <option value="" selected disabled>Select Type</option>
                            <option value="knife">Knife</option>
                            <option value="pistol">Pistol</option>
                            <option value="rifle">Rifle</option>
                            <option value="smg">SMGs</option>
                            <option value="heavy">Heavy</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="rarity" class="form-label">Rarity</label>
                        <select name="rarity" id="rarity" class="form-select" required>
                            <option value="" selected disabled>Select Rarity</option>
                            <option value="consumer">Consumer</option>
                            <option value="industrial">Industrial</option>
                            <option value="mil-spec">Mil-spec</option>
                            <option value="restricted">Restricted</option>
                            <option value="classified">Classified</option>
                            <option value="covert">Covert</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="image" class="form-label">Image</label>
                        <input type="file" name="image" id="image" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Item</button>
                </form>
            </div>
        </div>
