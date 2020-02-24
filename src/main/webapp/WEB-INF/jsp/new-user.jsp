<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
</head>
<body>
<h2>Create a new Login account</h2>
<form action="/new-user" method="post">
    <label for="username">User Name:</label><input type="text" name="username" id="username"><br>
    <label for="password">Password:</label><input type="password" name="password" id="password">
    <input type="submit" value="Submit">
</form>
<% if (request.getSession().getAttribute("UsernameAlreadyTaken") != null) {%>
<p style="color:red">that username is already taken, try a different one.</p>
<%}%>
</body>
</html>