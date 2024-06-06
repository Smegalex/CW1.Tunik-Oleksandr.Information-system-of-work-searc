<%-- 
    Document   : main
    Created on : 5 черв. 2024 р., 14:43:59
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
                        <form action="search" method="GET">
                            <input type="text" name="searchText" placeholder="Search...">
                            <input type="hidden" name="command" value="search">
                            <input type="submit" value="Search">
                        </form>
                        <a href="${pageContext.request.contextPath}/users"><img src="<c:url value='/resources/img/account.png'/>" alt="profile icon"/></a>
                    </nav>
                </header>
                <div class="vacanciesBody">
                    <c:forEach var="vacancy" items="${vacancies}">
                        <div class="vacancyContainer" id="${vacancy.getId()}">
                            <h3><c:out value="${vacancy.getTitle()}"/></h3><span>${vacancy.formatTime()}</span>
                            <p><c:out value="${vacancy.getDescription()}"/></p>
                            <form action="vacancies" method="GET">
                                <input type="hidden" name="command" value="view"/>
                                <input type="hidden" name="id" value="${vacancy.getId()}" />
                                <input type="submit" value="View" />
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
