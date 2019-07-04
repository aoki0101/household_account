package controllers.kakeibo;

import java.io.IOException;

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
 * Servlet implementation class KakeiboEditServlet
 */
@WebServlet("/kakeibo/edit")
public class KakeiboEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManager em = DBUtil.createEntityManager();
		
		kakeibo k = em.find(kakeibo.class, Integer.parseInt(request.getParameter("id")));
		Integer cate = k.getCategory();
		em.close();
		
		Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");
		if(login_employee.getId() == k.getEmployee().getId()){
		    request.setAttribute("kakeibo", k);
		    request.setAttribute("cate", cate);
		    request.setAttribute("_token", request.getSession().getId());
		    request.getSession().setAttribute("kakeibo_id", k.getId());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/kakeibo/edit.jsp");
		rd.forward(request, response);
		
	}

}
