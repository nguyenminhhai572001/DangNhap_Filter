package com.dangnhap.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dangnhap.Model.UsersModel;
import com.dangnhap.common.Constant;
import com.dangnhap.pojo.Users;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsersModel usersModel = new UsersModel();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password"); 
		Users users = usersModel.getUsers(email, password);
		
		if(users != null) {
			HttpSession session = req.getSession();
			session.setAttribute(Constant.SESSION_USER, users);
			session.setMaxInactiveInterval(1800);
			resp.sendRedirect(req.getContextPath() + "/admin");
		}
		else {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		}
		
	}
}
