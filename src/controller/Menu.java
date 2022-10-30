/*
 * 작성일 : 2022.07.15.
 * 작성자 : 이수진
 * 설명 : 메뉴 인터페이스 (메뉴 메서드, 회원관리리스트 생성)
 *  
 */

package controller;

import java.util.HashMap;
import java.util.Map;

public interface Menu {
	
	
	public void commonMenu(int num); //공통메뉴
	public void loginMenu(); //로그인메뉴
	public void join(); //회원가입

	public void adminLogin(); //관리자 로그인
	public void adminMenu(); //관리자 메뉴
	public void adminStockMenu(); //관리자 재고관리 메뉴
	public void adminOrderMenu(); //관리자 주문관리 메뉴
	public void adminTotalMenu(); //관리자 결산 메뉴

	public void customerlogin(); //고객로그인
	public void customerMenu(); //고객메뉴
	public void customerCartMenu(); //고객 장바구니 메뉴 
	
	
	//HashMap생성
	public Map<String, String> join = new HashMap<String, String>();	//회원리스트
	
}
