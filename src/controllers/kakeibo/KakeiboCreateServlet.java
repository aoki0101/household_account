package controllers.kakeibo;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
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
import models.validators.KakeiboValidator;
import utils.DBUtil;

/**
 * Servlet implementation class KakeiboCreateServlet
 */
@WebServlet("/kakeibo/create")
public class KakeiboCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

                kakeibo k = new kakeibo();
                k.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

                Date kakeibo_date = new Date(System.currentTimeMillis());
                String kd_str = request.getParameter("kakeibo_date");
                if(kd_str != null && !kd_str.equals("")) {
                    kakeibo_date = Date.valueOf(request.getParameter("kakeibo_date"));
                }
                k.setKakeibo_date(kakeibo_date);
                k.setContent(request.getParameter("content"));
                k.setProduct(request.getParameter("product"));
                k.setCategory(Integer.parseInt(request.getParameter("category")));
                int cost = Integer.parseInt(request.getParameter("cost"));
                k.setCost(cost);



       if(request.getParameter("product") ==null || request.getParameter("product").equals("")) {
           k.setProduct("");
       }

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            k.setCreated_at(currentTime);

            List<String> errors = KakeiboValidator.validate(k);
        if(errors.size() > 0) {
            em.close();

            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("kakeibo", k);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/kakeibo/new.jsp");
            //RequestDispatcher rd2 = request.getRequestDispatcher("/WEB-INF/views/kakeibo/new2.jsp");

            //rd2.forward(request, response);
            rd.forward(request, response);
        }else{
            em.getTransaction().begin();
            em.persist(k);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "作成が完了しました。");

            response.sendRedirect(request.getContextPath() + "/kakeibo/index");
        }
        }

    }



}
