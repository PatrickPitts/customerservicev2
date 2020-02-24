<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login using your credentials</title>
</head>
<body>
<h2>Login to access site</h2>
<form action="/login" method="post">
    <label for="username">User Name:</label><input type="text" name="username" id="username"><br>
    <label for="password">Password:</label><input type="password" name="password" id="password">
    <input type="submit" value="Submit">
</form>
<br>
<a href="/new-user">Create a new account</a>
</body>
</html>