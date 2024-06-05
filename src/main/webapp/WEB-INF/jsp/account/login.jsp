<%-- 
    Document   : login
    Created on : 5 черв. 2024 р., 18:23:23
    Author     : sasha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login to your account:</h1>
        <form action="users" method="PUSH">
            <label for="email">Enter your email:</label><br>
            <input type="text" name="email" id="email" placeholder="email@mail.com"/><br><br>
            <label for="password">Enter password:</label><br>
            <input type="password" name="password" id="password"/><br><br>
            <input type="hidden" name="command" value="login"/>
            <input type="submit" name="submit" value="Submit"/>
        </form>
        <p>Don't have an account with us? <a href="./signup">Click here to sign up.</a></p>
    </body>
</html>
