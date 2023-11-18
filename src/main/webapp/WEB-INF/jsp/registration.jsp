<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="language">
    <fmt:message key="header.registration" var="title"/>
    <fmt:message key="username" var="username"/>
    <fmt:message key="password" var="password"/>
</fmt:bundle>
<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<jsp:include page="header.jsp" />
<form action="<c:url value='register'/>"method="post">
    <label for="username">${username}</label>
    <input type="text" name="username" id="username">
    <label for="password">${password}</label>
    <input type="password" name="password" id="password">
    <input type="submit" value="${title}">
</form>
</body>
</html>