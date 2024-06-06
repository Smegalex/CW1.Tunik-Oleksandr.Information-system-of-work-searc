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
