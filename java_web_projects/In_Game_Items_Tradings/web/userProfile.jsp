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
                <!-- User Info -->
                <form class="col-lg-9" id="edit-user-profile" action="EditUserProfile" method="post" enctype="multipart/form-data">
                    <div class="row">
                        <div class="user-information col-lg-8">
                            <div class="container">
                                <input name="id" type="text" value="${sessionScope.user.id}" hidden>
                                <div class="form-group">
                                    <label for="username" class="form-label">Username</label>
                                    <div class="col-lg-10 ps-0">
                                        <input id="username" name="username" type="text" value="${sessionScope.user.username}" class="form-control" readonly>
                                        <span class="form-message"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="form-label">Email</label>
                                    <div class="col-lg-10 ps-0">
                                        <input id="email" name="email" type="text" value="${sessionScope.user.email}" class="form-control">
                                        <span class="form-message"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="dob" class="form-label">Date of Birth</label>
                                    <div class="col-lg-10 ps-0">
                                        <input id="dob" name="dob" type="date" value="${sessionScope.user.dob}" class="form-control">
                                        <span class="form-message"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="gender" class="form-label">Gender</label>
                                    <div class="radio-group">
                                        <c:choose>
                                            <c:when test="${sessionScope.user.gender eq 'Male'}">
                                                <label for="male" class="gender-select text-center"
                                                       onclick="changeColor('rgb(88, 101, 242)', 'male')"><i class="fa-solid fa-mars"></i></label>
                                                <input type="radio" name="gender" value="Male" id="male"
                                                       onchange="resetColor('female')" checked>
                                                <label for="female" class="gender-select text-center"
                                                       onclick="changeColor('rgb(238, 69, 158)', 'female')"><i class="fa-solid fa-venus"></i></label>
                                                <input type="radio" name="gender" value="Female" id="female"
                                                       onchange="resetColor('male')">
                                            </c:when>
                                            <c:otherwise>
                                                <label for="male" class="gender-select text-center"
                                                       onclick="changeColor('rgb(88, 101, 242)', 'male')"><i class="fa-solid fa-mars"></i></label>
                                                <input type="radio" name="gender" value="Male" id="male"
                                                       onchange="resetColor('female')">
                                                <label for="female" class="gender-select text-center"
                                                       onclick="changeColor('rgb(238, 69, 158)', 'female')"><i class="fa-solid fa-venus"></i></label>
                                                <input type="radio" name="gender" value="Female" id="female"
                                                       onchange="resetColor('male')" checked>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <span class="form-message"></span>
                                </div>
                                <div class="form-message">${requestScope.mess3}</div>  
                                <!-- Form Button -->          
                                <div class="row">
                                    <div class="summit-button col-lg-2">
                                        <button type="submit" form="edit-user-profile">Save</button>
                                    </div>
                                    <div class="summit-button col-lg-2">
                                        <button type="reset" form="edit-user-profile">Reset</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- User PFP -->
                        <div class="col-lg-4 user-pfp">
                            <div class="container">
                                <div class="form-group">
                                    <label for="pfp" class="form-label">
                                        <img class="img-fluid rounded-circle" src="UI/image/profile_pics/${sessionScope.user.getAvatar()}" alt="" style="width: 300px; height: 300px; object-fit: contain">
                                        <c:set var="profile" value="${sessionScope.user.getAvatar()}"/>
                                    </label>
                                    <input type="file" accept="image/*" name="avatar" class="form-control file-input" id="pfp">
                                    <span class="form-message"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
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