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
                        <a class="homeLink" href="${pageContext.request.contextPath}/vacancies"><img src="<c:url value='/resources/img/home.png'/>" alt="home icon"/></a>
                        <form action="search" method="GET">
                            <input type="text" name="searchText" placeholder="Search...">
                            <input type="hidden" name="command" value="search">
                            <input type="submit" value="Search">
                        </form>
                        <a href=""><img src="<c:url value='/resources/img/account.png'/>" alt="profile icon"/></a>
                    </nav>
                </header>
                <div class="profileContentWrapper">
                    <h1>Welcome back, ${user.getName()}!</h1>
                    <form action="vacancies" method="POST">
                        <input type="hidden" name="command" value="createRedirect"/>
                        <input type="submit" value="Create new vacancy" />
                    </form>
                    <a href="${pageContext.request.contextPath}/users?command=logout">Log out.</a>
                    <div class="vacancyList">
                        <c:if test='${!user.getPostedVacancies().isEmpty()}'><h2>Your vacancies:</h2></c:if>
                        <c:forEach var="vacancy" items="${user.getPostedVacancies().values()}">
                            <div class="vacancy" id="${vacancy.getId()}">
                                <h3>Title: <c:out value="${vacancy.getTitle()}"/></h3>
                                <span>Status: 
                                    <c:choose> 
                                        <c:when test="${vacancy.isOpenStatus()}">Live</c:when>
                                        <c:otherwise> Closed for public </c:otherwise>
                                    </c:choose>
                                </span>
                                <div class="buttons">
                                    <form action="vacancies" method="GET">
                                        <input type="hidden" name="command" value="view"/>
                                        <input type="hidden" name="id" value="${vacancy.getId()}" />
                                        <input type="submit" value="View vacancy" />
                                    </form>
                                    <form action="vacancies" method="POST">
                                        <input type="hidden" name="command" value="answers"/>
                                        <input type="hidden" name="id" value="${vacancy.getId()}" />
                                        <input class="success" type="submit" value="View answers" />
                                    </form>
                                    <form action="vacancies" method="POST">
                                        <input type="hidden" name="command" value="editRedirect"/>
                                        <input type="hidden" name="id" value="${vacancy.getId()}" />
                                        <input type="submit" value="Edit vacancy" />
                                    </form>
                                    <form action="vacancies" method="POST">
                                        <input type="hidden" name="command" value="delete"/>
                                        <input type="hidden" name="id" value="${vacancy.getId()}" />
                                        <input class="error" type="submit" value="Delete vacancy" />
                                    </form>
                                </div>
                                <tr>
                            </div>
                        </c:forEach>
                    </div>
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
