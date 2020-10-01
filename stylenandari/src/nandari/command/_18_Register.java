package nandari.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class _18_Register implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setAttribute("type", new Integer(1));//1이면 멤버로그인
		return "/18_register.jsp";
	}
	
}
