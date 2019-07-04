package controllers.kakeibo;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.kakeibo;

/**
 * Servlet implementation class KakeiboNew2Servlet
 */
@WebServlet("/kakeibo/new2")
public class KakeiboNew2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboNew2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("_token",request.getSession().getId() );
		  kakeibo k = new kakeibo();
	      k.setKakeibo_date(new Date(System.currentTimeMillis()));
	      request.setAttribute("kakeibo", k);

	      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/kakeibo/new2.jsp");
	      rd.forward(request, response);
		
	}

	
}
