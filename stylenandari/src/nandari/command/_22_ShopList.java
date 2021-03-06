package nandari.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nandari.bean.ManageItemBean;
import nandari.bean.ManagerDAO;



public class _22_ShopList implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		
		List<ManageItemBean> itemList = null;
		int count = 0;
		String item_kind = request.getParameter("item_kind");
		ManagerDAO itemProcess = ManagerDAO.getInstance();
		
		//kind값이 all이면 전체 상품의 수를 얻어냄
		if(item_kind.equals("all"))
            count = itemProcess.getItemCount(); 
		else//all이 아니면 해당 카테고리의 상품수를 얻어냄
			count = itemProcess.getItemCount(item_kind);
        if (count > 0){//상품이 있으면 수행
        	//상품목록을 얻어냄
        	itemList = itemProcess.getItems(item_kind);
        	request.setAttribute("itemList", itemList);
        }
        
        //해당 뷰에서 사용할 속성
        request.setAttribute("count", new Integer(count));
        request.setAttribute("item_kind", item_kind);
        request.setAttribute("type", new Integer(1));
		return "/22_shopList.jsp";
	}

}
