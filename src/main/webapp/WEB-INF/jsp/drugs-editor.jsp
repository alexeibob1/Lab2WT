<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="language">
    <fmt:message key="drugs_editor.title" var="title"/>
</fmt:bundle>
<html>
<head>
    <title>${title}</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    HELLO ADMIN!
</body>
</html>
