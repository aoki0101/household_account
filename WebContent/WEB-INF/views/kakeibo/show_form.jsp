<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/kakeibo" var=""/>


<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        
            
                <h2>家計簿　
                
                履歴</h2>
                
                <table>
                    <tr>
                        <td>品名</td>
                        <td>日付</td>
                        <td>金額</td>
                        <td>内容</td>
                    </tr>
                </table>
                <c:forEach var="k" items = "${KAKEIBO}">
                    <c:if test="${k.category == 0}">
                        <table>
                            <tr>
                                <td><c:out value="${k.product}"/></td>
                                <td><c:out value="${k.kakeibo_date}"/></td>
                                <td><c:out value="${k.living_expense}"/></td>
                                <td><c:out value="${k.content}" /></td>
                            </tr>
                         </table>
                    </c:if>
                    
              </c:forEach>
               
             
               
            
        
        
        <p><a href="<c:url value='/kakeibo/index' />">家計簿に戻る</a></p>
    </c:param>
    </c:import>
