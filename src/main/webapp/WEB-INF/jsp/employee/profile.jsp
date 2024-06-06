<%-- 
    Document   : profile
    Created on : 5 черв. 2024 р., 20:21:21
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
    <body>
        <!-- employee -->
        <div class="pageWrapper">
            <div class="mainContainer">
                <header>
                    <nav>
                        <a class="homeLink" href="${pageContext.request.contextPath}/vacancies"><img src="<c:url value='/resources/img/home.png'/>" alt="home icon"/></a>
                        <form action="search" method="GET">
                            <input type="text" name="searchText" placeholder="Search..." value="${prevSearch}">
                            <input type="hidden" name="command" value="search">
                            <input type="submit" value="Search">
                        </form>
                        <a href=""><img src="<c:url value='/resources/img/account.png'/>" alt="profile icon"/></a>
                    </nav>
                </header>
                <div class="profileContentWrapper">
                    <h1>Welcome back, ${user.getName()}!</h1>
                    <a href="${pageContext.request.contextPath}/users?command=logout">Log out.</a>

                    <div class="answerList">
                        <c:if test='${!user.getAnswers().isEmpty()}'><h2>Your applications:</h2></c:if>
                        <c:forEach var="answer" items="${user.getAnswers().values()}">
                            <div class="answer" id="${answer.getId()}">
                                <h3>Title: <c:out value="${answer.getVacancy().getTitle()}"/></h3><span>Submitted at: ${answer.formatTime()}</span>

                                <form action="vacancies" method="GET">
                                    <input type="hidden" name="command" value="view"/>
                                    <input type="hidden" name="id" value="${answer.getVacancy().getId()}" />
                                    <input type="submit" value="View vacancy" />
                                </form>
                                <p>Your answer:<br><c:out value="${answer.getAnswer()}"/></p>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
