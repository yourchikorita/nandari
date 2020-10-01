package nandari.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nandari.command.CommandAction;
import nandari.command._01_ManagerMain;
import nandari.command._02_ManagerLogin;
import nandari.command._03_ManagerLoginPro;
import nandari.command._04_ManagerLogout;
import nandari.command._05_ItemList;
import nandari.command._06_ItemRegister;
import nandari.command._07_ItemRegisterPro;
import nandari.command._08_ItemUpdate;
import nandari.command._09_ItemUpdatePro;
import nandari.command._10_ItemDeletePro;
import nandari.command._11_shopMain;
import nandari.command._12_Login;
import nandari.command._13_LoginPro;
import nandari.command._14_Logout;
import nandari.command._15_Modify;
import nandari.command._16_ModifyForm;
import nandari.command._17_ModifyPro;
import nandari.command._18_Register;
import nandari.command._19_RegisterPro;
import nandari.command._20_Confirm;
import nandari.command._21_DeletePro;
import nandari.command._22_ShopList;
import nandari.command._23_ItemContent;
import nandari.command._34_InsertCart;
import nandari.command._35_CartList;
import nandari.command._36_CartUpdateForm;
import nandari.command._37_CartUpdatePro;
import nandari.command._38_DeleteCart;
import nandari.command._39_BuyList;
import nandari.command._40_BuyForm;
import nandari.command._41_BuyPro;
import nandari.command._42_OrderList;


@WebServlet(urlPatterns = {"*.do"})
public class Controller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();   
	
	public void init() throws ServletException {
		commandMap.put("/mg/managerMain.do", new _01_ManagerMain());
		commandMap.put("/mg/managerLogin.do", new _02_ManagerLogin());
		commandMap.put("/mg/managerLoginPro.do", new _03_ManagerLoginPro());
		commandMap.put("/mg/managerLogout.do", new _04_ManagerLogout());
		commandMap.put("/mg/itemList.do", new _05_ItemList());
		commandMap.put("/mg/itemRegister.do", new _06_ItemRegister());
		commandMap.put("/mg/itemRegisterPro.do", new _07_ItemRegisterPro());
		commandMap.put("/mg/itemUpdate.do", new _08_ItemUpdate());
		commandMap.put("/mg/itemUpdatePro.do", new _09_ItemUpdatePro());
		commandMap.put("/mg/itemDeletePro.do", new _10_ItemDeletePro());
		commandMap.put("/mg/orderList.do", new _42_OrderList());
		
		
		commandMap.put("/index.do",new _11_shopMain());//메인화면
		commandMap.put("/login.do", new _12_Login());
		commandMap.put("/loginPro.do", new _13_LoginPro());
		commandMap.put("/logout.do", new _14_Logout());
		commandMap.put("/modify.do", new _15_Modify());
		commandMap.put("/modifyForm.do", new _16_ModifyForm());
		commandMap.put("/modifyPro.do", new _17_ModifyPro());
		commandMap.put("/register.do", new _18_Register());
		commandMap.put("/registerPro.do", new _19_RegisterPro());
		commandMap.put("/confirmId.do", new _20_Confirm());
		commandMap.put("/deletePro.do", new _21_DeletePro());
		commandMap.put("/list.do", new _22_ShopList());
		commandMap.put("/itemContent.do", new _23_ItemContent());
		
		commandMap.put("/insertCart.do", new _34_InsertCart());
		commandMap.put("/cartList.do", new _35_CartList());
		commandMap.put("/cartUpdateForm.do", new _36_CartUpdateForm());
		commandMap.put("/cartUpdatePro.do", new _37_CartUpdatePro());
		commandMap.put("/deleteCart.do", new _38_DeleteCart());
		commandMap.put("/buyList.do", new _39_BuyList());
		commandMap.put("/buyForm.do", new _40_BuyForm());
		commandMap.put("/buyPro.do", new _41_BuyPro());
		
	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request,response);
	}
	protected void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandAction com = null;
		try {
			String command = request.getRequestURI();//플젝 전체 주소
			if(command.indexOf(request.getContextPath()) == 0) {
				command = command.substring(request.getContextPath().length());
				com = (CommandAction)commandMap.get(command);//벨류
				view = com.requestPro(request,response); //requestPro함수의 리턴값은 jsp
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
		request.setAttribute("cont",view);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/00_index.jsp");
		dispatcher.forward(request,response);
	}

}
