$(document).ready(function(){
	
	$("#shopMain").click(function(){//[메인으로]버튼 클릭
		window.location.href="/stylenandari/index.do";
	});
});

function editSu(editBtn){//[수정]버튼 클릭
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "/stylenandari/cartUpdateForm.do?cart_id="+arr[0];
	query += "&buy_count="+arr[1];
	window.location.href=query;
}

function delList(delBtn){//[삭제]버튼 클릭
	var rStr = delBtn.name;
	var query = "/stylenandari/deleteCart.do?list="+rStr;
    window.location.href=query;
}