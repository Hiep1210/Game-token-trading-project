<%-- 
    Document   : requestList
    Created on : Jun 5, 2023, 12:02:45 AM
    Author     : VICTUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Page</title>
        <!-- Link Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <!-- Link Icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <!-- Link CSS -->
        <link rel="stylesheet" href="UI/css/style.css">
        <link rel="stylesheet" href="UI/css/userProfile.css">
        <link rel="stylesheet" href="UI/css/admin.css">
        <style>
            .table {
                color: white;
            }
            p {
                color: white;
            }
        </style>
    </head>

    <body>
        <c:set var="redirect" value="GetprocessItemController"/>
        <!-- Navbar -->
        <%@include file="navbar.jsp" %>
         <script>
            //load page before display message
            window.onload = function () {
                if (${requestScope.message != null}) {
                    document.title = "GIT";
                    alert('${requestScope.message}');
                }
            };
        </script>
        <div class="container-fluid main-content">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-lg-3 sidebar">
                    <%@include file="sidebar.jsp" %>
                </div>
                <!-- Page Info -->
                <div class="col-lg-9 page-info">
                    <div class="container">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Sender's Items</th>
                                    <th scope="col">Sender's Game Account</th>
                                    <th scope="col">Receiver's Items</th>
                                    <th scope="col">Sender's Game Account</th>
                                    <th scope="col">Receiver game account name</th>
                                    <th scope="col">Create Date</th>
                                    <th scope="col">Action</th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var = "trade" items="${requestScope.trade}" varStatus="currentStatus">
                                <form action='ProcessItemController' method='post'>
                                    <input type="hidden" name="processItemId" value="${trade.id}">    
                                    <tr>
                                        <th scope="row">${currentStatus.index + 1}</th>
                                        <td>
                                            <c:forEach var="offer" items="${requestScope.offer[trade.transactionId]}">
                                                <a href="#" id="">${offer.give.skinName}|${offer.give.itemName}|${offer.give.exterior}</a><br>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            ${trade.object.gAcc}
                                        </td>
                                        <td>
                                            <c:forEach var="rec" items="${requestScope.rec[trade.transactionId]}">
                                                <a href="#" id="">${rec.rec.skinName}|${rec.rec.itemName}|${rec.rec.exterior}</a><br>
                                        </c:forEach>
                                        </td>
                                        <td>
                                            ${trade.gameAccountName}
                                        </td>
                                        <td>
                                            <input type="text" name="denyReason" value="Seller did not send item!">
                                        </td>
                                        <td>
                                            ${trade.processTime}
                                        </td>
                                        <td>
                                            Accept<input type="radio" name="decision" value="accept" required="">
                                            Reject<input type="radio" name="decision" value="reject">
                                        </td>
                                        <td><input type='submit' name='action' value='Confirm'></td>
                                    </tr>
                                </form>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>
    </body>

</html>
