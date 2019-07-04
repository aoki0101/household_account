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
 * Servlet implementation class KakeiboNewServlet
 */
@WebServlet("/kakeibo/new")
public class KakeiboNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakeiboNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    request.setAttribute("_token", request.getSession().getId());
	    
	    
	    //本日の日時の取得
	    kakeibo k = new kakeibo();
	    k.setKakeibo_date(new Date(System.currentTimeMillis()));
	    request.setAttribute("kakeibo", k);
	    
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/kakeibo/new.jsp");
	    //RequestDispatcher rd2  =request.getRequestDispatcher("/WEB-INF/views/kakeibo/new2.jsp");
	    //rd2.forward(request, response);
	    rd.forward(request, response);
	}

}
