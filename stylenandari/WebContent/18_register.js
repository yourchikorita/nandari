$(document).ready(function(){
	$("#checkId").click(function(){//[ID중복확인]버튼 클릭
	  if($("#id").val()){
		var query = {id:$("#id").val()};
		
	    $.ajax({
	    	type:"post",
	    	url:"/stylenandari/confirmId.do",
	    	data:query,
	    	success:function(data){
	    		var str1 = '<p id="ck">';
	    		var loc = data.indexOf(str1);
	    		var len = str1.length;
	    		var check = data.substr(loc+len,1);//전체 html에서 check만 가지고온다.
	    		if(check == "1"){//사용할 수 없는 아이디, 디비에 같은 아이디가 있다.
	    			alert("이미 등록된 아이디 입니다...");
	    	    	$("#id").val("");
	    	     }else//사용할 수 있는 아이디
	    	  	    alert("사용할 수 있는 아이디!");
	 	    }
	    });
	  }else{//아이디를 입력하지 않고 [ID중복확인]버튼을 클릭한 경우
		  alert("사용할 아이디를 입력");
		  $("#id").focus();
	  }
	});
	
	$("#process").click(function(){//[가입하기]버튼 클릭
		  var query = {id:$("#id").val(), 
				  passwd:$("#passwd").val(),
			      name:$("#name").val(),
			      address:$("#address").val(),
			      tel:$("#tel").val()};
		  
		  $.ajax({
		      type:"post",
		      url:"registerPro.do",
		      data:query,
		      success:function(data){
		    	  window.location.href="index.do";
		 	  }
		  });
	});
	
	$("#cancle").click(function(){//[취소]버튼 클릭
		window.location.href="index.do";
	});

 });
