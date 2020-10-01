package nandari.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nandari.bean.ManageItemBean;
import nandari.bean.ManagerDAO;
import nandari.bean.QnaBean;


public class _23_ItemContent implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		
//		List<QnaBean> qnaLists;
		String item_kind = request.getParameter("item_kind");
		int item_id = Integer.parseInt(request.getParameter("item_id"));
		
		//item_id에 해당하는 상품을 얻어냄
		ManagerDAO itemProcess = ManagerDAO.getInstance();
		ManageItemBean item = itemProcess.getItem(item_id);
		
		//book_id에 해당하는 상품의 QnA 수를 얻어냄
//		QnaDAO qnaProcess = QnaDAO.getInstance();
//		int count = qnaProcess.getArticleCount(book_id);
//	
//		if (count > 0){//QnA가 있으면 수행
//			///book_id에 해당하는 상품의 QnA를 얻어냄 
//			qnaLists = qnaProcess.getArticles(count, book_id);
//        	request.setAttribute("qnaLists", qnaLists);
//        }

		request.setAttribute("item", item);
		request.setAttribute("item_id", new Integer(item_id));
		request.setAttribute("item_kind", item_kind);
		//request.setAttribute("count", new Integer(count));
		request.setAttribute("type", new Integer(1));
		return "/23_itemContent.jsp";
	}
}