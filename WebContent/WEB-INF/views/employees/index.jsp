<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
        <c:param name = "content">
            <c:if test = "${flush != null}">
                <div id="flush_success">
                        <c:out value="${flush}" />
                </div>
            </c:if>
            <h2>あなたの設定</h2>
            <table id="employee_list">
                <tbody>
                    <tr>
                        <th>ログインID</th>
                        <th>氏名</th>

                    </tr>
                    <tr>
                        <td><c:out value="${employee.code}" /></td>
                        <td><a href = "<c:url value = '/employees/show?id=${employee.id}' />"><c:out value="${employee.name}"/></a></td>
                    </tr>
                </tbody>
            </table>
            <p><a href="#" onclick="confirmDestroy();">データを削除する</a></p>
                <form method="POST" action="<c:url value='/employees/destroy' />">
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