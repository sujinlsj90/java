/*
 * 작성일 : 2022.07.15.
 * 작성자 : 이수진
 * 설명 : 회원가입, 로그인, 메뉴 기능 구현
 *  
 */

package controller;


import java.util.InputMismatchException;

import controller.Admin;
import controller.AdminImpl;
import controller.CustomerImpl;
import view.Code;
import view.Main;

public class MenuImpL implements Code, Menu {	
	
	private MenuImpL() {}
	
	private static MenuImpL menu = new MenuImpL();
			
	public static MenuImpL getInstance() {
		if(menu == null) {
			menu= new MenuImpL();
		}
		return menu;
	}
	

	
	@Override
	public void commonMenu(int num) {
		Admin admin = AdminImpl.getInstance();
		Customer customer = CustomerImpl.getInstance();
		
		switch(num) {
		
		case SHOP_LOGIN : loginMenu(); //로그인
		break;
		case HOST_MENU : adminMenu();	//관리자로그인
		break;
		
		case HOST_STOCK_MENU : adminStockMenu();  // 재고관리 메뉴
		break;
		case HOST_GOODS_LIST : admin.productList(); // 상품목록
		break;
		case HOST_GOODS_ADD : admin.productAdd();	// 제품추가
		break;
		case HOST_GOODS_UPDATE : admin.productupdate();	//제품수정
		break;
		case HOST_GOODS_DEL : admin.productRemove(); //제품삭제
		break;
		
		case HOST_ORDER_MENU : adminOrderMenu(); //주문관리 메뉴
		break;
		case HOST_ORDER_LIST : admin.orderList(); //주문목록
		break;
		
		case HOST_ORDER_CONFIRM : admin.orderConfirm(); //결제승인
		break;
		case HOST_ORDER_CANCEL : admin.orderCancle(); //결제취소
		break;
		
		case HOST_ORDER_TOTAL :  adminTotalMenu();//결산
		break;
		
		//고객메뉴
		case GUEST_MENU : customerMenu(); //고객메뉴
		break;
		case GUEST_MENU_LOGIN : customerlogin(); //고객로그인
		break;
		case GUEST_GOODS : customer.productList(); //고객 상품리스트
		break;
		case GUEST_CART_MENU : customerCartMenu(); //장바구니 메뉴
		break;
		case GUEST_CART_LIST : customer.cartList(); //장바구니 리스트
		break;
		
		case GUEST_CART_ADD : customer.cartAdd(); //장바구니 추가
		break;
		
		case GUEST_CART_DEL : customer.cartRemove(); //장바구니 삭제
		break;
		case GUEST_CART_BUY : customer.cartBuy(); //장바구니 구매
		break;
		
		case GUEST_NOW_BUY : customer.nowBuy(); //바로구매
		break;
		
		case GUEST_REFUND : customer.refund();  //환불
		
		case 1000 : 
			System.out.println("프로그램을 종료합니다");
			System.exit(0);
			
		default :
			System.out.println("존재하지 않는 메뉴입니다");
		}
			
	
	}

	@Override
	public void loginMenu() {	// 로그인메뉴 - 5이상의 숫자, 문자열 입력 불가
		System.out.println("====================================");
		System.out.println("1.관리자로그인  2.고객로그인 3.회원가입 4.종료");
		System.out.println("====================================");
		System.out.print("번호입력 : ");
		int choice = 0;
		try {
			choice = Main.keyboard.nextInt();
		} catch (InputMismatchException e) {
			e.getStackTrace();
			System.out.println("메뉴는 1~4까지의 숫자입니다");
		} finally {
			Main.keyboard.nextLine();
			switch(choice) {
			case 1 : 
				adminLogin();
				break;	 
			
			case 2 : 
				customerlogin();
				break;
				
			case 3 :
				join();
				break;
				
			case 4 : 
				System.out.println("프로그램 종료");
				System.exit(0);
				
			default : 
				System.out.println("==================");
				System.out.println("다시 입력해주세요");
				System.out.println("==================");
				loginMenu();
				
			}
		}
	}

	@Override
	public void join() {	//회원가입
		
		System.out.println("----------------회원가입----------------");
		System.out.println("회원가입을 위해 정보를 입력하세요");
		System.out.println("--------------------------------------");
		
System.out.print("아이디 [5자~20자] : ");
		String id = Main.keyboard.nextLine();
		
		if(join.containsKey(id)) {
			System.out.println("이미 존재하는 아이디입니다");
			join();
		} else if(id.length() < 5 || 20 < id.length()) {
				System.out.println("아이디는 5자~20자입니다");
				join();
			} else {				
				System.out.print("비밀번호	[5자~20자] : ");
				String pw = Main.keyboard.nextLine();
					if(pw.length() < 5 || 20 < pw.length()) {
						System.out.println("비밀번호는 5자~20자입니다");
						System.out.print("비밀번호 : ");
						pw = Main.keyboard.nextLine();
					} else {
					}
					join.put(id, pw);
			}
		commonMenu(SHOP_LOGIN);
		}
	

	
	
		


