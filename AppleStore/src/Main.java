import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static String id = null;
	static String password = null;
	static boolean isCustomer = false;
	static boolean isAdmin = false;
	
	public static void welcomeMenu() {
		System.out.println("-------------------");
		System.out.println("1. 로그인 | 2. 회원가입");
		System.out.println("-------------------");
		System.out.print("메뉴 : ");
		int menu = scanner.nextInt();
		scanner.nextLine();
		switch (menu) {
		case 1 : login(); break;
		case 2 : join(); break;
		default:
			break;
		}
	}
	
	public static void customerMenu() {
		System.out.println("---------------------------------------------");
		System.out.println("1.제품보기 | 2.장바구니 | 3.주문확인 | 4.회원관리 | 5.로그아웃");
		System.out.println("---------------------------------------------");
		System.out.print("메뉴 : ");
		int menu = scanner.nextInt();
		scanner.nextLine();
		switch (menu) {
		case 1: 
			
			break;

		default:
			break;
		}
	}
	
	public static void adminMenu() {
		System.out.println("---------------------------------------------");
		System.out.println("1.상품관리 | 2.주문관리 | 3.회원관리 | 4.로그아웃");
		System.out.println("---------------------------------------------");
	
		
	}
	
	public static void login() {
		System.out.print("ID : ");
		id = scanner.nextLine();
		System.out.println("Password : ");
		password=scanner.nextLine();
		
		/*
		 * 고객이 로그인 성공시 isCustomer = True;
		 * 관리자 로그인 성공시 isAdmin = True;
		 */
		if (isCustomer) {
			customerMenu();
		} else if (isAdmin) {
			adminMenu();
		}
	}
	
	public static void join() {
		/*
		 * 회원가입
		 */
		
		welcomeMenu();
	}
	

		System.out.println("AppleStore에 오신 걸 환영합니다.");
		welcomeMenu();
	}

}	

