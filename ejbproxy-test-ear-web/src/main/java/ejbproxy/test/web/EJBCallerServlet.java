package ejbproxy.test.web;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EJBCallerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Object service;

	private Object customService;

	@Override
	public void init() throws ServletException {
		try {
			InitialContext ctx = new InitialContext();
			service = ctx
					.lookup("ejbproxy-test-ear-0.0.1/BasicServiceImpl/remote");
			customService = ctx.lookup("BasicServiceImpl/Custom");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\"><HTML><HEAD><TITLE>EJBCaller</TITLE></HEAD><BODY>");

		out.write("<FORM action=\"/ejbproxy-test-ear-web/ejbcaller\" method=\"post\">");
		out.write("<INPUT type=\"submit\" name=\"doComputation\" value=\"Call doComputation!\">");
		out.write("<INPUT type=\"submit\" name=\"returnResult\" value=\"Call returnResult!\">");
		out.write("<INPUT type=\"submit\" name=\"throwException\" value=\"Call throwException!\">");
		out.write("<INPUT type=\"submit\" name=\"custom_doComputation\" value=\"Call custom_doComputation!\">");
		out.write("<INPUT type=\"submit\" name=\"custom_returnResult\" value=\"Call custom_returnResult!\">");
		out.write("<INPUT type=\"submit\" name=\"custom_throwException\" value=\"Call custom_throwException!\">");
		out.write("</FORM>");

		out.write("</BODY></HTML>");
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getParameter("doComputation") != null) {
			try {
				service.getClass().getMethod("doComputation", String.class)
						.invoke(service, "some-comp-id");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (req.getParameter("custom_doComputation") != null) {
			try {
				customService.getClass()
						.getMethod("doComputation", String.class)
						.invoke(customService, "some-comp-id");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (req.getParameter("returnResult") != null) {
			try {
				service.getClass().getMethod("returnResult").invoke(service);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (req.getParameter("custom_returnResult") != null) {
			try {
				customService.getClass().getMethod("returnResult")
						.invoke(customService);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (req.getParameter("throwException") != null) {
			try {
				service.getClass().getMethod("throwException", String.class).invoke(service, "nothing");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (req.getParameter("custom_throwException") != null) {
			try {
				customService.getClass().getMethod("throwException", String.class)
				.invoke(customService, "nothing");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		doGet(req, resp);
	}

}
