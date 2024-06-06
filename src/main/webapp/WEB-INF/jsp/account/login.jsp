<%-- 
    Document   : login
    Created on : 5 черв. 2024 р., 18:23:23
    Author     : sasha
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ISoWS: Login</title>
        <link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>"/>
    </head>
    <body>
        
        <div class="login-container">
            <h1>Login to your account:</h1>
            <form action="users" method="POST" class="login-form">
                <label for="email">Enter your email:</label><br>
                <input type="email" name="email" id="email" placeholder="email@mail.com"/><br><br>
                <label for="password">Enter password:</label><br>
                <input type="password" name="password" id="password"/><br><br>
                <input type="hidden" name="command" value="login"/>
                <input type="submit" name="submit" value="Submit"/>
            </form>
            <p>Don't have an account with us? <a href="${pageContext.request.contextPath}/users?command=signup">Click here to sign up.</a></p>
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
