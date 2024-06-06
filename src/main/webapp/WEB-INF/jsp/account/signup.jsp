<%-- 
    Document   : signUp
    Created on : 5 черв. 2024 р., 18:25:21
    Author     : sasha
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ISoWS: Sign Up</title>
        <link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>"/>
    </head>
    <body>
        
        <div class="login-container">
            <h1>Create an account:</h1>
            <form action="users" method="POST" class="login-form">
                <label for="email">Enter your email:</label><br>
                <input type="text" name="email" id="email" placeholder="email@mail.com"/><br><br>
                <label for="password">Enter password:</label><br>
                <input type="password" name="password" id="password"/><br><br>
                <label for="fullName">Enter your full name:</label><br>
                <input type="text" name="fullName" id="fullName" placeholder="Full name..."/><br><br>
                <label>Choose a type of account you want to create:</label><br>
                <span><input type="radio" name="accountType" id="employee" value="employee"/><label for="employee">Employee</label></span><br>
                <span><input type="radio" name="accountType" id="employer" value="employer"/><label for="employer">Employer</label></span><br><br>
                <input type="hidden" name="command" value="signup"/>
                <input type="submit" name="submit" value="Submit"/>
            </form>
        </div>
        <div class="popupContainer <c:if test="${popupCondition}">showing</c:if> ${popupStatus}">
                <div class="popupContent">
                    <h2>${popupTitle}</h2>
                <p>${popupBody}</p>
                <button onclick="closePopup()">Close</button>
            </div>
        </div>  
        <script>
            function closePopup() {
                document.getElementsByClassName('popupContainer')[0].classList.remove('showing');
            }
        </script>
    </body>
</html>
