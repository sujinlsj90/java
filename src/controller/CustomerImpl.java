/*
 * 작성일 : 2022.07.15.
 * 작성자 : 이수진
 * 설명 : 고객 기능 구현(장바구니, 구매, 환불)
 *  
 */
package controller;


import Model.Beans;
import view.Code;
import view.Main;

public class CustomerImpl implements Customer{
	
	//싱글톤생성
	private static CustomerImpl customer = new CustomerImpl();
	
	public static CustomerImpl getInstance() {
		if(customer == null) {
			customer= new CustomerImpl();
		}
		return customer;
	}	

	
	//싱글톤 호출(메뉴)	
	Menu menu = MenuImpL.getInstance();
	

	@Override
	public void cartList() {	//장바구니 목록 출력
		Beans beans = Beans.getInstance();
		System.out.println("----------------------장바구니 목록----------------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t단가\t수량\t");
		System.out.println("------------------------------------------------------");
		
		beans.ShowCartList() ;
		menu.commonMenu(Code.GUEST_CART_MENU);
		
	}

	@Override
	public void cartAdd() { 	//장바구니 추가 
		Beans beans = Beans.getInstance();
		System.out.println("----------------------구매가능 목록----------------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t금액\t재고\t");
		System.out.println("------------------------------------------------------");
		beans.availableBeanslist();
		
		System.out.println("=============장바구니 추가============");
		System.out.print("제품번호를 입력하세요 [이전 0] : ");
		int volume;
		int goodsNo = Main.keyboard.nextInt();
		if(goodsNo == 0) {
			menu.commonMenu(Code.GUEST_CART_MENU);
		} else {
			if(AdminImpl.beanslist.containsKey(goodsNo)) {		//재고리스트에 해당제품이 있으면 
				System.out.print("구매수량 : ");
				volume = Main.keyboard.nextInt();	// volume = 구매수량
				
				String name = Admin.beanslist.get(goodsNo).getName();	//각 변수에 해당 제품의 정보 불러옴
				int weight = Admin.beanslist.get(goodsNo).getWeight();
				int price = Admin.beanslist.get(goodsNo).getPrice();
				int packDate = Admin.beanslist.get(goodsNo).getPackDate();
				int quantity = Admin.beanslist.get(goodsNo).getQuantity();
				
				if(volume > AdminImpl.beanslist.get(goodsNo).getQuantity()) {	//장바구니 추가 가능한 수량 체크
					System.out.println("========추가 가능한 수량이 부족합니다========");
					 cartAdd();
				} else {
						if(cartlist.containsKey(goodsNo)) {	// 장바구니에 없으면 새로 추가, 있으면 동일상품 증가
							int cartAdd = cartlist.get(goodsNo).getQuantity() + volume; 
							cartlist.get(goodsNo).setQuantity(cartAdd);
						} else {
							cartlist.put(goodsNo, new Beans(goodsNo, name, weight, packDate, price, volume));
						}
						quantity -= volume;	// 장바구니 추가 시 구매가능 수량 감소
						Admin.beanslist.get(goodsNo).setQuantity(quantity);
				
						System.out.println("=======================================");
						System.out.println(goodsNo + "번 제품이 장바구니에 담겼습니다");
						System.out.println("=======================================");
						cartList();
				
				cartAdd();
				}	
				}  else {
				System.out.println("==============없는 제품입니다============");
				cartAdd();
			}
			
		}
			
		menu.commonMenu(Code.GUEST_CART_MENU);
		}	


	@Override
	public void productList() {	//구매요청목록
		Beans beans = Beans.getInstance();
		System.out.println("----------------------구매요청 목록---------------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t금액\t수량");
		System.out.println("-----------------------------------------------------");
		beans.OrderList();
		menu.commonMenu(Code.GUEST_CART_MENU);
	}
	
	@Override
	public void cartRemove() {	// 장바구니 추가된 상품 삭제
		System.out.println("=========장바구니 삭제========");
		System.out.print("제품번호를 입력하세요 [이전 0] : ");
		int goodsNo = Main.keyboard.nextInt();
		if(goodsNo == 0) {
			menu.commonMenu(Code.GUEST_CART_MENU);
		} else {
			if(cartlist.containsKey(goodsNo)) {	
				
				int volume = cartlist.get(goodsNo).getQuantity();	//장바구니 갯수가 변동되는만큼 구매가능 수량 증가
				int quantity = Admin.beanslist.get(goodsNo).getQuantity(); 	 
				quantity += volume;				
				Admin.beanslist.get(goodsNo).setQuantity(quantity);		
				
				cartlist.remove(goodsNo);	//장바구니에서 제품 삭제
				System.out.println("===========================");
				System.out.println(goodsNo + "번 제품이 삭제되었습니다");
				System.out.println("===========================");
			} else {
				System.out.println("=========없는 제품입니다=======");
				cartRemove();
			}
		}	
		menu.commonMenu(Code.GUEST_CART_MENU);
		
	}
	@Override
	public void cartBuy() {	//장바구니에 있는 제품 구매하기
		Beans beans = Beans.getInstance();
		System.out.println("----------------------장바구니 목록----------------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t단가\t수량\t");
		System.out.println("------------------------------------------------------");
		beans.ShowCartList() ;
		
		System.out.println("=========장바구니 구매========"); //장바구니목록에서 삭제
		System.out.print("제품번호를 입력하세요 [이전 0] : ");
		int goodsNo = Main.keyboard.nextInt();
		if(goodsNo == 0) {
			menu.commonMenu(Code.GUEST_CART_MENU);
		} else {
			if(cartlist.containsKey(goodsNo)) {	//장바구니에 해당 키값 있을 시 주문목록(고객, 관리자)에 추가
				String name = cartlist.get(goodsNo).getName();		
				int weight = cartlist.get(goodsNo).getWeight();
				int price = cartlist.get(goodsNo).getPrice();
				int packDate = cartlist.get(goodsNo).getPackDate();
				int quantity = cartlist.get(goodsNo).getQuantity();
			
				orderlist.put(goodsNo, new Beans(goodsNo, name, weight, packDate, price, quantity));	//고객 및 관리자 주문리스트에 값 넣기
				Admin.adminOrderlist.putAll(orderlist);
				
				int pay = orderlist.get(goodsNo).getPrice() * orderlist.get(goodsNo).getQuantity();	//pay : 결제예정금액
									
					cartlist.remove(goodsNo);	//장바구니에서 제품 삭제
					System.out.println("==========================");
					System.out.println("구매요청제품 : " + goodsNo);
					System.out.println("결제예정금액 : " + pay + "원");
					System.out.println("==========================");
					productList();
				} else {
					System.out.println("=========없는 제품입니다=======");
					cartBuy();
				}
			} 
		menu.commonMenu(Code.GUEST_CART_MENU);
		}
	
