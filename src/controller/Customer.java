/*
 * 작성일 : 2022.07.15.
 * 작성자 : 이수진
 * 설명 : 고객 기능 인터페이스(고객 기능 메서드, 고객용 리스트)
 *  
 */

package controller;

import java.util.HashMap;
import java.util.Map;

import Model.Beans;

public interface Customer {
	
	//고객 - 상품목록
	public void productList();
	
	//고객 - 장바구니
	public void cartList(); //장바구니 리스트
	public void cartAdd(); //장바구니 추가
	public void cartRemove(); //장바구니 삭제
	public void cartBuy(); //장바구니
	

	//고객 - 바로구매
	public void nowBuy();	//바로구매
	
	//고객 - 환불
	public void refund(); //환붛
	
	//HashMap 생성
	public Map<Integer, Beans> cartlist = new HashMap<Integer, Beans>(); //장바구니
	public Map<Integer, Beans> orderlist = new HashMap<Integer, Beans>(); //주문목록
	public Map<Integer, Beans> buylist = new HashMap<Integer, Beans>(); //구매완료(장바구니, 바로구매)
	
	
}
