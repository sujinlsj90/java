/*
 * 작성일 : 2022.07.15.
 * 작성자 : 이수진
 * 설명 : 진입 화면
 *  
 */

package view;

import java.util.Scanner;
import controller.MenuImpL;


public class Main {
	public static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {

		
		System.out.println("*******************************");
		System.out.println("------------원두쇼핑몰------------");
		System.out.println("*******************************");
		System.out.println();
		
		MenuImpL menu = MenuImpL.getInstance();
		menu.loginMenu();
	}

}
