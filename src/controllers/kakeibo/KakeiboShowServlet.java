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
 * Servlet implementation class KakeiboShowServlet
 */
@WebServlet("/kakeibo/show")
public class KakeiboShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
        String queryString = request.getQueryString();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);


        //for でループで作成できないのか？


        cal.set(year, month, 1);
        String first_str =  sdf.format(cal.getTime());
        Date first_date = Date.valueOf(first_str);
        request.setAttribute(first_str, first_str);
        request.setAttribute("first_date", first_date);


        cal.set(year, month+1,0);
       String last_str = sdf.format(cal.getTime());
       Date last_date = Date.valueOf(last_str);

        request.setAttribute("last_date", last_date);


        List<kakeibo>KAKEIBO = em.createNamedQuery("getMyAllKakeibo",kakeibo.class)
                .setParameter("employee", login_employee)
                .setParameter("firstday", first_date)
                .setParameter("lastday",last_date )
                .getResultList();


        em.close();


        request.setAttribute("KAKEIBO", KAKEIBO);
        request.setAttribute("queryString", queryString);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/kakeibo/show.jsp");
        rd.forward(request, response);
    }



}
