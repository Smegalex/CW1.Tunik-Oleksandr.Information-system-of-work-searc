<%-- 
    Document   : edit
    Created on : 6 черв. 2024 р., 14:33:39
    Author     : sasha
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ISoWS</title>
        <link rel="stylesheet" href="<c:url value='/resources/css/styles.css'/>"/>
    </head>
    <body>
        <div class="pageWrapper">
            <div class="mainContainer">
                <header>
                    <nav>
                        <a class="homeLink" href="${pageContext.request.contextPath}/vacancies"><img src="<c:url value='/resources/img/home.png'/>" alt="home icon"/></a>
                        <form action="search" method="GET">
                            <input type="text" name="searchText" placeholder="Search...">
                            <input type="hidden" name="command" value="search">
                            <input type="submit" value="Search">
                        </form>
                        <a href=""><img src="<c:url value='/resources/img/account.png'/>" alt="profile icon"/></a>
                    </nav>
                </header>
                <div style="padding: 5px 20px">
                    <form action="vacancies" method="POST" style="display: flex; flex-direction: column">
                        <input style="max-width: 300px" type="text" name="title" value="${vacancy.getTitle()}"><br><br>
                        <textarea name="description">${vacancy.getDescription()}</textarea><br><br>
                        <label>Status of the vacancy:</label>
                        <span><input type="radio" id="open" name="status" value="open"><label for="open">Open</label></span>
                        <span><input type="radio" id="closed" name="status" value="closed"><label for="closed">Closed for public</label></span>
                        <input type="hidden" name="command" value="edit">
                        <input type="hidden" name="id" value="${vacancy.getId()}"><br>
                        <input type="submit" value="Submit" style="max-width: 150px; align-self: center">
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
