<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- validateの活用--%>
<c:if test="${errors != null }">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>
    </div>
</c:if>
<label for="code">ログインID</label><br />
<input type="text" name="code" value="${login_employee.code}" />
<br /><br />

<label for = "name">氏名</label><br />
<input type="text" name="name" value="${login_employee.name}" />
<br /><br />

<label for="password">パスワード</label><br />
<input type="text" name="password"  />
<br /><br />

<input type="hidden" name="_token" value="${_token }" />
<button type="submit">作成</button>