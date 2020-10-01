package nandari.command;

import java.sql.Timestamp;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import nandari.bean.ManageItemBean;
import nandari.bean.ManagerDAO;



public class _09_ItemUpdatePro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");//한글 인코딩
		
		String filename ="";
		String realFolder = "";//웹 어플리케이션상의 절대 경로 저장
		String saveFolder = "/images"; //파일 업로드 폴더 지정
		String encType = "utf-8"; //인코딩타입
		int maxSize = 1*1024*1024;  //최대 업로될 파일크기 1Mb
		
		MultipartRequest imageUp = null;

		//웹 어플리케이션상의 절대 경로를 구함
		ServletContext context = request.getSession().getServletContext();
		realFolder = context.getRealPath(saveFolder);  
       
		try{
			//파일 업로드를 수행하는 MultipartRequest 객체 생성 
			imageUp = new MultipartRequest(request,realFolder,maxSize,
					            encType,new DefaultFileRenamePolicy());
			   
			//<input type="file">인 모든 파라미터를 얻어냄
			Enumeration<?> files = imageUp.getFileNames();
			  
		     while(files.hasMoreElements()){
		       String name = (String)files.nextElement();
		       filename = imageUp.getFilesystemName(name);
		     }
		}catch(Exception e){
		     e.printStackTrace();
		}
		
		ManageItemBean item = new ManageItemBean();
		int item_id= Integer.parseInt( imageUp.getParameter("item_id"));
		String item_kind = imageUp.getParameter("item_kind");
		String item_name = imageUp.getParameter("item_name");
		String item_price = imageUp.getParameter("item_price");
		String item_count = imageUp.getParameter("item_count");
		String item_summary = imageUp.getParameter("item_summary");
		String discount_rate = imageUp.getParameter("discount_rate");

			
		item.setItem_name(item_name);
		item.setItem_price(Integer.parseInt(item_price));
		item.setItem_count(Short.parseShort(item_count));
		item.setItem_image(filename);
		item.setItem_summary(item_summary);
		item.setDiscount_rate(Byte.parseByte(discount_rate));
		item.setReg_date(new Timestamp(System.currentTimeMillis()).toString());

		//DB연동해서 상품 수정 처리
		ManagerDAO itemProcess = ManagerDAO.getInstance();
		itemProcess.updateItem(item, item_id);
		
		request.setAttribute("item_kind", item_kind);
		return "/09_itemUpdatePro.jsp";
	}
}