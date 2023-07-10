<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Game Item</title>
</head>
<body>
    <h1>Add Game Item</h1>

    <%-- Form to add a new game item --%>
    <form action="SaveGameItem.jsp" method="post">
        <label for="skinName">Skin Name:</label>
        <input type="text" name="skinName" id="skinName"><br>
        <label for="itemName">Item Name:</label>
        <input type="text" name="itemName" id="itemName"><br>
        <label for="type">Type:</label>
            <select name="type" id="type">
            <option value="Pistol">Pistol</option>
            <option value="Heavy">Heavy</option>
            <option value="Rifles">Rifles</option>
            <option value="SMGs">SMGs</option>
            <option value="Knife">Knife</option>
            </select><br>

        <label for="rarity">Rarity:</label>
            <select name="rarity" id="rarity">
            <option value="Consumer">Consumer</option>
            <option value="Industrial">Industrial</option>
            <option value="Mil-spec">Mil-spec</option>
            <option value="Restricted">Restricted</option>
            <option value="Classified">Classified</option>
            <option value="Covert">Covert</option>
            </select><br>
        
        <label for="img">Image:</label>
            <input type="file" name="img" id="img"><br>
        <input type="submit" value="Add">
    </form>

    <%-- Link to go back to the list of game items --%>
    <br>
    <a href="ViewGameItem.jsp">Back to Game Items</a>

</body>
</html>
