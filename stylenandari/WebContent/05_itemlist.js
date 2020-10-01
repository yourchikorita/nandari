$(document).ready(function(){
	
	$("#regist").click(function(){//[책등록]버튼 클릭
		window.location.href="itemRegister.do";
	});
	
	$("#bookMain").click(function(){//[관리자 메인으로]버튼 클릭
		window.location.href="managerMain.do";
	});
});

//[수정]버튼을 클릭하면 자동실행
function edit(editBtn){
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "itemUpdate.do?item_id="+arr[0];
	query += "&item_kind="+arr[1];
	window.location.href=query;
}

//[삭제]버튼을 클릭하면 자동실행
function del(delBtn){
	alert("this=",delBtn);
	var rStr = delBtn.name;
	var arr = rStr.split(",");
	var query = "itemDeletePro.do?item_id="+arr[0];
	query += "&item_kind="+arr[1];
	window.location.href=query;
}