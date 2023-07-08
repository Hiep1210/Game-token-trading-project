<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
</head>
<body>
    <h1>Add User</h1>
    <form action="AddUserController" method="post">
        <table>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username" required></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required></td>
            </tr>
            <tr>
                <td>Repeat Password:</td>
                <td><input type="password" name="repeatPassword" required></td>
            </tr>
            <tr>
                <td>DOB:</td>
                <td><input type="date" name="dob"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><input type="email" name="email"></td>
            </tr>
            <tr>
                <td>Gender:</td>
                <td><select name="gender">
                        <option value="">Select Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                         
                    </select></td> 
            </tr> 
            <%-- Add other fields here --%> 
        </table> 
        <br/> 
        <input type="submit" value="Add User"> 
    </form> 
</body> 
</html>
