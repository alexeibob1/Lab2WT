<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="language">
    <fmt:message key="header.login" var="title"/>
    <fmt:message key="username" var="username"/>
    <fmt:message key="password" var="password"/>
</fmt:bundle>
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty sessionScope.login}">
        <c:redirect url="/pharmacy/home"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="header.jsp" />
        <form action="<c:url value='login'/>"method="post">
            <label for="username">${username}</label>
            <input type="text" name="username" id="username">
            <br>
            <label for="password">${password}</label>
            <input type="password" name="password" id="password">
            <br>
            <input type="submit" value="${title}">
        </form>
    </c:otherwise>
</c:choose>


</body>
</html>