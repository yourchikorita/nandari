package nandari.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nandari.bean.ManagerDAO;


public class _10_ItemDeletePro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int item_id = Integer.parseInt(request.getParameter("item_id"));
		String item_kind = request.getParameter("item_kind");
		
		//DB연동 - book_id에 해당하는 상품을 삭제
		ManagerDAO bookProcess = ManagerDAO.getInstance();
		bookProcess.deleteItem(item_id); 
		
		request.setAttribute("item_kind",item_kind);
		return "/10_itemDeletePro.jsp";
	}
}