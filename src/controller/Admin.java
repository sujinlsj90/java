/*
 * 작성일 : 2022.07.15.
 * 작성자 : 이수진
 * 설명 : 관리자 기능 인터페이스(관리자 기능 메서드, 관리자용 리스트)
 * 
 */

package controller;

import java.util.HashMap;
import java.util.Map;

import Model.Beans;

public interface Admin {
	
	public static final String ID = "admin";
	public static final String PW = "admin";
	
	//관리자 - 재고관리
	public void productList(); //원두목록
	public void productAdd(); //원두추가
	public void productupdate(); //원두수정
	public void productRemove(); //원두삭제
	
	//관리자 - 주문관리
	public void orderList(); //주문목록
	public void orderConfirm(); //결제승인
	public void orderCancle(); //결제취소
	
	//관리자 - 결산
	public void saleTotal(); //결산
	
	
	//HashMap 생성
		public Map<Integer, Beans> beanslist = new HashMap<Integer, Beans>(); //재고리스트
		public Map<Integer, Beans> adminOrderlist = new HashMap<Integer, Beans>(); //구매요청내역 확인리스트
		public Map<Integer, Beans> refundList = new HashMap<Integer, Beans>(); // 환불요청내역 확인리스트

}
