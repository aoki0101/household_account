package controllers.kakeibo;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.kakeibo;
import utils.DBUtil;

/**
 * Servlet implementation class KakeiboIndexServlet
 */
@WebServlet("/kakeibo/index")
public class KakeiboIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @return
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        //現在の日付(MONTH)を取得
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);



        //メソッド化できないのか？


        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
        //Date thisfirst_date = sdf.format(thisMonthfirstDay));
        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }
        //月ごとにpageを

        try{
            month = Integer.parseInt(request.getParameter("month"));
        }catch(Exception e){
        }

        cal.set(year, month, 1);
        String first_str =  sdf.format(cal.getTime());
        Date first_date = Date.valueOf(first_str);
        request.setAttribute("first_date", first_date);
        int thisMonthfirstDay = cal.get(Calendar.MONTH);

        cal.set(year, month+1,0);
       String last_str = sdf.format(cal.getTime());
       Date last_date = Date.valueOf(last_str);

        request.setAttribute("last_date", last_date);




        List<kakeibo> KAKEIBO = em.createNamedQuery("getMyAllKakeibo",kakeibo.class)
                                            .setParameter("employee", login_employee)
                                            .setParameter("firstday", first_date)
                                            .setParameter("lastday",last_date )
                                            .getResultList();

         long kakeibo_count = (long)em.createNamedQuery("getKakeiboCount", Long.class)
                                                    .getSingleResult();



        int L_total = 0;
        int F_total = 0;
        int S_total = 0;
        int Income = 0;
        int E_income = 0;
        int O_income = 0;
        for(int i = 0; i< KAKEIBO.size(); i++){
            switch(KAKEIBO.get(i).getCategory()){
            case 0:
                L_total += KAKEIBO.get(i).getCost();
                break;
            case 1:
                F_total += KAKEIBO.get(i).getCost();
                break;
            case 2:
                S_total += KAKEIBO.get(i).getCost();
                break;
            case 3:
                Income += KAKEIBO.get(i).getCost();
                break;
            case 4:
                E_income += KAKEIBO.get(i).getCost();
                break;
            case 5:
                O_income += KAKEIBO.get(i).getCost();
                break;
            }
        }
        //DBをcostに変換する。
        em.close();
        request.setAttribute("L_total", L_total);
        request.setAttribute("F_total", F_total);
        request.setAttribute("S_total", S_total);
        request.setAttribute("Income", Income);
        request.setAttribute("E_income", E_income);
        request.setAttribute("O_income", O_income);
        request.setAttribute("KAKEIBO", KAKEIBO);
        request.setAttribute("kakeibo_count", kakeibo_count);
        request.setAttribute("page", page);

        //カレンダーでデータ取得

        request.setAttribute("month", month+1);
        //request.setAttribute("month_count",month_count);
        request.setAttribute("day", day);


        request.setAttribute("thisMonthfirstDay", thisMonthfirstDay );

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        //ファイルを作成
        //大カテゴリ 小カテゴリ メンターのURL参照f
        //グラフ　
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/kakeibo/index.jsp");
        rd.forward(request,response);
    }



}
