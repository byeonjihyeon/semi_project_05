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
 * Servlet Filter implementation class MemberFilter
 */
public class MemberFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public MemberFilter() {
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
			 
			 //url끝마다 CRUD구분하여 "N"일 경우 접근제어.
			  String url = req.getRequestURI();
				  if(url.endsWith("/details")) {
						 System.out.println();
						 //회원 1명에 대한 select 권한을 가지지 않는다면,
						 if(loginAdmin.get(1).getSelYN().equals("N")) {
								RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
								request.setAttribute("icon", "warning");
								request.setAttribute("msg", "해당 권한으로는 조회 불가합니다.");
								request.setAttribute("loc", "/admin/member/list?page=1");
								view.forward(request, response);
								//메소드 종료
								return;
						 }
					 };
			 
				 //회원에 대한 delete 권한을 가지지 않는다면,
				 if(url.endsWith("/delete")) {
					 System.out.println();
					 if(loginAdmin.get(1).getDelYN().equals("N")) {
							RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
							request.setAttribute("icon", "warning");
							request.setAttribute("msg", "해당 권한으로는 삭제 불가합니다.");
							request.setAttribute("loc", "/admin/member/list?page=1");
							view.forward(request, response);
							//메소드 종료
							return;
					 }
				 };
				 
				 //회원에 대한 update 권한을 가지지 않는다면,
				 if(url.endsWith("/updateFrm")) {
					 System.out.println();
					 if(loginAdmin.get(1).getUpdYN().equals("N")) {
							RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
							request.setAttribute("icon", "warning");
							request.setAttribute("msg", "해당 권한으로는 수정 불가합니다.");
							request.setAttribute("callback", "window.close()");
							view.forward(request, response);
							//메소드 종료
							return;
					 }
				 };
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