	@Override
	public void adminLogin() {	//관리자 로그인
		String id;
		String pw;
			System.out.println("관리자 아이디와 비밀번호를 입력하세요");			
			System.out.print("아이디 : ");
			id = Main.keyboard.nextLine();
			
			System.out.print("비밀번호 : ");
			pw = Main.keyboard.nextLine();
			
			if(id.equals(Admin.ID) && pw.equals(Admin.PW)) {
				System.out.println("=======================");
				System.out.println("관리자모드로 로그인되었습니다");		
				System.out.println("=======================");
				System.out.println();
				adminMenu();
				System.exit(0);				
			} else {
				System.out.println("============================");
				System.out.println("정보가 일치하지 않습니다");				
				System.out.println("============================");
				System.out.println();
				adminLogin();
			}
			commonMenu(HOST_MENU);
	}
	
		
	

	@Override
	public void adminMenu() { //관리자 메뉴
		
		int adminChoice = 0;
		
		System.out.println("-------------관리자메뉴----------");
		System.out.println("1.재고관리 2.주문관리 3.로그아웃");
		System.out.println("-------------------------------");
		System.out.print("번호입력 : ");
		
		try {
			adminChoice = Main.keyboard.nextInt();
		} catch (InputMismatchException e) {
			e.getStackTrace();
			System.out.println("메뉴는 1~3까지의 숫자입니다");
		} finally {
			Main.keyboard.nextLine();
			switch(adminChoice) {
			case 1 : 
				commonMenu(HOST_STOCK_MENU);
				break;	
			
			case 2 : 
				commonMenu(HOST_ORDER_MENU);
				break;	
				
			case 3 : 
				commonMenu(SHOP_LOGIN);
				break;
				
			default :
				System.out.println("==================");
				System.out.println("다시 입력해주세요");
				System.out.println("==================");
				commonMenu(HOST_MENU);
			} 
		}
	}
		
	
	
	


	@Override
	public void adminStockMenu() {
		AdminImpl admin = AdminImpl.getInstance();
		
		int adminStockChoice = 0;
		System.out.println("------------재고관리---------------");
		System.out.println("1.목록  2.추가  3.수정  4.삭제  5.이전");
		System.out.println("---------------------------------");
		System.out.print("번호입력 : ");	
		
		try {
		adminStockChoice = Main.keyboard.nextInt();
		} catch (InputMismatchException e) {
			e.getStackTrace();
			System.out.println("메뉴는 1~5까지의 숫자입니다");
		} finally {
			Main.keyboard.nextLine();
			switch(adminStockChoice) {
			case 1 : 
				menu.commonMenu(HOST_GOODS_LIST);
				admin.productList();
				break;	
			
			case 2 : 
				menu.commonMenu(HOST_GOODS_ADD);
				break;	
				
			case 3 : 
				menu.commonMenu(HOST_GOODS_UPDATE);
				break;
				
			case 4 : 
				menu.commonMenu(HOST_GOODS_DEL);
				admin.productRemove();
				break;
			case 5 : 
				menu.commonMenu(HOST_MENU);
				break;
			default :
				System.out.println("==================");
				System.out.println("다시 입력해주세요");
				System.out.println("==================");
				commonMenu(HOST_STOCK_MENU);
			}
		}
	}
		

	@Override
	public void adminOrderMenu() {
		System.out.println("-------------------주문관리------------------");
		System.out.println("1.주문목록 2.결제승인 3.결제취소 4.결산 5.이전");
		System.out.println("-------------------------------------------");
		System.out.print("번호입력 : ");
		int adminOrderChoice = 0; 
		
		try {
			adminOrderChoice = Main.keyboard.nextInt();
		} catch (InputMismatchException e) {
			e.getStackTrace();
			System.out.println("메뉴는 1~3까지의 숫자입니다");
		} finally {
			Main.keyboard.nextLine();
			switch(adminOrderChoice) {
			case 1 : 
				commonMenu(HOST_ORDER_LIST);
				break;	
			
			case 2 : 
				commonMenu(HOST_ORDER_CONFIRM);
				break;	
				
			case 3 : 
				commonMenu(HOST_ORDER_CANCEL);
				break;
				
			case 4 : 
				commonMenu(HOST_ORDER_TOTAL);
				break;
			case 5 : 
				commonMenu(HOST_MENU);
				break;
			default :
				System.out.println("==================");
				System.out.println("다시 입력해주세요");
				System.out.println("==================");
				commonMenu(HOST_ORDER_MENU);
			}
		}
	}

