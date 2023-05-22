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
        // 주문만 생성함 여기에는 물건 개수 없음
        String basketIdString;
        int count = 0;
        System.out.print("주문할 장바구니 ID 를 입력하세요> "); //TODO 여기서 Stirng 으로 받고 스페이스바 기준으로 쪼개서 list 만들어서 그거만큼 돌려
        System.out.println();
        basketIdString = scanner.nextLine();
        String[] basketIdStringArr = basketIdString.split(" ");
        for (int i = 0; i < basketIdStringArr.length; i++) {
            BasketVO basketVO = new BasketVO();
            basketVO.setBasketId(Integer.parseInt(basketIdStringArr[i]));
            CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
            count=customerOrderDAO.insertCustomerOrder(customerVO, basketVO);
            count= count+count;
        }
        if(count != 0){
            System.out.println(count + "개 주문에 성공했습니다.");
        }
    }

    public void insertOrderDetail(CustomerVO customerVO) {
        // 방금 생성된 orderId 를 뽑아서
    }

}
