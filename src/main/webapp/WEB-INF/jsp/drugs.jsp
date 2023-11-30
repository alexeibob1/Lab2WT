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
    <fmt:message key="drug.addProductFormTitle" var="addProductFormTitle"/>
    <fmt:message key="drug.addDrug" var="addDrug"/>
    <fmt:message key="yes" var="yes"/>
    <fmt:message key="no" var="no"/>
    <fmt:message key="russian" var="ru"/>
    <fmt:message key="english" var="en"/>
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
                    <td>
                        <c:choose>
                            <c:when test="${product.isNeedPrescription eq true}">
                                ${yes}
                            </c:when>
                            <c:otherwise>
                                ${no}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${product.price}</td>
                    <td>${product.amount}</td>
                    <c:choose>
                        <c:when test="${sessionScope.role.name eq 'PHARMACIST' or sessionScope.role.name eq 'ADMIN'}">
                            <td>
                                <form action="<c:url value="/edit-drug"/>" method="get">
                                    <input type="hidden" value="${product.productID}" name="id" id="editProductID">
                                    <div>
                                        <input type="submit" value="${edit}">
                                    </div>
                                </form>
                            </td>
                            <td>
                                <form action="<c:url value="/delete-drug"/>" method="get">
                                    <input type="hidden" value="${product.productID}" name="id" id="deleteProductID">
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
                                    <input type="number" name="basketAmount" id="basketAmount" value="1" min="1" max="${product.amount}">
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
    <c:if test="${sessionScope.role.name eq 'ADMIN' or sessionScope.role.name eq 'PHARMACIST'}">
        <div style="font-weight: bold">
            ${addProductFormTitle}
        </div>
        <form action="<c:url value="/add-new-drug" />" method="post">
            <div style="font-weight: bold">
                ${ru}
            </div>
            <div>
                <input type="text" placeholder="${name}" name="name_ru" id="name_ru" required>
            </div>
            <div>
                <input type="text" placeholder="${manufacturer}" name="manufacturer_ru" id="manufacturer_ru" required>
            </div>
            <div>
                <input type="text" placeholder="${dosageUnit}" name="dosageUnit_ru" id="dosageUnit_ru" required>
            </div>
            <div>
                <input type="text" placeholder="${form}" name="form_ru" id="form_ru" required>
            </div>

            <div style="font-weight: bold">
                    ${en}
            </div>
            <div>
                <input type="text" placeholder="${name}" name="name_en" id="name_en" required>
            </div>
            <div>
                <input type="text" placeholder="${manufacturer}" name="manufacturer_en" id="manufacturer_en" required>
            </div>
            <div>
                <input type="text" placeholder="${dosageUnit}" name="dosageUnit_en" id="dosageUnit_en" required>
            </div>
            <div>
                <input type="text" placeholder="${form}" name="form_en" id="form_en" required>
            </div>
            
            <div>
                ${prescription}
                <div>
                    <label for="radio1">${yes}</label>
                    <input type="radio" name="prescriptionOption" id="radio1" value="yes">
                </div>
                    <div>
                        <label for="radio2">${no}</label>
                        <input type="radio" name="prescriptionOption" id="radio2" value="no">
                    </div>
            </div>
            
            <div>
                <input type="number" name="amount" id="amount" placeholder="${amount}">
            </div>
            <div>
                <input type="number" name="price" id="price" placeholder="${price}" step="0.01">
            </div>
            <div>
                <input type="number" name="dosage" id="dosage" placeholder="${dosage}" step="0.01">
            </div>
            
            
            <input type="submit" value="${addDrug}">
        </form>
    </c:if>
</body>
</html>
