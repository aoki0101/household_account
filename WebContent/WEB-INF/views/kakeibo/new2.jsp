<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
<c:param name="content">
<form method="POST" action="<c:url value='/kakeibo/create' />">
<c:if test="${errors != null }">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
                ・<c:out value="${error }" />
        </c:forEach>
    </div>
</c:if>
<h2>収入　新規作成</h2>
<label for="kakeibo_date">日付</label><br />
<input type="date" name="kakeibo_date" value="<fmt:formatDate value='${kakeibo.kakeibo_date}' pattern='yyyy-MM-dd' />" />
<br /><br />

<label for="category" >項目</label><br />
    <select name="category" id="category">
        <option value="3" <c:if test="${kakeibo.category == 3}">selected</c:if>>月入</option>
        <option value="4" <c:if test="${kakeibo.category == 4}">selected</c:if>>臨時収入</option>
        <option value="5" <c:if test="${kakeibo.category == 5}">selected</c:if>>その他</option>
    </select>

    <label for="cost">金額</label>
<input type="number" name="cost" value="0"<c:out value="円"/>>
<br />
<label for="content">内容</label><br />
<textarea name="content" rows="3" cols="30">${kakeibo.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">作成</button>
</form>
        <p><a href="<c:url value='/kakeibo/new' />">支出を記録</a></p>
        <p><a href="<c:url value='/kakeibo/index' />">家計簿に戻る</a></p>
</c:param>
</c:import>