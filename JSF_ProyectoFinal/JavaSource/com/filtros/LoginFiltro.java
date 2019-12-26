package com.filtros;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
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

import com.beans.LoginBean;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class LoginFiltro implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String url = req.getContextPath() + "/login/login.xhtml";

		boolean logueado = session != null && session.getAttribute("usuario") != null;
		boolean loginRequest = req.getRequestURI().equals(url);
		boolean resourceRequest = req.getRequestURI()
				.startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

		if (logueado || loginRequest || resourceRequest) {
			chain.doFilter(req, resp);
			
		} else if (url.indexOf("logout.xhtml") >= 0) {
			
			session.removeAttribute("usuario");
			session.invalidate();
			resp.sendRedirect(url);
		} else {			
			resp.sendRedirect(url);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
