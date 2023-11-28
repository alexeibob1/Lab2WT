<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="language">
    <fmt:message key="user.name" var="name"/>
    <fmt:message key="user.surname" var="surname"/>
    <fmt:message key="user.patronymic" var="patronymic"/>
    <fmt:message key="user.birth_date" var="birthDate"/>
    <fmt:message key="user.username" var="username"/>
    <fmt:message key="user.email" var="email"/>
    <fmt:message key="user.password" var="password"/>
    <fmt:message key="user.edit" var="edit"/>
    <fmt:message key="user.role" var="roleLabel"/>
    <fmt:message key="user.edit_message" var="editMessage"/>
</fmt:bundle>
<html>
<head>
    <title>Редактируем пользователя</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<form action="<c:url value="/admin/users/change-user"/>" method="post">
    <input type="hidden" name="userID" value="${requestScope.user.userId}">
    <c:set var="roles" value="${requestScope.roles}"/>
    <label for="role">${roleLabel}</label>
    <select name="role" id="role">
        <c:forEach var="role" items="${roles.roles}">
            <c:choose>
                <c:when test="${requestScope.user.role.name eq role.name}">
                    <option value="${role.id}.${role.name}" selected>${role.name}</option>
                </c:when>
                <c:otherwise>
                    <option value="${role.id}.${role.name}">${role.name}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <div>
        <label for="name">${name}</label>
        <input type="text" id="name" name="name" value="${requestScope.user.name}">
    </div>
    <div>
        <label for="surname">${surname}</label>
        <input type="text" id="surname" name="surname" value="${requestScope.user.surname}">
    </div>
    <div>
        <label for="patronymic">${patronymic}</label>
        <input type="text" id="patronymic" name="patronymic" value="${requestScope.user.patronymic}">
    </div>
    <div>
        <label for="birthDate">${birthDate}</label>
        <input type="date" id="birthDate" name="birthDate" value="${requestScope.user.birthDate}">
    </div>
    <div>
        <label for="username">${username}</label>
        <input type="text" id="username" name="username" value="${requestScope.user.username}" readonly>
    </div>
    <div>
        <label for="email">${email}</label>
        <input type="email" id="email" name="email" value="${requestScope.user.email}" readonly>
    </div>
    <div>
        <label for="password">${password}</label>
        <input type="text" id="password" name="password" value="${requestScope.user.password}" readonly>
    </div>
    <input type="submit" value="${edit}">
    <c:choose>
        <c:when test="${not empty sessionScope.edit_message}">
            <div class="edit-message">
                    ${editMessage}
            </div>
            <c:remove var="edit_message" scope="session" />
        </c:when>
    </c:choose>
</form>
</body>
</html>
