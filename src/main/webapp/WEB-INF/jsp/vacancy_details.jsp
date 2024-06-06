<%-- 
    Document   : vacancy_details
    Created on : 5 черв. 2024 р., 14:44:38
    Author     : sasha
--%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ISoWS: ${vacancy.getTitle()}</title>
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
                        <a class="profileLink" href="${pageContext.request.contextPath}/users"><img src="<c:url value='/resources/img/account.png'/>" alt="profile icon"/></a>
                    </nav>
                </header>
                <div class="vacancyMeatWrapper">
                    <div class="vacancyDetails">
                        <h2>${vacancy.getTitle()}</h2><span>${vacancy.formatTime()}</span>
                        <h3>Employer: ${vacancy.getEmployer().getName()}</h3>
                        <p>${vacancy.getDescription()}</p>
                    </div>
                    <div class="answerWrapper <c:if test="${role == 'employee'}">showing</c:if>">
                            <h3>Answer to the vacancy:</h3>
                            <form style="flex-direction: column" action="vacancies" method="POST" class="leaveAnswer">
                                <label for="answer">Enter your answer:</label>
                                <textarea name="answer" id="answer" rows="4" cols="50"></textarea><br><br>
                                <input type="hidden" name="vacancyId" value="${vacancy.getId()}">
                            <input type="hidden" name="command" value="leaveAnswer">
                            <label>All needed details will be passed to the employer automatically.</label><br>
                            <input type="submit" value="Submit!" style="max-width: 200px">
                        </form>
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
