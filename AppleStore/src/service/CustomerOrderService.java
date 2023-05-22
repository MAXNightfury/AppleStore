package src.service;

import src.dao.CustomerOrderDAO;
import src.vo.BasketVO;
import src.vo.CustomerVO;

import java.util.Arrays;
import java.util.Scanner;

public class CustomerOrderService {
    static Scanner scanner = new Scanner(System.in);
    static CustomerOrderDAO customerDAO = new CustomerOrderDAO(); //TODO static으로  올려 놓는게 맞나..?
    public void insertCustomerOrder(CustomerVO customerVO) {
        // 사용자가 ID 를 가지고 주문을 생성한다.
        // 뭘 주문할건지 basket 을 까?
        String basketIdString;
        System.out.print("주문할 장바구니 ID 를 입력하세요> "); //TODO 여기서 Stirng 으로 받고 스페이스바 기준으로 쪼개서 list 만들어서 그거만큼 돌려
        System.out.println();
        basketIdString = scanner.nextLine();
        String[] basketIdStringArr = basketIdString.split(" ");
        for(int i=0; i<basketIdStringArr.length; i++){
            BasketVO basketVO=new BasketVO();
            basketVO.setBasketId(Integer.parseInt(basketIdStringArr[i]));
            CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
            customerOrderDAO.insertCustomerOrder(customerVO);
        }
    }
}