	@Override
	public void adminTotalMenu() {
		AdminImpl admin = AdminImpl.getInstance();
		int totalChoice = 0;
		
		System.out.println("-------- 결   산 ---------");
		System.out.println("  1.결 산       2. 이 전  ");
		System.out.println("-----------------------");
		System.out.print("번호입력 : ");
		
		try {
			totalChoice = Main.keyboard.nextInt();
		} catch (InputMismatchException e) {
			e.getStackTrace();
			System.out.println("메뉴는 1~3까지의 숫자입니다");
		} finally {
			Main.keyboard.nextLine();
			switch(totalChoice) {
			case 1 : 
				admin.saleTotal();
				commonMenu(HOST_ORDER_TOTAL);
				break;	
			
			case 2 : 
				commonMenu(HOST_ORDER_MENU);
				break;	
				
			default :
				System.out.println("==================");
				System.out.println("다시 입력해주세요");
				System.out.println("==================");
				commonMenu(HOST_ORDER_MENU);
			} 
		}
	}
		


	@Override
	public void customerlogin() {
		
		String id;
		String pw;
		System.out.println("-----------고객로그인----------");
				
			System.out.println("아이디와 비밀번호를 입력해주세요");
			System.out.print("아이디 : ");
			id = Main.keyboard.next();
						 if(join.containsKey(id)) {
				System.out.print("비밀번호 : ");
				pw = Main.keyboard.next();
				if(join.get(id).equals(pw)) {
					System.out.println("=======================");
					System.out.println("로그인되었습니다");		
					System.out.println("=======================");
					commonMenu(GUEST_MENU);
				} else {
					System.out.println("=======================");
					System.out.println("비밀번호 불일치");
					System.out.println("=======================");
					commonMenu(GUEST_MENU_LOGIN);
					
				}
			} else {
				System.out.println("=======================");
				System.out.println("아이디를 다시 입력하세요");
				System.out.println("=======================");
				commonMenu(GUEST_MENU_LOGIN);
			}	
			 			
	}

	@Override
	public void customerMenu() {
		
		System.out.println("-------------고객메뉴----------------");
		System.out.println("1.장바구니  2.구매    3.환불   4.로그아웃");
		System.out.println("-----------------------------------");
		System.out.print("번호입력 : ");
		int customerChoice = 0; 
		
		try {
			customerChoice = Main.keyboard.nextInt();
		} catch (InputMismatchException e) {
			e.getStackTrace();
			System.out.println("메뉴는 1~4까지의 숫자입니다");
		} finally {
			Main.keyboard.nextLine();
			switch(customerChoice) {
			case 1 : 
				customerCartMenu();
				break;
			case 2 : 
				commonMenu(GUEST_NOW_BUY);
				break;
			case 3 :
				commonMenu(GUEST_REFUND);
				break;
			case 4 : 
				commonMenu(SHOP_LOGIN);
				break;
			default : 
				System.out.println();
				System.out.println("==================");
				System.out.println("잘못된 번호입니다");
				System.out.println("==================");
				commonMenu(GUEST_MENU);
			}
		}
	}

	@Override
	public void customerCartMenu() {
			
		System.out.println("=============장바구니============");
		System.out.println("1.추가 2.삭제 3.구매 4.목록 5.이전");
		System.out.println("-------------------------------");
		System.out.print("번호 : ");
		
		int cartN = 0;
		try {
			cartN = Main.keyboard.nextInt();
		} catch (InputMismatchException e) {
			e.getStackTrace();
			System.out.println("메뉴는 1~5까지의 숫자입니다");
		} finally {
			Main.keyboard.nextLine();
			switch(cartN) {
			case 1 : 
				commonMenu(GUEST_CART_ADD);
			break;
			case 2 : 
				commonMenu(GUEST_CART_DEL);
			break;
			case 3 : 
				commonMenu(GUEST_CART_BUY);
			break;
			case 4 : 
				commonMenu(GUEST_CART_LIST);
			break;
			case 5 :
				commonMenu(GUEST_MENU);
				break;
			default : 
				System.out.println();
				System.out.println("==================");
				System.out.println("잘못된 번호입니다");
				System.out.println("==================");
				customerCartMenu();
			}
		}
	}
}
