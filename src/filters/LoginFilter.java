package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Employee;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
	    String context_path = ((HttpServletRequest)request).getContextPath();
	    String servlet_path = ((HttpServletRequest)request).getServletPath();
	    if(!servlet_path.matches("/css.*" )) {
	        
	        HttpSession session = ((HttpServletRequest)request).getSession();
	        
	        //セッションスコープに保存されたログインユーザ情報を取得
	        Employee e = (Employee)session.getAttribute("login_employee");
	        
	        
	        
	            if(!servlet_path.equals("/login")) {
	        //ログアウトしている状態であればログインに
	                if(e == null && !servlet_path.matches("/employees/new") && !servlet_path.matches("/employees/create")) {
	                    
	                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");
	                    return;
	                }
	                    
	                }else{
	                    //ログインしているのにログイン画面を表示しようとする場合
	                    //トップページに行く
	                    if(e != null) {
	                        ((HttpServletResponse)response).sendRedirect(context_path + "/");
	                        return;
	                    }
	                }
	        
	    }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
