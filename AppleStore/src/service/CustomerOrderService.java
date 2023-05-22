package src.service;

import src.dao.CustomerOrderDAO;
import src.vo.CustomerVO;

import java.util.Scanner;

public class CustomerOrderService {
    static Scanner scanner = new Scanner(System.in);
    static CustomerOrderDAO customerDAO = new CustomerOrderDAO(); //TODO static으로  올려 놓는게 맞나..?
    public void insertCustomerOrder(CustomerVO customerVO) {
        // 사용자가 ID 를 가지고 주문을 생성한다.
        // 뭘 주문할건지 basket 을 까?
        int basketId;
        System.out.print("주문할 장바구니 ID 를 입력하세요> ");
        System.out.println();
        basketId = scanner.nextInt();


    }
}
