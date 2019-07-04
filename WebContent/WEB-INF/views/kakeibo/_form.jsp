<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null }">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
                ・<c:out value="${error }" />
        </c:forEach>
    </div>
</c:if>




<label for="kakeibo_date">日付</label><br />
<input type="date" name="kakeibo_date" value="<fmt:formatDate value='${kakeibo.kakeibo_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for ="product">商品名</label><br />
<input type="text" name="product"  value="<c:out value='${kakeibo.product}'/>">
<br /><br />

<label for="category" >項目</label><br />
    <select name="category" id="category">
        <option value="0" <c:if test="${kakeibo.category == 0}">selected</c:if>>生活費</option>
        <option value="1" <c:if test="${kakeibo.category == 1}">selected</c:if>>固定費</option>
        <option value="2" <c:if test="${kakeibo.category == 2}">selected</c:if>>特別費</option>
    </select>
<!--Javascriptで対応-->
<label for="cost">金額</label>
<input type="number" name="cost" value=
        <c:choose>
            <c:when test="${cate == 0}">
                <c:out value="${kakeibo.cost}"/>
            </c:when>
            <c:when test="${cate == 1}">
                <c:out value="${kakeibo.cost}"/>
            </c:when>
            <c:when test="${cate == 2}">
                <c:out value="${kakeibo.cost}"/>
            </c:when>
            <c:when test="${cate == 3}">
                <c:out value="${kakeibo.cost}"/>
            </c:when>
            <c:when test="${cate == 4}">
                <c:out value="${kakeibo.cost}"/>
            </c:when>
            <c:when test="${cate == 5}">
                <c:out value="${kakeibo.cost}"/>
            </c:when>
        </c:choose>
>
<br />
<label for="content">内容</label><br />
<textarea name="content" rows="3" cols="30">${kakeibo.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">作成</button>