	@Override
	public void nowBuy() {	//즉시구매
		Beans beans = Beans.getInstance();
		System.out.println("----------------------구매가능 목록----------------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t금액\t재고\t");
		System.out.println("------------------------------------------------------");
		beans.availableBeanslist();
		
		System.out.println("=========제품 구매하기=======");
		System.out.print("제품번호를 입력하세요 [이전 0] : ");
		int goodsNo = Main.keyboard.nextInt();
		if(goodsNo == 0) {
			menu.commonMenu(Code.GUEST_MENU);
		} else {
			if(Admin.beanslist.containsKey(goodsNo)) { //해당 제품이 있다면
				System.out.print("구매수량 : ");
				int volume = Main.keyboard.nextInt();
				String name = Admin.beanslist.get(goodsNo).getName();		//값 불러오기
				int weight = Admin.beanslist.get(goodsNo).getWeight();
				int price = Admin.beanslist.get(goodsNo).getPrice();
				int packDate = Admin.beanslist.get(goodsNo).getPackDate();	
				
				if(volume > Admin.beanslist.get(goodsNo).getQuantity()) {	//구매가능한 수량 체크 후 구매 진행
					System.out.println("========구매 가능한 수량이 부족합니다========");
					 cartAdd();					 
				} else {
							if(orderlist.containsKey(goodsNo)) {	// 주문리스트에 없으면 추가, 있으면 동일상품 증가
								int orderAdd = orderlist.get(goodsNo).getQuantity() + volume; 
								orderlist.get(goodsNo).setQuantity(orderAdd);
								Admin.adminOrderlist.get(goodsNo).setQuantity(orderAdd);
							} else {
								orderlist.put(goodsNo, new Beans(goodsNo, name, weight, packDate, price, volume));		//관리자, 고객 주문리스트에 값 넣기
								Admin.adminOrderlist.putAll(orderlist);
							}
					
					int pay = orderlist.get(goodsNo).getPrice() * volume;	//pay : 결제예정금액
					
					System.out.println("==========================");
					System.out.println("구매요청제품 : " + goodsNo);
					System.out.println("결제예정금액 : " + pay + "원");
					System.out.println("==========================");
					
					int quantity = Admin.beanslist.get(goodsNo).getQuantity(); 	//구매가능 수량 감소
					quantity -= volume;	
					Admin.beanslist.get(goodsNo).setQuantity(quantity);

					nowBuy();
				} 
			} else {
					System.out.println("=========없는 제품입니다=======");
					cartBuy();
				}
			} 
		menu.commonMenu(Code.GUEST_CART_MENU);
		}

	@Override
	public void refund() {	//환불요청
		Beans beans = Beans.getInstance();	
		System.out.println("----------------------구매내역----------------------");
		System.out.println("제품번호\t제품명\t무게\t로스팅날짜\t금액\t수량");
		System.out.println("---------------------------------------------------");
		beans.ShowCompleteList();
		
		System.out.println("============환불요청==========="); //장바구니목록에서 삭제
		System.out.print("제품번호를 입력하세요 [이전 0] : ");
		int goodsNo = Main.keyboard.nextInt();
		if(goodsNo == 0) {
			menu.commonMenu(Code.GUEST_MENU);
		} else {
		if(buylist.containsKey(goodsNo)) {	//구매목록에 있으면 환불리스트로 값 보내기
				String name = buylist.get(goodsNo).getName();		
				int weight = buylist.get(goodsNo).getWeight();
				int price = buylist.get(goodsNo).getPrice();
				int packDate = buylist.get(goodsNo).getPackDate();
				int quantity = buylist.get(goodsNo).getQuantity();
				
				Admin.refundList.put(goodsNo, new Beans(goodsNo, name, weight, packDate, price, quantity));
				
				int refund = Admin.refundList.get(goodsNo).getPrice() * Admin.refundList.get(goodsNo).getQuantity();	//refund : 환불예정금액
				buylist.remove(goodsNo);	//구매 목록에서 해당 제품 삭제
				System.out.println("==========================");
				System.out.println("환불요청제품 : " + goodsNo);
				System.out.println("환불예정금액 : " + refund + "원");
				System.out.println("==========================");
				
			} else {
				System.out.println("=========없는 제품입니다=======");
			}
		}
		menu.commonMenu(Code.GUEST_MENU);
	}
}
