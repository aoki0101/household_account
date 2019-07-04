<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name = "content">
    <c:choose>
        <c:when test="${kakeibo != null }">
        <c:if test = "${cate < 3}">
            <h2>記録編集</h2>
            <form method="POST" action="<c:url value='/kakeibo/update' />">
                 <c:import url="_form.jsp" />
            </form>
        </c:if>
        <c:if test = "${cate >= 3 }">
            <h2>記録編集</h2>
            <form method="POST" action="<c:url value='/kakeibo/update' />">
                <c:import url="income_form.jsp"/>
            </form>
        </c:if>
        </c:when>
        <c:otherwise>
                <h2>お探しのデータは見つかりませんでした</h2>
        </c:otherwise>
    </c:choose>
    <p><c:out value="${cate}" /></p>
    <p><a href="<c:url value='/kakeibo/index' />">一覧に戻る</a></p>
    <p><a href="#" onclick="confirmDestroy();">この記録を削除する</a></p>
        <form method="POST" action="${pageContext.request.contextPath}/kakeibo/destroy">
            <input type="hidden" name="_token" value="${_token}" />
        </form>
        <script>
        function confirmDestroy() {
            if(confirm("本当に削除してよろしいですか？")) {
                document.forms[1].submit();
            }
        }
        </script>
    </c:param>
</c:import>