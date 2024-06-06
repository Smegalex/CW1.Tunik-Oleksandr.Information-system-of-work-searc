<%-- 
    Document   : profile
    Created on : 5 черв. 2024 р., 20:21:33
    Author     : sasha
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ISoWS: Profile</title>
        <link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>"/>
    </head>
    <body>
        <!-- employer -->
        <div class="pageWrapper">
            <div class="mainContainer">
                <header>
                    <nav>
                        <form action="search" method="GET">
                            <input type="text" name="searchText" placeholder="Search...">
                            <input type="hidden" name="command" value="search">
                            <input type="submit" value="Search">
                        </form>
                        <a href=""><img src="<c:url value='/resources/img/account.png'/>" alt="profile icon"/></a>
                    </nav>
                </header>
                <h1>Hello World!</h1>
                <a href="${pageContext.request.contextPath}/users?command=logout">Log out.</a>
            </div>
        </div>
    </body>
</html>
