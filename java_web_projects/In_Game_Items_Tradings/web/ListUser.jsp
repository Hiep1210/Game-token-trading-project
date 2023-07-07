<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
</head>
<body>
    <h1>User Management</h1>
    <form action="ListUser.jsp" method="get">
        Filter by:
        <select name="filterType">
            <option value="">Select Filter</option>
            <option value="id">ID</option>
            <option value="username">Username</option>
            <option value="role">Role</option> <%-- Add role filter here --%> 
        </select> 
        <%-- Add role filter value here --%> 
        <%-- Only display if role filter is selected --%> 
        <%-- Example: --%> 
        <%-- if (filterType != null && filterType.equals("role")) { --%> 
            <select name="filterValue"> 
                <option value="">Select Role</option> 
                <option value="user">User</option> 
                <option value="admin">Admin</option> 
            </select> 
        <%-- } else { --%> 
            <input type="text" name="filterValue"> 
        <%-- } --%> 
        <input type="submit" value="Filter"> 
    </form> 
    <br/> 
    <table border="1"> 
        <tr> 
            <th>ID</th> 
            <th>Username</th> 
            <th>Password</th> 
            <th>DOB</th> 
            <th>Email</th> 
            <th>Gender</th> 
            <th>Avatar</th> 
            <th>Role ID</th> 
            <th>Money</th> 
            <th>Edit</th> 
            <th>Delete</th> 
        </tr> 
        <c:forEach var="user" items="${users}"> 
            <%-- Add filter logic here --%> 
            <%-- Only display users that match the filter --%> 
            <%-- If no filter is selected, display all users --%> 
            <%-- Example: --%> 
            <%-- if (filterType == null || (filterType.equals("id") && user.getId() == filterValue) || (filterType.equals("username") && user.getUsername().equals(filterValue)) || (filterType.equals("role") && user.getRole().equals(filterValue))) { --%> 
                <%-- Display user row --%> 
                <tr> 
                    <td>${user.id}</td> 
                    <td>${user.username}</td> 
                    <td>${user.password}</td> 
                    <td>${user.dob}</td> 
                    <td>${user.email}</td> 
                    <td>${user.gender}</td> 
                    <td>${user.avatar}</td>  
                    <%-- Display role name instead of role id here --%>
                    <%-- Example: --%>
                    <%-- if (user.getRoleid() == 1) { --%>
                        <%-- Admin role --%>
                        <td><%= "Admin" %></td>
                    <%-- } else { --%>
                        <%-- User role --%>
                        <td><%= "User" %></td>
                    <%-- } --%>
                    <td>${user.money}</td>
                    <td><a href="editUser.jsp?id=${user.id}">Edit</a></td>
                    <td><a href="deleteUser?id=${user.id}">Delete</a></td>
                </tr>
            <%-- } --%>
        </c:forEach>
    </table>
    <%-- Add other code here --%>
</body>
</html>
