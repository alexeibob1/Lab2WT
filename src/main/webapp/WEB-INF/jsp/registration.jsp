<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="language">
    <fmt:message key="header.registration" var="title"/>
    <fmt:message key="username" var="username"/>
    <fmt:message key="password" var="password"/>
    <fmt:message key="email" var="email"/>
    <fmt:message key="confirmPassword" var="confirmPassword"/>
</fmt:bundle>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <script src="${pageContext.request.contextPath}/static/js/login_validation.js"></script>
    <title>${title}</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.login}">
        <c:redirect url="/home"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="header.jsp" />
        <form action="register" method="post" id="reg-form">
            <input type="hidden" id="locale" value="${sessionScope.locale}" />
            <label for="email">${email}</label>
            <input type="email" name="email" id="email" required>
            <br>
            <label for="username">${username}</label>
            <input type="text" name="username" id="username" required maxlength="20">
            <br>
            <label for="password">${password}</label>
            <input type="password" name="password" id="password" required minlength="7" maxlength="25">
            <br>
            <label for="confirmPassword">${confirmPassword}</label>
            <input type="password" name="confirmPassword" id="confirmPassword" required minlength="7" maxlength="25">
            <br>
            <div id="validationMessage"></div>
            <input type="submit" id="submit" value="${title}">
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>