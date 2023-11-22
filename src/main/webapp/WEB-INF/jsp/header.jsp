<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="language">
    <fmt:message key="header.home" var="home"/>
    <fmt:message key="header.login" var="login"/>
    <fmt:message key="header.registration" var="registration"/>
    <fmt:message key="header.logout" var="logout"/>
    <fmt:message key="english" var="english"/>
    <fmt:message key="russian" var="russian"/>
</fmt:bundle>
<%--    Here will be a great block of fmt messages--%>

<header>
    <a href="<c:url value='home'/>">${home}</a>

    <c:choose>
        <c:when test="${not empty sessionScope.login}">

<%--            Here place links for specific roles--%>
            <a href="<c:url value='logout'/>">${logout}</a>
        </c:when>
        <c:otherwise>
            <a href="<c:url value='login-form'/>">${login}</a>
            <a href="<c:url value='register-form'/>">${registration}</a>
        </c:otherwise>
    </c:choose>

    <a href="<c:url value='change-locale?locale=ru'/>">${russian}</a>
    <a href="<c:url value='change-locale?locale=en'/>">${english}</a>
    <br>
</header>