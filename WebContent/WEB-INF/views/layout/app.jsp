<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>家計簿</title>
        <link rel = "stylesheet" href="<c:url value='/css/reset.css'/>">
        <link rel = "stylesheet" href="<c:url value='/css/style.css'/>">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.min.js"></script>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_menu">
                <h1><a href="<c:url value='/' />">家計簿</a></h1>&nbsp;&nbsp;&nbsp;
                <c:if test="${sessionScope.login_employee != null || e != null}">
                    <a href="<c:url value='/kakeibo/index' />">記録</a>&nbsp;
                    <a href="<c:url value='/kakeibo/Chart' />">グラフ</a>&nbsp;
                    <a href="<c:url value='/employees/index?id=${sessionScope.login_employee.id}' />">設定</a>&nbsp;
                </c:if>
                </div>
                <c:if test="${sessionScope.login_employee != null}">
                    <div id="employee_name">
                        <c:out value="${sessionScope.login_employee.name}" />&nbsp;&nbsp;&nbsp;
                        <a href="<c:url value='/logout' />">ログアウト</a>
                    </div>
                </c:if>
            </div>

            <div id="content">
                ${param.content}
            </div>
            <div id = "footer">

            </div>
        </div>
    </body>
</html>