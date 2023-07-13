package com.sh.hairball.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.member.model.vo.MemberRole;


/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/admin/*") // admin페이지 전체
public class AdminFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AdminFilter() {
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
		
		HttpServletRequest httpReq = (HttpServletRequest) request; // 다운캐스팅 getAttribute getSession 등 이런메소드는 부모타입에는 없는 메소드라서 다운캐스팅 함
		HttpServletResponse httpRes = (HttpServletResponse) response; // 다운캐스팅
		
		HttpSession session = httpReq.getSession(); // getSession 하려면 다운캐스팅 후에 사용
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		
		if(loginMember == null || loginMember.getMemberRole() != MemberRole.A) {
			session.setAttribute("msg", "관리자만 이용하실 수 있습니다."); // msg는 header.jsp에서 미리 선언해둠
			httpRes.sendRedirect(httpReq.getContextPath() + "/"); // 홈페이지로 리다이렉트
			return;
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
