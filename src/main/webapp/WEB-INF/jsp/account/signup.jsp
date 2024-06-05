<%-- 
    Document   : signUp
    Created on : 5 черв. 2024 р., 18:25:21
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
        <h1>Create an account:</h1>
        <form action="users" method="POST">
            <label for="email">Enter your email:</label><br>
            <input type="text" name="email" id="email" placeholder="email@mail.com"/><br><br>
            <label for="password">Enter password:</label><br>
            <input type="password" name="password" id="password"/><br><br>
            <label for="fullName">Enter your full name:</label><br>
            <input type="text" name="fullName" id="fullName" placeholder="Full name..."/><br><br>
            <label>Choose a type of account you want to create:</label><br>
            <input type="radio" name="accountType" id="employee" value="employee"/><label for="employee">Employee</label><br>
            <input type="radio" name="accountType" id="employer" value="employer"/><label for="employer">Employer</label><br><br>
            <input type="submit" name="submit" value="Submit"/>
        </form>
    </body>
</html>
