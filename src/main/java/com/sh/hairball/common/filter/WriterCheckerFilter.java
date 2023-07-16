package com.sh.hairball.common.filter;

import java.io.IOException;
import java.util.List;

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
import com.sh.hairball.qnaboard.model.QuestionVo;
import com.sh.hairball.qnaboard.service.QuestionService;

/**
 * Servlet Filter implementation class WriterCheckerFilter
 */
@WebFilter("/qnaBoard/questionDetail")
public class WriterCheckerFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public WriterCheckerFilter() {
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
		
		HttpServletRequest httpReq = (HttpServletRequest) request; 
		HttpServletResponse httpRes = (HttpServletResponse) response; 
		
		HttpSession session = httpReq.getSession(); 
		QuestionService questionService = new QuestionService();
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		
		int id = Integer.parseInt(request.getParameter("id"));
		QuestionVo question = questionService.findById(id);
		request.setAttribute("question", question);
		System.out.println("question = " + question.toString());
		if(!(loginMember != null && ((question.getMemberId().equals(loginMember.getMemberId())) ||
				loginMember.getMemberRole() == MemberRole.A ))) {
			
			session.setAttribute("msg", "게시물 작성자만 조회할 수 있습니다.");
			httpRes.sendRedirect(httpReq.getContextPath() + "/qnaBoard/questionList");
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
