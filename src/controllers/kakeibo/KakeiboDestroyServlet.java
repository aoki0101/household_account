package controllers.kakeibo;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.kakeibo;
import utils.DBUtil;

/**
 * Servlet implementation class KakeiboDestroyServlet
 */
@WebServlet("/kakeibo/destroy")
public class KakeiboDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboDestroyServlet() {
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
		    
		    kakeibo k = em.find(kakeibo.class, (Integer)(request.getSession().getAttribute("kakeibo_id")));
		    
		    em.getTransaction().begin();
		    em.remove(k);
		    em.getTransaction().commit();
		    em.close();
		    
		    request.getSession().removeAttribute("kakeibo_id");
		    
		    response.sendRedirect(request.getContextPath() + "/kakeibo/index");
		}
	}

}
