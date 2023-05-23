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
    public static ProductService productService =new ProductService();
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

    public static boolean adminLogout() { // TODO 로그아웃 시 비밀번호 확인하지 않음
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

    }

    public static void OrderManage() {

    }

    public static void CustomerManage() {

    }

}