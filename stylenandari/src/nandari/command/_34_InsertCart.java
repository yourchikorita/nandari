package nandari.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nandari.bean.CartBean;
import nandari.bean.CartDAO;

public class _34_InsertCart implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		//장바구니에 추가할 정보를 파라미터에서 받아냄
		byte buy_count = Byte.parseByte(request.getParameter("buy_count"));
		int item_id = Integer.parseInt(request.getParameter("item_id"));
		String item_name = request.getParameter("item_name");
		String item_image = request.getParameter("item_image");
		int buy_price = (int)Float.parseFloat(request.getParameter("buy_price"));
		String buyer = request.getParameter("buyer");
	

		//장바구니에 추가하기 위한 정보구성
		CartBean cart = new CartBean();
		cart.setItem_id(item_id);
		cart.setItem_image(item_image);
		cart.setItem_name(item_name);
		cart.setBuy_count(buy_count);
		cart.setBuy_price(buy_price);
		cart.setBuyer(buyer);
		
		//장바구니에 추가
		CartDAO bookProcess = CartDAO.getInstance();
		bookProcess.insertCart(cart);
		return  "/34_insertCart.jsp";
	}

}
