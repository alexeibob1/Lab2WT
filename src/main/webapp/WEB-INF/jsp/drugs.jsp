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
    <fmt:message key="drug.edit" var="edit"/>
    <fmt:message key="drug.delete" var="delete"/>
    <fmt:message key="drug.basket" var="addToBasket"/>
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
                    <c:choose>
                        <c:when test="${sessionScope.role.name eq 'PHARMACIST' or sessionScope.role.name eq 'ADMIN'}">
                            <td>
                                <form action="<c:url value="/edit-product"/>" method="get">
                                    <input type="hidden" value="${product.productID}" name="productID" id="editProductID">
                                    <div>
                                        <input type="submit" value="${edit}">
                                    </div>
                                </form>
                            </td>
                            <td>
                                <form action="<c:url value="/delete-product"/>" method="get">
                                    <input type="hidden" value="${product.productID}" name="productID" id="deleteProductID">
                                    <div>
                                        <input type="submit" value="${delete}">
                                    </div>
                                </form>
                            </td>
                        </c:when>
                        <c:when test="${sessionScope.role.name eq 'CLIENT' or empty sessionScope.role}">
                            <td>
                                <form action="<c:url value="/add-to-basket"/>" method="post">
                                    <input type="hidden" value="${product.productID}" name="${product.productID}" id="${product.productID}">
                                    <input type="number" name="amount" id="amount" value="1" min="1" max="${product.amount}">
                                    <div>
                                        <input type="submit" value="${addToBasket}">
                                    </div>
                                </form>
                            </td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>    
        </tbody>
    </table>

</body>
</html>
