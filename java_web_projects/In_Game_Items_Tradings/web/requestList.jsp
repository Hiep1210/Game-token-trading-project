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
        <c:set var="redirect" value="GetPaymentRequestController"/>
        <!-- Navbar -->
        <%@include file="navbar.jsp" %>

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
                                            <th scope="col">Amount</th>
                                            <th scope="col">Date</th>
                                            <th scope="col">Picture</th>
                                            <th scope="col">Action</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var = "paymentRequest" items="${requestScope.paymentRequestList}">
                                        <form action='ProcessPaymentRequestController' method='post'>
                                            <input type="hidden" name="paymentRequestId" value="${paymentRequest.id}">
                                            <input type="hidden" name="type" value="payment">
                                            <tr>
                                                <th scope="row">1</th>
                                                <td>${paymentRequest.money}</td>
                                                <td> ${paymentRequest.date}</td>
                                                <td><img src="UI/image/${paymentRequest.img}" alt="invoice picture" width="400" 
                                                         height="500" style="object-fit: scale-down"></td>
                                                <td>
                                                    Accept<input type="radio" name="decision" value="accept"><br>
                                                    Reject<input type="radio" name="decision" value="reject">
                                                </td>
                                                <td><input type='submit' name='action' value='Accept' class="btn btn-success"></td>
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

        <!-- Link Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous">
        </script>
    </body>

</html>
