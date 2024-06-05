<%-- 
    Document   : main
    Created on : 5 черв. 2024 р., 14:43:59
    Author     : sasha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <nav>
                <form action="search" method="GET">
                    <input type="text" name="searchText" placeholder="Search...">
                    <input type="hidden" name="command" value="search">
                    <input type="submit" value="Search">
                </form>
                <a href="/users"><img src="../../img/account.png" alt="alt"/></a>
            </nav>
        </header>
        <div class="vacanciesBody">
            <c:forEach var="vacancy" items="${vacancies}">
                <div class="vacancyContainer" id="${vacancy.getId}">
                    <h3><c:out value="${vacancy.getTitle}"/></h3>
                    <p><c:out value="${vacancy.getDescription}"/></p>
                    <form action="riddles" method="GET">
                        <input type="hidden" name="command" value="view"/>
                        <input type="hidden" name="id" value="${vacancy.id}" />
                        <input type="submit" value="View" />
                    </form></div>
            </c:forEach>
        </div>
    </body>
</html>
