package kr.or.iei.common.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.iei.admin.model.vo.Admin;

/**
 * Servlet Filter implementation class GymFilter
 */
public class GymFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public GymFilter() {
        super();
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
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		
		if(session != null) {
			Object adminObj = session.getAttribute("loginAdmin");
		  
		  if(adminObj instanceof ArrayList<?>) {
			  ArrayList<Admin> loginAdmin = (ArrayList<Admin>)adminObj;
			  
			  //헬스장 관리에 대한 조회권한을 가지지 않는다면,
			  if(loginAdmin.get(2).getSelYN().equals("N")) {
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
					request.setAttribute("icon", "warning");
					request.setAttribute("msg", "현재 권한으로는 접근 불가합니다.");
					request.setAttribute("loc", "/");
					view.forward(request, response);
					//메소드 종료
					return;
			  }
			  
			  
			 
			  
		  }
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
