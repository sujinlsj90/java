/*
 * 작성일 : 2022.07.15.
 * 작성자 : 이수진
 * 설명 : 관리자 기능 구현(재고관리, 주문관리 등)
 *  
 */

package controller;


import java.text.SimpleDateFormat;
import Model.Beans;
import view.Code;
import view.Main;


public class AdminImpl implements Admin{
	
	//싱글톤 적용
		private AdminImpl() {
		}
		
		private static AdminImpl admin = new AdminImpl();
				
		public static AdminImpl getInstance() {
			if(admin == null) {
				admin= new AdminImpl();
			}
			return admin;
		}
		
	
	//싱글톤 호출(DTO, 메뉴)
	Beans beans = Beans.getInstance();
	Menu menu = MenuImpL.getInstance();

	@Override
	public void productList() {		//물품재고 출력
		System.out.println("---------------------물품재고---------------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t금액\t재고\t");
		System.out.println("------------------------------------------------");
		
		beans.availableBeanslist();

		menu.commonMenu(Code.HOST_STOCK_MENU);	
	}


	
	@Override
	public void productAdd() {	//재고 추가
		
		
			System.out.println("==========제품 등록==========");
			System.out.print("원두명 : ");
			String name = view.Main.keyboard.next();
			
			System.out.print("무게(g) : ");
			int weight = view.Main.keyboard.nextInt();
			
			System.out.print("로스팅날짜(yymmdd) : ");
			int packDate = view.Main.keyboard.nextInt();
			
			System.out.print("금액 : ");
			int price = view.Main.keyboard.nextInt();
			
			System.out.print("수량 : ");
			int quantity = view.Main.keyboard.nextInt();
			
			int goodsNo;
			while(true) {
				goodsNo = (int)(Math.random()*1000) + 1000;
				if((false == Admin.beanslist.containsKey(goodsNo))) { //중복번호방지
					Admin.beanslist.put(goodsNo, beans);
					System.out.println("==================================");
					System.out.println("상품이 추가되었습니다");
					System.out.println("==================================");
					break;
			}
		
		}
			Admin.beanslist.put(goodsNo, new Beans(goodsNo, name, weight, packDate, price, quantity));
			menu.commonMenu(Code.HOST_STOCK_MENU);		
		
		}
	
	

	@Override 
	public void productupdate() {	//등록된 재고 수정
		System.out.println("---------------------물품재고---------------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t금액\t재고\t");
		System.out.println("------------------------------------------------");
		
		beans.availableBeanslist();
				
				System.out.print("수정할 상품의 제품번호를 입력하세요[이전 0] : ");
				int goodsNo = Main.keyboard.nextInt();
				if(goodsNo == 0) {
					menu.commonMenu(Code.HOST_STOCK_MENU);
				} else {
					if (Admin.beanslist.containsKey(goodsNo)) {
					System.out.println("==========제품 수정==========");
					System.out.print("원두명 수정 : ");
					String name = Main.keyboard.next();
					
					System.out.print("무게 수정 : ");
					int weight = Main.keyboard.nextInt();
				
					System.out.print("로스팅날짜 수정 : ");
					int packDate = Main.keyboard.nextInt();
					
					System.out.print("금액 수정 : ");
					int price = Main.keyboard.nextInt();
				
					System.out.print("수량 수정 : ");
					int quantity = Main.keyboard.nextInt();
					System.out.println("==================================");
					System.out.println(goodsNo + "번 원두정보가 수정되었습니다");
					
							
					Admin.beanslist.put(goodsNo, new Beans(goodsNo, name, weight, packDate, price, quantity));
				} else {
					System.out.println("---------없는 제품입니다-------");
					productupdate();
				}
				menu.commonMenu(Code.HOST_STOCK_MENU);
				}
	}

	@Override
	public void productRemove() {	//등록된 재고 삭제
		System.out.println("---------------------물품재고---------------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t금액\t재고\t");
		System.out.println("------------------------------------------------");
		
		beans.availableBeanslist();
				
				System.out.print("삭제 상품의 제품번호를 입력하세요 [이전 0] : ");
				int goodsNo = Main.keyboard.nextInt();
				if(goodsNo == 0) {
					menu.commonMenu(Code.HOST_STOCK_MENU);
				} else {
					if (Admin.beanslist.containsKey(goodsNo)) {

						Admin.beanslist.remove(goodsNo);
						System.out.println("===========================");
						System.out.println(goodsNo + "번 제품이 삭제되었습니다");
						System.out.println("===========================");
					} else {	
						System.out.println("==========없는 제품입니다==========");
						productRemove();
					}
				}
				menu.commonMenu(Code.HOST_STOCK_MENU);
		}

	
	//고객 구매 시점 이후
	
