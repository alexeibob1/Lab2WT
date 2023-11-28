<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="language">
    <fmt:message key="header.login" var="title"/>
    <fmt:message key="username" var="username"/>
    <fmt:message key="password" var="password"/>
    <fmt:message key="email" var="email"/>
    <fmt:message key="login.error" var="failedLogin" />
</fmt:bundle>
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.username}">
        <c:redirect url="/home"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="header.jsp" />
        <form action="<c:url value='login'/>"method="post">
            <label for="username">${username}/${email}</label>
            <input type="text" name="usernameOrEmail" id="username" required maxlength="20">
            <br>
            <label for="password">${password}</label>
            <input type="password" name="password" id="password" required minlength="7">
            <br>
            <input type="submit" value="${title}">
        </form>
        <c:choose>
            <c:when test="${not empty requestScope.login_message}">
                <div id="login-message" class="login-message login-error">${failedLogin}</div>
                <c:remove var="login_message" scope="session"/>
            </c:when>
        </c:choose>
    </c:otherwise>
</c:choose>


</body>
</html>