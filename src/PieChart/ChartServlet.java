package PieChart;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * Servlet implementation class ChartServlet
 */
@WebServlet("/kakeibo/Chart")
public class ChartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em=DBUtil.createEntityManager();
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        //現在の日付(MONTH)を取得
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        //int day = cal.get(Calendar.DATE);



        //ここまでメソッド化
        try{
            month = Integer.parseInt(request.getParameter("month"));
        }catch(Exception e){
        }
      //メソッド化できないのか？
        cal.set(year, month, 1);//月初め
        String first_str =  sdf.format(cal.getTime());
        Date first_date = Date.valueOf(first_str);

        request.setAttribute(first_str, first_str);
        request.setAttribute("first_date", first_date);
        //int thisMonthfirstDay = cal.get(Calendar.MONTH);

        cal.set(year, month+1,0);//月終わり
       String last_str = sdf.format(cal.getTime());
       Date last_date = Date.valueOf(last_str);

       int Ld = cal.get(Calendar.DATE); //月の終わりの日にちを取得
       request.setAttribute("Ld", Ld);
       request.setAttribute("last_date", last_date);






        List<kakeibo> KAKEIBO = em.createNamedQuery("getMyAllKakeibo",kakeibo.class)
                .setParameter("employee", login_employee)
                .setParameter("firstday", first_date)
                .setParameter("lastday",last_date )
                .getResultList();//関数化



        List<Integer>I_total = new ArrayList<>();
        List<Integer>T_total = new ArrayList<>();
        int todayCost = 0;
        int todayIncome = 0;
        for(int i =1;i < Ld;i++ ){
            cal.set(year, month,i);
            String day_str = sdf.format(cal.getTime());
            Date DAY = Date.valueOf(day_str);

             List<kakeibo> day_list = em.createNamedQuery("getTodayCost", kakeibo.class)
                     .setParameter("employee", login_employee)
                     .setParameter("today", DAY)
                     .getResultList();
             for(int t=0;t < day_list.size(); t++){
             switch(day_list.get(t).getCategory()){
                 case 0:
                 case 1:
                 case 2:
                     todayCost += day_list.get(t).getCost();
                     break;
                 case 3:
                 case 4:
                 case 5:
                     todayIncome += day_list.get(t).getCost();
                     break;
                 }
             }
             if(){

             }
            System.out.println(DAY);

            request.setAttribute("DAY", i);
            request.setAttribute("day_list", day_list);
        }
        T_total.add(todayCost);
        I_total.add(todayIncome);

        request.setAttribute("T_total", T_total);
        request.setAttribute("I_total",I_total);
        System.out.println(todayCost);
        System.out.println(todayIncome);


        request.setAttribute("todayCost", todayCost);





        //
        int L_total = 0;
        int F_total = 0;
        int S_total = 0;
        int Income = 0;
        int E_income = 0;
        int O_income = 0;
        int today_cost = 0;
        List<Integer> cost_list = new ArrayList<Integer>();
        List<Integer> today_list = new ArrayList<Integer>();

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
            cost_list.add(KAKEIBO.get(i).getCost());
        }


        //DBをcostに変換する。
                em.close();
                request.setAttribute("today_cost", today_cost);
                request.setAttribute("cost_list",cost_list);
                request.setAttribute("L_total", L_total);
                request.setAttribute("F_total", F_total);
                request.setAttribute("S_total", S_total);
                request.setAttribute("Income", Income);
                request.setAttribute("E_income", E_income);
                request.setAttribute("O_income", O_income);


                long All_total = L_total + F_total + S_total;
                long All_income = Income + E_income +O_income;
                long Dif_cost = All_income - All_total;



                request.setAttribute("today_list", today_list);
                request.setAttribute("Today", 1);
                request.setAttribute("month", month+1);
                request.setAttribute("KAKEIBO", KAKEIBO);
                request.setAttribute("All_total",All_total );  //メソッド化できる
                request.setAttribute("All_income", All_income);//メソッド化できる
                request.setAttribute("Dif_cost", Dif_cost);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/kakeibo/graph.jsp");
                rd.forward(request, response);
    }

}
