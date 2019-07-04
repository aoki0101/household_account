<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">


                <h2>家計簿</h2>
                <c:choose>
                    <c:when test="${fn:endsWith(pageContext.request.queryString ,'living_expense')}">
                        <h2>生活費 履歴</h2>
                    </c:when>
                    <c:when test="${fn:endsWith(pageContext.request.queryString ,'fixed_expense')}">
                        <h2>固定費 履歴</h2>
                    </c:when>
                    <c:when test="${fn:endsWith(pageContext.request.queryString ,'special_expense')}">
                        <h2>特別費 履歴</h2>
                    </c:when>
                    <c:when test="${fn:endsWith(pageContext.request.queryString ,'special_expense')}">
                        <h2>特別費 履歴</h2>
                    </c:when>
                </c:choose>


                <table>
                <c:if test="${
                fn:contains(pageContext.request.queryString , 'expense')
                ||
                fn:endsWith(pageContext.request.queryString , 'all')
                }">
                    <tr>

                        <th>品名</th>
                        <th>日付</th>
                        <th>価格</th>
                        <th>内容</th>

                    </tr>
                </c:if>
                <c:if test="${fn:contains(pageContext.request.queryString , 'income')}">
                    <tr>

                        <th>日付</th>
                        <th>価格</th>
                        <th>内容</th>

                    </tr>
                </c:if>
                </table>

                <c:forEach var="k" items = "${KAKEIBO}">

                    <table>
                        <c:choose>
                        <%-- 　支出カテゴリー　--%>
                            <c:when test="${fn:endsWith(pageContext.request.queryString ,'living_expense')}">

                            <c:if test="${k.category == 0 }">
                                <tr>
                                <td><a href="<c:url value='/kakeibo/edit?id=${k.id}' />"><c:out value="${k.product}"/></a></td>
                                <td><c:out value="${k.kakeibo_date}"/></td>
                                <td><c:out value="${k.cost}"/>円</td>
                                <td><c:out value="${k.content}" /></td>

                                </tr>
                            </c:if>
                        </c:when>
                        <c:when test="${fn:endsWith(pageContext.request.queryString ,'fixed_expense')}">

                                <c:if test="${k.category == 1 }">
                                <tr>
                                <td><a href="<c:url value='/kakeibo/edit?id=${k.id}' />"><c:out value="${k.product}"/></a></td>
                                <td><c:out value="${k.kakeibo_date}"/></td>
                                <td><c:out value="${k.cost}"/>円</td>
                                <td><c:out value="${k.content}" /></td>

                                </tr>
                        </c:if>
                        </c:when>
                        <c:when test="${fn:endsWith(pageContext.request.queryString ,'special_expense')}">
                                <c:if test="${k.category == 2 }">
                                <tr>
                                <td><a href="<c:url value='/kakeibo/edit?id=${k.id}' />"><c:out value="${k.product}"/></a></td>
                                <td><c:out value="${k.kakeibo_date}"/></td>
                                <td><c:out value="${k.cost}"/>円</td>
                                <td><c:out value="${k.content}" /></td>

                                </tr>
                        </c:if>
                        </c:when>
                         <%-- 　収入カテゴリー　--%>
                         <c:when test="${fn:endsWith(pageContext.request.queryString ,'month_income')}">
                                <c:if test="${k.category == 3 }">
                                <tr>

                                <td><a href="<c:url value='/kakeibo/edit?id=${k.id}' />"><c:out value="${k.kakeibo_date}"/></a></td>
                                <td><c:out value="${k.cost}"/>円</td>
                                <td><c:out value="${k.content}" /></td>

                                </tr>
                        </c:if>
                        </c:when>
                         <c:when test="${fn:endsWith(pageContext.request.queryString ,'extraordinary_income')}">

                                <c:if test="${k.category == 4 }">
                                <tr>

                                <td><a href="<c:url value='/kakeibo/edit?id=${k.id}' />"><c:out value="${k.kakeibo_date}"/></a></td>
                                <td><c:out value="${k.cost}"/>円</td>
                                <td><c:out value="${k.content}" /></td>

                                </tr>
                        </c:if>
                        </c:when>
                        <c:when test="${fn:endsWith(pageContext.request.queryString ,'other_income')}">
                                <c:if test="${k.category == 5 }">
                                <tr>

                                <td><a href="<c:url value='/kakeibo/edit?id=${k.id}' />"><c:out value="${k.kakeibo_date}"/></a></td>
                                <td><c:out value="${k.cost}"/>円</td>
                                <td><c:out value="${k.content}" /></td>

                                </tr>
                        </c:if>
                        </c:when>
                        <c:when test="${fn:endsWith(pageContext.request.queryString ,'total')}">
                                <c:if test="${k.category >= 3 }">
                                <tr>

                                <td><a href="<c:url value='/kakeibo/edit?id=${k.id}' />"><c:out value="${k.kakeibo_date}"/></a></td>
                                <c:if test="${k.category == 3}">
                                    <td><c:out value="${k.cost}"/>円</td>
                                </c:if>
                                 <c:if test="${k.category == 4}">
                                    <td><c:out value="${k.cost}"/>円</td>
                                </c:if>
                                 <c:if test="${k.category == 5}">
                                    <td><c:out value="${k.cost}"/>円</td>
                                </c:if>
                                <td><c:out value="${k.content}" /></td>

                                </tr>
                        </c:if>
                        </c:when>

                        <c:otherwise>
                            <c:if test="${k.category < 3 }">
                            <tr>
                                <td><a href="<c:url value='/kakeibo/edit?id=${k.id}' />"><c:out value="${k.product}"/></a></td>
                                <td><c:out value="${k.kakeibo_date}"/></td>
                                <c:if test="${k.category == 0}">
                                    <td><c:out value="${k.cost}"/>円</td>
                                </c:if>
                                 <c:if test="${k.category == 1}">
                                    <td><c:out value="${k.cost}"/>円</td>
                                </c:if>
                                 <c:if test="${k.category == 2}">
                                    <td><c:out value="${k.cost}"/>円</td>
                                </c:if>
                                <td><c:out value="${k.content}" /></td>

                                </tr>
                            </c:if>
                        </c:otherwise>
                        </c:choose>
                    </table>
                    </c:forEach>


                   <p><c:out value="${pageContext.request.contextPath}?show${queryString }" /></p>
                   <p><c:out value="${pageContext.request.contextPath}/kakeibo/show" /></p>
                   <p><c:out value="${last_date}"/></p>
        <p><a href="<c:url value='/kakeibo/index' />">家計簿に戻る</a></p>
    </c:param>
    </c:import>

    <%--反省 --%>
    <%--
    DBにcategoryを作成してあるのに対して、
    living_expense,fixed_expense,special_expense
    二重判断の上、コードが分かりにくくなる。
    しかしヌルぽが発生するかもしれない。
    改善としては、
    金額はすべて @Column (cost)に入れcategoryで分ける。


    --%>
