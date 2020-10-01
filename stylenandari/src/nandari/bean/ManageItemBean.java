package nandari.bean;
//managerBean과 같은것
public class ManageItemBean {
	private int item_id; //아이템의 등록번호
	private String item_kind; //아이템의 분류
	private String item_name; //아이템이름
	private int item_price; //아이템가격
	private int item_count; //아이템의 재고수량
	private String item_image; //아이템 이미지명
	private String item_summary; //아이템 의 요약
	private int discount_rate; //아이템의 할인율
	private String reg_date; //아이템의 등록날짜
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getItem_kind() {
		return item_kind;
	}
	public void setItem_kind(String item_kind) {
		this.item_kind = item_kind;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public int getItem_count() {
		return item_count;
	}
	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}
	public String getItem_image() {
		return item_image;
	}
	public void setItem_image(String item_image) {
		this.item_image = item_image;
	}
	public String getItem_summary() {
		return item_summary;
	}
	public void setItem_summary(String item_summary) {
		this.item_summary = item_summary;
	}
	public int getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(int discount_rate) {
		this.discount_rate = discount_rate;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	
	
}
