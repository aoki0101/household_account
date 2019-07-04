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

import models.kakeibo;
import models.validators.KakeiboValidator;
import utils.DBUtil;

/**
 * Servlet implementation class KakeiboUpdateServlet
 */
@WebServlet("/kakeibo/update")
public class KakeiboUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            kakeibo k = em.find(kakeibo.class, (Integer)(request.getSession().getAttribute("kakeibo_id")));
            String p_name = request.getParameter("product");
            Integer cate= k.getCategory();
            k.setKakeibo_date(Date.valueOf(request.getParameter("kakeibo_date")));
            k.setProduct(request.getParameter("product"));
            k.setContent(request.getParameter("content"));
            k.setCost(Integer.parseInt(request.getParameter("cost")));
            k.setCreated_at(new Timestamp(System.currentTimeMillis()));
            if(p_name == null || p_name.equals("") && cate >= 3){
                k.setProduct("");
            }

            List<String> errors = KakeiboValidator.validate(k);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("kakeibo", k);
                request.setAttribute("errors", errors);
                request.setAttribute("cate", cate);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/kakeibo/edit.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("kakeibo_id");

                response.sendRedirect(request.getContextPath() + "/kakeibo/index");
            }

        }
    }

}
