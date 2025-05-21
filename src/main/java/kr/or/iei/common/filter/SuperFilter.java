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
 * Servlet Filter implementation class PreviligeFilter
 */
public class SuperFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public SuperFilter() {
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
			//로그한 관리자에 대한 정보 추출
			Object adminObj = session.getAttribute("loginAdmin");
			
			//추출한 Object객체를 로그인 관리자 객체로 형변환
			if (adminObj instanceof ArrayList<?>) {
				ArrayList<Admin> loginAdmin = (ArrayList<Admin>) adminObj;
				//슈퍼 관리자에 대한 조회권한을 가지지 않는다면 접근권한 불가 메시지 뜨고, 회원관리페이지로 이동
				if(loginAdmin.get(0).getSelYN().equals("N")) {
					RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
					request.setAttribute("icon", "warning");
					request.setAttribute("msg", "접근 권한이 없습니다.");
					request.setAttribute("loc", "/admin/member/list?page=1");
					view.forward(request, response);
					//메소드 종료
					return;
				}
			}
			
		}else {
			System.out.println("필터 오류");
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
