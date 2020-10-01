package nandari.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nandari.bean.ManageItemBean;
import nandari.bean.ManagerDAO;


public class _08_ItemUpdate implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		int item_id = Integer.parseInt(request.getParameter("item_id"));
		String item_kind = request.getParameter("item_kind");
		
		//DB연동 book_id에 해당하는 상품을 얻내서 book에 저장
		ManagerDAO itemProcess = ManagerDAO.getInstance();
		ManageItemBean item =  itemProcess.getItem(item_id);
		
		request.setAttribute("item_id", item_id);
		request.setAttribute("item_kind", item_kind);
        request.setAttribute("item", item);
		request.setAttribute("type", new Integer(0));
		return "/08_itemUpdate.jsp";
	}
}