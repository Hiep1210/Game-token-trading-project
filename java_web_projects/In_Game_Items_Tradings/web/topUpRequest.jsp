<%-- 
    Document   : userProfile
    Created on : Jun 5, 2023, 10:36:58 AM
    Author     : VICTUS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile</title>
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
        <c:set var="redirect" value="UserProfileController"/>
        <%@include file="navbar.jsp" %>

        <div class="container-fluid main-content">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-lg-3 sidebar">
                    <%@include file="sidebar.jsp" %>
                </div>
                <div class="col-lg-6 user-information">
                    <div class="container">
                        <div class="form">
                            <!-- Screen Header -->
                            <header class="header text-center">Top-up</header>
                            <!-- Main Form -->
                            <form id="topup-form" action='SendPaymentRequestController' method='post' enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="amount" class="form-label">Enter the amount you want</label>
                                    <input type="number" step="0.01" min="0.01"id="amount" name="amount" class="form-control"  placeholder="Insert top up amount">
                                    <span class="form-message"></span>
                                </div>
                                <div class="form-group">
                                    <label for="image" class="form-label">Attach invoice</label>
                                    <input type="file" accept="image/*" id="amount subtitle" name="image" class="form-control" required>
                                    <span class="form-message"></span>
                                </div>
                            </form>
                            <!-- Form Buttons -->
                            <div class="row">
                                <!-- Button Spacer -->
                                <div class="col-lg-9">
                                </div>
                                <!-- Summit button -->
                                <div class="col-lg-3">
                                    <div class="summit-button mt-2">
                                        <button type="submit" name="action" value="Submit" form="topup-form">Confirm</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                Validator({
                    form: '#edit-user-profile',
                    formGroupSelector: '.form-group',
                    errorSelector: '.form-message',
                    rules: [
                        Validator.isRequired('#username', 'Please enter your username'),
                        Validator.isEmail('#email'),
                        Validator.isDate('#dob'),
                        Validator.isRequiredGender('[name="gender"]', 'Please select a gender')
                    ]
                });
            });
        </script>
    </body>

</html>



