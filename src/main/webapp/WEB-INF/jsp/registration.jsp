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
    <fmt:message key="registration.success" var="successfulRegistration"/>
    <fmt:message key="registration.error" var="failedRegistration"/>
    <fmt:message key="user.name" var="name"/>
    <fmt:message key="user.surname" var="surname"/>
    <fmt:message key="user.patronymic" var="patronymic"/>
    <fmt:message key="user.birth_date" var="birthDate"/>
</fmt:bundle>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <script src="${pageContext.request.contextPath}/static/js/validation/registration_validation.js"></script>
    <title>${title}</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.username}">
        <c:redirect url="/home"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="header.jsp" />
        <form action="<c:url value="register"/>" method="post" id="reg-form">
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

            <label for="name">${name}</label>
            <input type="text" name="name" id="name" required minlength="2" maxlength="40">
            <br>

            <label for="surname">${surname}</label>
            <input type="text" name="surname" id="surname" required minlength="2" maxlength="40">
            <br>

            <label for="patronymic">${patronymic}</label>
            <input type="text" name="patronymic" id="patronymic" minlength="2" maxlength="40">
            <br>

            <label for="birthDate">${birthDate}</label>
            <input type="date" name="birthDate" id="birthDate" required>
            <br>
            
            <div id="validationMessage"></div>
            
            <input type="submit" id="submit" value="${title}">
            
            <c:choose>
                <c:when test="${sessionScope.reg_message eq 1}">
                    <div id="reg-message" class="reg-message reg-success">${successfulRegistration}</div>
                    <c:remove var="reg_message" scope="session"/>
                </c:when>
                <c:when test="${sessionScope.reg_message eq 2}">
                    <div id="reg-message" class="reg-message reg-error">${failedRegistration}</div>
                    <c:remove var="reg_message" scope="session"/>
                </c:when>
            </c:choose>
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>