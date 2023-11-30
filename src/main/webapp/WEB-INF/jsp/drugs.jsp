<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:bundle basename="language">
    <fmt:message key="drug.name" var="name"/>
    <fmt:message key="drug.manufacturer" var="manufacturer"/>
    <fmt:message key="drug.dosage" var="dosage"/>
    <fmt:message key="drug.dosageUnit" var="dosageUnit"/>
    <fmt:message key="drug.form" var="form"/>
    <fmt:message key="drug.needsPrescription" var="prescription"/>
    <fmt:message key="drug.price" var="price"/>
    <fmt:message key="drug.amount" var="amount"/>
</fmt:bundle>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>
<body>
    <jsp:include page="header.jsp"/>
    <c:set var="products" value="${requestScope.productsView.products}" />
    <table>
        <thead>
        <tr>
            <th>${name}</th>
            <th>${manufacturer}</th>
            <th>${dosage}</th>
            <th>${dosageUnit}</th>
            <th>${form}</th>
            <th>${prescription}</th>
            <th>${price}</th>
            <th>${amount}</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
    
                <tr>
                    <td>${product.name}</td>
                    <td>${product.manufacturer}</td>
                    <td>${product.dosage}</td>
                    <td>${product.dosageUnit}</td>
                    <td>${product.drugForm}</td>
                    <td>${product.isNeedPrescription}</td>
                    <td>${product.price}</td>
                    <td>${product.amount}</td>
    
                        <%--        Make button for admin/pharmacist for edit--%>
                        <%--        Make button for adding to basket--%>
                </tr>
            </c:forEach>    
        </tbody>
    </table>

</body>
</html>