	@Override
	public void orderList() {	//주문요청 리스트(구매대기)
		
		Beans beans = Beans.getInstance();
		System.out.println("-----------------구매대기 목록------------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t금액\t수량");
		System.out.println("--------------------------------------------");
		beans.AdminOrderList();
		menu.commonMenu(Code.HOST_ORDER_MENU);
	}

	@Override
	public void orderConfirm() {	//구매승인
		System.out.println("-----------------구매대기 목록-----------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t금액\t수량");
		System.out.println("--------------------------------------------");
		beans.AdminOrderList();
		
		System.out.println("==============구매승인==============");
		System.out.print("제품번호 [이전:0] : ");
		int goodsNo = Main.keyboard.nextInt();
		if(goodsNo == 0) { 
			menu.commonMenu(Code.HOST_ORDER_MENU);
		} else {
			if(adminOrderlist.containsKey(goodsNo)) {	//해당 키값이 주문리스트에 있다면 구매완료 리스트에 헤딩 제품 추가
				String name = adminOrderlist.get(goodsNo).getName();		
				int weight = adminOrderlist.get(goodsNo).getWeight();
				int price = adminOrderlist.get(goodsNo).getPrice();
				int packDate = adminOrderlist.get(goodsNo).getPackDate();
				int quantity = adminOrderlist.get(goodsNo).getQuantity();
				int pay = adminOrderlist.get(goodsNo).getPrice() * adminOrderlist.get(goodsNo).getQuantity();
				
				Customer.buylist.put(goodsNo, new Beans(goodsNo, name, weight, packDate, price, quantity));	//구매완료 리스트에 추가
				
				beans.Balance(pay);	//결산에 구매승인내역 반영
				
				String orderTime = new SimpleDateFormat("yy-MM-dd HH:mm").format(new java.util.Date());
				
				adminOrderlist.remove(goodsNo);		//관리자 주문내역에서 삭제
				Customer.orderlist.remove(goodsNo);	
				System.out.println("==================================");
				System.out.println("판매일시 : " + orderTime);
				System.out.println("판매제품 : " + goodsNo);
				System.out.println("판매대금 : " + pay + "원");
				System.out.println("==================================");
				orderList();
			} else {
				System.out.println("=========없는 제품입니다=======");
				orderConfirm() ;
			}
		}
		menu.commonMenu(Code.HOST_ORDER_MENU);		
	}

	@Override
	public void orderCancle() {	//환불요청승인
		System.out.println("-----------------환불요청내역----------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t금액\t수량");
		System.out.println("------------------------------------------");
		beans.ShowRefundList();		
		
		System.out.print("환불 처리할 코드를 입력하세요[이전:0] : ");
		int goodsNo = Main.keyboard.nextInt();
		
		if(goodsNo == 0) {
			menu.commonMenu(Code.HOST_ORDER_MENU);
		} else {
			if(refundList.containsKey(goodsNo)) {	
				int volume = refundList.get(goodsNo).getQuantity();// 환불 시 재고 증가 및 결산 감소 
				int quantity = beanslist.get(goodsNo).getQuantity();
				quantity += volume;	
				beanslist.get(goodsNo).setQuantity(quantity);
				
				int refund = refundList.get(goodsNo).getPrice() * refundList.get(goodsNo).getQuantity();	//승인금액 계산
				
				beans.refund(refund);		// 환불 시 결산 감소 메서드
				
				Customer.buylist.remove(goodsNo);
				refundList.remove(goodsNo);//환불요청리스트에서 삭제
				
				String date = new SimpleDateFormat("yy.MM.dd. HH:mm").format(new java.util.Date());
				System.out.println("==============================");
				System.out.println("환불승인제품코드 : " + goodsNo);
				System.out.println("환불승인일시 : " + date);
				System.out.println("환불승인금액 : " + refund);
				System.out.println("==============================");
				
			} else {
			System.out.println("==============================");
			System.out.println("요청목록에 없습니다");
			System.out.println("==============================");
			orderCancle();
			} 
		menu.commonMenu(Code.HOST_ORDER_MENU);	
		}
	}

	@Override
	public void saleTotal() {	//결산내용 출력
		String orderTime = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(new java.util.Date());
		System.out.println(orderTime + " 기준 결산 : " +  beans.getBalance() + "원");
		menu.commonMenu(Code.HOST_ORDER_MENU);
		
	}

}


