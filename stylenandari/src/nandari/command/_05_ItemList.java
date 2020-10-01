package nandari.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nandari.bean.ManageItemBean;
import nandari.bean.ManagerDAO;


public class _05_ItemList implements CommandAction{
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		List<ManageItemBean> itemList = null;
		String item_kind = request.getParameter("item_kind");
		int count = 0;
		
		//DB연동 - 전체 상품의 수를 얻어냄
		ManagerDAO itemProcess = ManagerDAO.getInstance();
        count = itemProcess.getItemCount(); 
        
        if (count > 0){//상품이 있으면 수행
        	//상품전체를 테이블에서 얻어내서 itemList에 저장
        	itemList = itemProcess.getItems(item_kind);
        	//itemList를 뷰에서 사용할 수 있도록 request속성에 저장
        	request.setAttribute("itemList", itemList);
        }
       
        //뷰에서 사용할 속성
        request.setAttribute("count", new Integer(count));
        request.setAttribute("item_kind", item_kind);
        request.setAttribute("type", new Integer(0));
       
		return "/05_itemList.jsp";
	}

}
