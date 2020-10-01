$(document).ready(function(){
	$("#insertCart").click(function(){//[장바구니에 담기]버튼 클릭
		var buyer = $("#buyer").val();
		var item_kind = $("#item_kind").val();
		var query = {item_id:$("#item_id").val(),
				     buy_count:$("#buy_count").val(),
				     item_image:$("#item_image").val(),
				     item_name:$("#item_name").val(),
				     buy_price:$("#buy_price").val(),
				     buyer:buyer};		
		$.ajax({
 		     type: "POST",
 		     url: "/stylenandari/insertCart.do",
 		     data: query,
 		     success: function(data){
	alert(data);
 		    	 alert("장바구니에 담겼습니다."); 
 		     }
 		});
	});
	
	$("#list").click(function(){//[목록으로]버튼 클릭
		window.location.href="/stylenandari/list.do?item_kind=all";
	});
	
	$("#shopMain").click(function(){//[메인으로]버튼 클릭
		window.location.href="/stylenandari/index.do";
	});
	
	$("#writeQna").click(function(){//[상품QnA쓰기]버튼 클릭
		var book_id = $("#book_id").val();
		var book_kind = $("#book_kind").val();
		
 		var query="/shoppingmall/qnaForm.do?item_id="+book_id;
 		query += "&book_kind="+book_kind;
 		window.location.href=query;
 		
	});
});

function edit(editBtn){//[수정]버튼 클릭
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "/stylennandari/qnaUpdateForm.do?qna_id="+arr[0];
	query += "&book_kind="+arr[1];
	window.location.href=query;
}

function del(delBtn){//[삭제]버튼 클릭
	var rStr = delBtn.name;
	var arr = rStr.split(",");
	
	var query = {qna_id: arr[0]};
    $.ajax({
       type: "POST",
       url: "/stylenandari/qnaDeletePro.do",
       data: query,
       success: function(data){
    	   var str1 = '<p id="ck">';
 		   var loc = data.indexOf(str1);
 		   var len = str1.length;
 		   var check = data.substr(loc+len,1);
 		   if(check == "1"){//
 		  	  alert("QnA가 삭제 되었습니다.");
 		 	  var query = "/stylenandari/itemContent.do?book_id="+arr[1];
 			  query += "&book_kind="+arr[2];
 			  window.location.href=query;
 	       }else//사용할 수 있는 아이디
 	  	      alert("QnA가 삭제 실패");
       }
    });
}