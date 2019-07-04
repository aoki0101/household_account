<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="All_total" value="${L_total + F_total + S_total }" scope="session"/>
<c:set var="All_Income" value="${Income + E_income + O_income}" scope="session"/>
<c:set var="Difference_cost" value="${All_Income - All_total }" scope="session"/>
<%

%>

<c:import url="/WEB-INF/views/layout/app.jsp" >
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}" />
            </div>
        </c:if>

        <c:forEach var="k" items="${KAKEIBO}">

        </c:forEach>

        <h2><c:out value='${month}'/>月　収支一覧</h2><br />
        <!--  テスト-->





<!--
月を取得
        <h2><c:out value="${first_date}" /></h2>
        <h2><c:out value="${last_date}" /></h2>
        <h2><c:out value="${thisMonthfirstDay}" /></h2>
-->
        <table id="kakeibo_list">
            <tbody>
            <tr>
                <th><a href="<c:url value='/kakeibo/show?all' />">支出</a></th>
                <th><a href="<c:url value='/kakeibo/show?living_expense'/>">生活費</a></th>
                <th><a href="<c:url value='/kakeibo/show?fixed_expense'/>">固定費</a></th>
                <th><a href="<c:url value='/kakeibo/show?special_expense'/>">特別費</a></th>
            </tr>

                        <tr>
                            <td class=""><c:out value="${All_total}" />円</td>
                            <td ><c:out value="${L_total}" />円</td>
                            <td class=""><c:out value="${F_total}" />円</td>
                            <td class=""><c:out value="${S_total}" />円</td>
                        </tr>
            </tbody>
        </table>
        <table id="kakeibo_list">
            <tbody>
                    <tr>
                        <th ><a href="<c:url value='/kakeibo/show?income_total' />">収入</a></th>
                        <th><a href="<c:url value='/kakeibo/show?month_income' />">月収</a></th>
                        <th><a href="<c:url value='/kakeibo/show?extraordinary_income' />">臨時収入</a></th>
                        <th><a href="<c:url value='/kakeibo/show?other_income' />">その他</a></th>
                    </tr>
                        <tr >
                            <td class=""><c:out value="${All_Income}" />円</td>
                            <td class=""><c:out value="${Income}" />円</td>
                            <td class=""><c:out value="${E_income}" />円</td>
                            <td class=""><c:out value="${O_income}" />円</td>
                        </tr>
            </tbody>
        </table>
        <table>
            <tbody>
            <tr><th>収支<th><c:out value="${Difference_cost}" />円<tr>

            </tbody>
        </table>
        <!-- pagenation -->
         <div id="pagination">

            <c:forEach var="i" begin="1" end="12" step="1">
                <c:choose>
                    <c:when test="${i == month}">
                        <c:out value="${month}" /><c:out value="月"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/kakeibo/index?month=${i-1}"><c:out value="${i}" />月</a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <!-- pagenation -->



        <p><a href="<c:url value='/kakeibo/new' />">支出を記録</a></p>
        <p><a href="<c:url value='/kakeibo/new2' />">収入を記録</a></p>


    </c:param>
</c:import>

