<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%
//選択肢としては、Objectのリストをkakeiboのリストに変換する。
//→エラーとして、kakeiboの型がないパラメーターがない。
//jspでDBの操作ができるのか？
//今日の日付ならその値を足していく。
//DAYを取得して、データを入力するさいに、kakeibo_dateで同日か判断する。
%>
<%@ page import="net.arnx.jsonic.JSON" %>
<c:import url="/WEB-INF/views/layout/app.jsp" >

    <c:param name="content">
    <c:out value="${sessionScope.All_total}"/>
    <c:out value="${today}" />

    <c:out value="${dd}" />
    <h2>カテゴリーグラフ</h2>
    <h2><c:out value="${Ld}" />日</h2>
    <c:choose>
    <c:when test = "${All_total != 0}">
    <div style="width:50%; float:left">
        <canvas id="e_Chart"></canvas>
         <table style="width:50%;">
            <tr><th>収入合計</th><td><c:out value="${All_total}"/>円</td></tr>
            <tr><th>生活費</th><td><c:out value="${L_total}" />円</td></tr>
             <tr><th>固定費</th><td><c:out value="${F_total}" />円</td></tr>
             <tr><th>特別費</th><td><c:out value="${S_total}" />円</td></tr>
        </table>
    </div>

    <c:if test ="${Income + E_income + O_income >0}">
    <div style="width:50%; float:right;">
        <canvas id = "i_Chart"></canvas>
         <table style="width:50%; ">
            <tr><th>支出合計</th><td><c:out value="${Income + E_income + O_income}" />円</td></tr>
            <tr><th>月収</th><td><c:out value="${Income}" />円</td></tr>
             <tr><th>臨時収入</th><td><c:out value="${E_income}" />円</td></tr>
             <tr><th>その他</th><td><c:out value="${O_income}" />円</td></tr>
        </table>
    </div>
    </c:if>
    <h2>-------------------------------------------------------</h2>
    <h2>収支の折れ線グラフ</h2>
    <div>
    <canvas id="bar_Chart"></canvas>
    </div>
    <script type = "text/javascript" >
    var ctx = document.getElementById("e_Chart").getContext('2d');
    var myChart = new Chart(ctx, {
      type: 'pie',
      data: {
        labels: ["生活費", "固定費", "特別費"],
        datasets: [{
          backgroundColor: [
            "#2ecc71",
            "#3498db",
            "#95a5a6"

          ],
          data: [
              <%=request.getAttribute("L_total")%>,
              <%=request.getAttribute("F_total")%>,
              <%=request.getAttribute("S_total")%>,
              ]
        }]
      }
    });
    var ctx= document.getElementById("i_Chart").getContext('2d');
    var i_Chart = new Chart(ctx, {
        type:'pie',
        data:{
            labels:["月収","臨時収入","その他"],
            datasets:[{
                backgroundColor: [
                    "#b44c97",
                    "#38b48b",
                    "#f5b1aa"
                ],
                data: [
                    <%=request.getAttribute("Income")%>,
                    <%=request.getAttribute("E_income")%>,
                    <%=request.getAttribute("O_income")%>,
                ]
            }]
        }
    });
    //棒グラフ作成

    var ctx = document.getElementById("bar_Chart").getContext("2d");
    var chart = new Chart(ctx, {
          type: "line",
          data:{

              labels:[
                    <%
                    int Last_d = Integer.valueOf(String.valueOf(request.getAttribute("Ld")));
                    int i = 1;
                    while(i <=Last_d){
                        String M = String.valueOf(request.getAttribute("month"));
                        out.print("\"" + M + "月" + i + "日"+ "\"" + ",");
                        i++;
                    }


                    %>
       ],
            datasets:[
            {

            label:"収入",
              data:
                    <%=request.getAttribute("I_total")%>
              ,
              borderColor: "rgba(255,0,0,1)",
              backgroundColor: "rgba(0,0,0,0)"
            },
            {

            label:"支出",
              data:<%=request.getAttribute("T_total")%>,
              borderColor:"rgba(0,0,255,1)",
              backgroundColor: "rgba(0,0,0,0)"
            },



          ]
          },

        });
    </script>
    <!-- pagenation -->
         <div id="pagination">

            <c:forEach var="i" begin="1" end="12" step="1">
                <c:choose>
                    <c:when test="${i == month}">
                        <c:out value="${month}" /><c:out value="月"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/kakeibo/Chart?month=${i-1}"><c:out value="${i}" />月</a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <!-- pagenation -->
    </body>
    </c:when>

    <c:otherwise>
       <h3>支出を入力してください。</h3>
        <!-- pagenation -->
         <div id="pagination">

            <c:forEach var="i" begin="1" end="12" step="1">
                <c:choose>
                    <c:when test="${i == month}">
                        <c:out value="${month}" /><c:out value="月"/>&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/kakeibo/Chart?month=${i-1}"><c:out value="${i}" />月</a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <!-- pagenation -->
    </c:otherwise>
    </c:choose>

   </c:param>
    </c:import>


