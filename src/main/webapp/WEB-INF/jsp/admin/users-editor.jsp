<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="language">
    <fmt:message key="users_editor.title" var="title"/>
    
    <fmt:message key="user.role" var="role"/>
    <fmt:message key="user.name" var="name"/>
    <fmt:message key="user.surname" var="surname"/>
    <fmt:message key="user.patronymic" var="patronymic"/>
    <fmt:message key="user.birth_date" var="birthdate"/>
    <fmt:message key="user.username" var="username"/>
    <fmt:message key="user.email" var="email"/>
    <fmt:message key="user.password" var="password"/>
    <fmt:message key="user.edit" var="editAction"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">

</head>
<body>
    <jsp:include page="../header.jsp"/>
    <c:set var="users" value="${requestScope.usersView.users}"/>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>${role}</th>
                <th>${name}</th>
                <th>${surname}</th>
                <th>${patronymic}</th>
                <th>${birthdate}</th>
                <th>${username}</th>
                <th>${email}</th>
                <th>${password}</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.role.name}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.patronymic}</td>
                    <td>${user.birthDate}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.password}</td>
                    <td>
                        <c:choose>
                            <c:when test="${user.username ne sessionScope.username}">
                                <form action="<c:url value="/admin/users/show"/>" method="get">
                                    <input type="hidden" value="${user.userId}" name="userID">
                                    <input type="submit" value="${editAction}">
                                </form>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
