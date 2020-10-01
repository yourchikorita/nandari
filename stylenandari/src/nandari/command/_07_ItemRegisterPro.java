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


public class _07_ItemRegisterPro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//한글 인코딩
		System.out.println("여기까지 들어옴");
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
			  
			 //파일 정보가 있다면
		     while(files.hasMoreElements()){
		       //input 태그의 속성이 file인 태그의 name 속성값 :파라미터이름
		       String name = (String)files.nextElement();
		   
		       //서버에 저장된 파일 이름
		       filename = imageUp.getFilesystemName(name);
		     }
		  }catch(Exception e){
		     e.printStackTrace();
		  }
		
		//폼으로부터 넘어온 정보중 파일이 아닌 정보를 얻어냄
		ManageItemBean item = new ManageItemBean();
		String item_kind = imageUp.getParameter("item_kind");
		String item_name = imageUp.getParameter("item_name");
		String item_price = imageUp.getParameter("item_price");
		String item_count = imageUp.getParameter("item_count");
		String item_summary = imageUp.getParameter("item_summary");
		String discount_rate = imageUp.getParameter("discount_rate");

		item.setItem_kind(item_kind);
		item.setItem_name(item_name);
		item.setItem_price(Integer.parseInt(item_price));
		item.setItem_count(Short.parseShort(item_count));
	
		item.setItem_image(filename);
		item.setItem_summary(item_summary);
		//book.setDiscount_rate(Byte.parseByte(discount_rate));
		item.setDiscount_rate(Integer.parseInt(discount_rate));
		item.setReg_date(new Timestamp(System.currentTimeMillis()).toString());

		//DB연동 - 넘어온 정보를 테이블의 레코드로 추가
		ManagerDAO itemProcess = ManagerDAO.getInstance();
		itemProcess.insertItem(item);
		
		request.setAttribute("item_kind", item_kind);
		return "/07_itemRegisterPro.jsp";
	}
}
