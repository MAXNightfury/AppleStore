package src.service;

import src.dao.AdminDAO;
import src.dao.ProductDAO;
import src.vo.AdminVO;
import src.vo.ProductVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class AdminService {
    public static Scanner scanner = new Scanner(System.in);
    public static ProductVO productVO = null;
    public static ProductDAO productDAO = new ProductDAO();
    public static AdminVO adminVO = null;
    public static AdminDAO adminDAO = null;

    public static AdminVO adminLogin() {
        scanner = new Scanner(System.in);
        System.out.print("관리자 ID : ");
        String adminId = scanner.nextLine();
        System.out.print("관리자 PW : ");
        String adminPassword = scanner.nextLine();
        adminDAO = new AdminDAO();
        int resultCount = adminDAO.selectAdmin(adminId, adminPassword);
        if (resultCount == 1) {
            System.out.println("관리자로 로그인 되었습니다.");
            adminVO = new AdminVO();
            adminVO.setAdminId(adminId);
            adminVO.setAdminPassword(adminPassword);
        } else {
            System.out.println("관리자 계정이 올바르지 않습니다.");
        }
        return adminVO;
    }

    public static boolean adminLogout() {
        System.out.print("관리자 " + adminVO.getAdminId() + "의 비밀번호 : ");
        scanner = new Scanner(System.in);
        String adminPassword = scanner.nextLine();

        int resultCount = adminDAO.selectAdmin(adminVO.getAdminId(), adminPassword);
        if (resultCount == 1) {
            System.out.println("관리자로 로그인 되었습니다.");
            return true;
        } else {
            System.out.println("관리자 계정이 올바르지 않습니다.");
            return false;
        }
    }

    public static void ProductManage() {
        System.out.println("---------------------------------------------");
        System.out.println("1.상품추가 | 2.상품확인 | 3.상품수정 | 4.상품삭제 | 5.뒤로가기");
        System.out.println("---------------------------------------------");
        scanner = new Scanner(System.in);
        System.out.print("메뉴 : ");
        int menu = scanner.nextInt();
        scanner.nextLine();

        switch (menu) {
            case 1:
                productVO = new ProductVO();
                System.out.println("[새로운 상품의 정보를 입력하세요.]");
                System.out.print("상품 이름: ");
                productVO.setProductName(scanner.nextLine());
                System.out.println("1.MAC | 2.IPhone | 3.IPad | 4.Air Pods | 5.Apple Watch| 6.Accessories");
                System.out.print("상품의 카테고리: ");
                productVO.setCategoryId(scanner.nextInt());
                System.out.print("상품 가격: ");
                productVO.setProductPrice(scanner.nextInt());
                scanner.nextLine();
                System.out.print("상품 이미지: ");
                productVO.setProductImage(scanner.nextLine());
                System.out.print("상품의 개수: ");
                productVO.setProductCount(scanner.nextInt());
                int resultCount = productDAO.insertProduct(productVO);
                System.out.println(resultCount + "개의 상품이 추가되었습니다.");
                break;

            case 2:
                System.out.println("--------------------------------------");
                System.out.println("1.카테고리별 | 2.인기순 | 3.가격낮은순 | 4.가격높은순");
                System.out.println("--------------------------------------");
                System.out.print("메뉴 : ");
                int submenu = scanner.nextInt();
                scanner.nextLine();
                switch (submenu) {
                    case 1:
                        System.out.println("---------------------------------------------------------------------");
                        System.out.println("1.MAC | 2.IPhone | 3.IPad | 4.Air Pods | 5.Apple Watch| 6.Accessories");
                        System.out.println("---------------------------------------------------------------------");
                        System.out.print("카테고리 번호 : ");
                        int categoryId = scanner.nextInt();
                        scanner.nextLine();
                        productDAO.selectCategoryProduct(categoryId);
                        break;
                    case 2:
                        productDAO.selectPopularProduct();
                        break;
                    case 3:
                        productDAO.selectlowPriceProduct();
                        break;
                    case 4:
                        productDAO.selectHighPriceProduct();
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected value: " + menu);
                }

                break;
            case 3:
                System.out.print("수정할 상품의 ID : ");
                int updateProductId = scanner.nextInt();
                scanner.nextLine();
                ProductVO updateProductVO = new ProductVO();
                updateProductVO.setProductId(updateProductId);
                int updateCount = productDAO.updateProduct(updateProductVO);
                System.out.println(updateCount + "개의 상품이 수정되었습니다.");
                break;

            case 4:
                System.out.print("삭제할 상품의 ID : ");
                int deleteProductId = scanner.nextInt();
                scanner.nextLine();
                ProductVO deleteProductVO = new ProductVO();
                deleteProductVO.setProductId(deleteProductId);
                int deleteCount = productDAO.deleteProduct(deleteProductVO);
                System.out.println(deleteCount+"개의 상품을 삭제했습니다.");
                break;

            case 5:
                return;
            default:
                throw new IllegalArgumentException("Unexpected value: " + menu);
        }
    }

    public static void OrderManage() {

    }

    public static void CustomerManage() {

    }

}