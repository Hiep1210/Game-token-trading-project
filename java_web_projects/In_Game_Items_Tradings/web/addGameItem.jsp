<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Game Item</title>
</head>
<body>
    <h1>Add Game Item</h1>
    <form action="addGameItem" method="post">
        <table>
            <tr>
                <td>Skin Name:</td>
                <td><input type="text" name="skinName" required></td>
            </tr>
            <tr>
                <td>Item Name:</td>
                <td><input type="text" name="itemName" required></td>
            </tr>
            <tr>
                <td>Type:</td>
                <td><input type="text" name="type"></td>
            </tr>
            <tr> 
                <td>Rarity:</td> 
                <td><select name="rarity"> 
                        <option value="">Select Rarity</option> 
                        <option value="Consumer">Consumer</option> 
                        <option value="Industrial">Industrial</option> 
                        <option value="Mil-spec">Mil-spec</option> 
                        <option value="Restricted">Restricted</option> 
                        <option value="Classified">Classified</option> 
                        <option value="Covert">Covert</option> 
                    </select></td> 
            </tr>
            <tr>
                <td>Exterior:</td>
                <td><input type="text" name="exterior"></td>
            </tr>
            <tr>
                <td>Image:</td>
                <td><input type="text" name="img"></td> 
            </tr> 
        </table> 
        <br/> 
        <input type="submit" value="Add Game Item"> 
    </form> 
</body> 
</html> 
