package project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dao.AddPhoneNumberDao;
import project.dao.AddPhoneNumberDaoOracl;
import project.dao.AddPhoneNumberVo;



@WebServlet(urlPatterns="/pns")
public class AddPhoneNumberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("a");
		if("form".equals(action)) {
		RequestDispatcher rd = 
				getServletContext().getRequestDispatcher("/WEB-INF/view/PNSForm.jsp");
		rd.forward(req, resp);
		}else if("delete".equals(action)) {
			Long id = Long.valueOf(req.getParameter("no"));
			
			AddPhoneNumberDao dao = new AddPhoneNumberDaoOracl();
			dao.delete(id);
			resp.sendRedirect(req.getContextPath()+"/pns");
		}else if("search".equals(action)) {
			String keyword= req.getParameter("keyword");
			AddPhoneNumberDao dao = new AddPhoneNumberDaoOracl();
			List<AddPhoneNumberVo> list = dao.search(keyword);
			dao.search(keyword);
			req.setAttribute("list", list);
			
			
			RequestDispatcher rd= getServletContext().getRequestDispatcher("/WEB-INF/view/StratIndex.jsp");
			rd.forward(req, resp);
			
		}
		else {
		AddPhoneNumberDao dao = new AddPhoneNumberDaoOracl();
		List<AddPhoneNumberVo> list = dao.getList();
		
		req.setAttribute("list", list);
		
		
		RequestDispatcher rd= getServletContext().getRequestDispatcher("/WEB-INF/view/StratIndex.jsp");
		rd.forward(req, resp);
	}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action=req.getParameter("action");
		if ("insert".equals(action)) {
			String name =req.getParameter("name");
			String hp =req.getParameter("hp");
			String tel =req.getParameter("tel");
			
			AddPhoneNumberVo vo = new AddPhoneNumberVo();
			vo.setName(name);
			vo.setHp(hp);
			vo.setTel(tel);
		
			AddPhoneNumberDao dao = new AddPhoneNumberDaoOracl();
			dao.insert(vo);
			resp.sendRedirect(req.getContextPath() + "/pns");
		} else {
			doGet(req, resp);
		}
		
	}

}
