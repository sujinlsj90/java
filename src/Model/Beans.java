/*
 * 작성일 : 2022.07.15.
 * 작성자 : 이수진
 * 설명 : 데이터 전송, 출력
 *  
 */

package Model;

import java.util.Map;
import controller.Admin;
import controller.AdminImpl;
import controller.Customer;
import controller.CustomerImpl;

//DTO (Data Transfer Object) : 데이터 전송 객체
public class Beans{
	
	//멤버변수
	private int goodsNo; //제품번호
	private String name;	//상품명
	private int weight;	//무게
	private int packDate;	//로스팅날짜
	private int price; //금액
	private int quantity; //수량

	private int balance = 0; //잔고(결산)
	
	//생성자
	private Beans() {}
	
	//싱글톤 생성
	private static Beans beansinfo = new Beans();
	
	public static Beans getInstance() {
		if(beansinfo == null) {
			beansinfo = new Beans();
		}
		return beansinfo;
	}
	
	public Beans(int goodsNo, String name, int weight, int packDate, int price, int quantity) {
		this.goodsNo = goodsNo;
		this.name = name;
		this.weight = weight;
		this.packDate = packDate;
		this.price = price;
		this.quantity = quantity;
	}
	
	//멤버메서드

	public int getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getPackDate() {
		return packDate;
	}

	public void setPackDate(int packDate) {
		this.packDate = packDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	//결산 메서드
	public void Balance(int sum) {
		this.setBalance(balance += sum);
	}
	
	public void refund(int sum) {
		this.setBalance(balance -= sum);
	}

	
	//출력 메서드
	public void printBeansInfo (){
		System.out.println(goodsNo + "\t" + name + "\t" + weight + "g" + "\t" + packDate + "\t" + price + "원" + "\t" + quantity + "팩");
	
	}
	
	public void availableBeanslist() {
	
		for(Map.Entry<Integer, Beans> str : AdminImpl.beanslist.entrySet()) { 
			Beans value = str.getValue();
			value.printBeansInfo();	
			}
	}

	public void OrderList() {
		for(Map.Entry<Integer, Beans> str : CustomerImpl.orderlist.entrySet()) { 
			Beans value = str.getValue();
			value.printBeansInfo();	}
		
	}
	public void AdminOrderList() {
		for(Map.Entry<Integer, Beans> str : Admin.adminOrderlist.entrySet()) { 
			Beans value = str.getValue();
			value.printBeansInfo();	}
		
	}
	public void ShowCompleteList() {
		for(Map.Entry<Integer, Beans> str :Customer.buylist.entrySet()) { 
			Beans value = str.getValue();
			value.printBeansInfo();	}
	}
	public void ShowCartList() {
		for(Map.Entry<Integer, Beans> str : Customer.cartlist.entrySet()) { 
			Beans value = str.getValue();
			value.printBeansInfo();	}
	}
	public void ShowRefundList() {
		for(Map.Entry<Integer, Beans> str : Admin.refundList.entrySet()) { 
			Beans value = str.getValue();
			value.printBeansInfo();	}
	}
	
}


	 
 


