package nandari.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nandari.bean.ManageItemBean;
import nandari.bean.ManagerDAO;

public class _11_shopMain implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		ManageItemBean itemList[] = null;
		List<ManageItemBean[]> itemLists = new ArrayList<ManageItemBean[]>();
		
		ManagerDAO itemProcess = ManagerDAO.getInstance();
		
		
		//카테고리별 최신의 상품 각각 항목별로 3개씩 얻어내서 List에 저장
		for(int i=1; i<=3;i++){
			itemList = itemProcess.getItems(i+"00",3);
			itemLists.add(itemList);
		}
		

		
		//해당 페이지로 보낼 내용 설정
        request.setAttribute("itemLists", itemLists);
       
      
		//회원이 로그인하는 페이지
		request.setAttribute("type", new Integer(1));
		return  "/11_shopMain.jsp";
	}

}
