<%-- 
    Document   : answers
    Created on : 6 черв. 2024 р., 14:18:31
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
                        <form action="vacancies" method="GET">
                            <input type="text" name="searchText" placeholder="Search...">
                            <select id="sorting" name="sorting">
                                <option value="byRelevance" <c:if test="sortBy == ${'byRelevance'}">selected="true"</c:if>>By Relevance</option>
                                <option value="alphabetically" <c:if test="sortBy == ${'alphabetically'}">selected="true"</c:if>>Alphabetically</option>
                                <option value="contrAlphabetically" <c:if test="sortBy == ${'contrAlphabetically'}">selected="true"</c:if>>Contr Alphabetically</option>
                                <option value="newToOld" <c:if test="sortBy == ${'newToOld'}">selected="true"</c:if>>New to Old</option>
                                <option value="oldToNew" <c:if test="sortBy == ${'oldToNew'}">selected="true"</c:if>>Old to New</option>
                                </select>
                                <input type="hidden" name="command" value="search">
                                <input type="submit" value="Search">
                            </form>
                            <a href="${pageContext.request.contextPath}/users"><img src="<c:url value='/resources/img/account.png'/>" alt="profile icon"/></a>
                    </nav>
                </header>
                <div class="pageContentWrapper">
                    <h2>Vacancy title: {vacancy.getTitle()}</h2>
                    <div class="answerList">
                        <c:forEach var="answer" items="${vacancy.getAnswers().values()}">
                            <div class="vacancyAnswer">
                                <h3>Employee: ${answer.getEmployee().getEmail()}</h3>
                                <span>Posted: ${answer.formatTime()}</span>
                                <p>Answer:<br>${answer.getAnswer()}</p>
                            </div><br>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
