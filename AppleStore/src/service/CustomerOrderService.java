package src.service;

import src.dao.BasketDAO;
import src.dao.CustomerOrderDAO;
import src.dao.ProductDAO;
import src.vo.BasketVO;
import src.vo.CustomerOrderJoinProductVO;
import src.vo.CustomerVO;
import src.vo.ProductVO;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerOrderService {
    static Scanner scanner = new Scanner(System.in);
    static CustomerOrderDAO customerDAO = new CustomerOrderDAO(); //TODO static으로  올려 놓는게 맞나..?
    static BasketService basketService = new BasketService();

    public void insertCustomerOrder(CustomerVO customerVO) { //TODO 재고 확인 해서 넣어

        String basketIdString;
        int count = 0;
        System.out.print("주문할 장바구니 ID 를 입력하세요> ");
        System.out.println();
        basketIdString = scanner.nextLine();
        String[] basketIdStringArr = basketIdString.split(" ");
        int orderBundleId = Integer.parseInt(basketIdStringArr[0]);

        for (int i = 0; i < basketIdStringArr.length; i++) {
            BasketVO basketVO = new BasketVO();
            // 장바구니 모든 열 보여주는 메소드 호출해서 basketVO return 받아
            basketVO.setBasketId(Integer.parseInt(basketIdStringArr[i]));
            basketVO = basketService.selectOneBasket(basketVO);
            // 재고 확인
            int basketProductCount=basketVO.getBasketProductCount();
            // 현재 productCount
            int basketProductId=basketVO.getProductId();
            ProductVO productVO =new ProductVO();
            productVO.setProductId(basketProductId);
            ProductDAO productDAO = new ProductDAO();
            productVO =productDAO.selectProductByBasket(productVO);
            if(productVO.getProductCount() -basketProductCount <0 ){
                // 시스템 종료 재고 부족
                System.out.println("상품재고가 부족합니다."); //TODO 어떤 상품?
                System.exit(0);
            }else{
                // 위 basketVO에는 선택한 basket의 모든 정보가 있으니까 저걸 basketId 배열에 받은 숫자만큼 insert 해
                CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
                count = customerOrderDAO.insertCustomerOrder(customerVO, basketVO, orderBundleId);
                count++;
                // 양수면 그만큼 빼
                productDAO.updateProductCountDecrease(basketVO);
            }
        }
        if (count != 0) {
            System.out.println(count + "개 주문에 성공했습니다."); //TODO 사실 basketId 개수니까 뭐라고 해야하지? 이거 다시봐 틀려
            for (int i = 0; i < basketIdStringArr.length; i++) {
                BasketVO basketVO = new BasketVO();
                basketVO.setBasketId(Integer.parseInt(basketIdStringArr[i]));
                BasketDAO basketDAO = new BasketDAO();
                basketDAO.deleteBasket(basketVO); // 주문 완료 되면 장바구니 찐삭제
                //product.productCount에서 주문한거만큼 count 내려

            }
        }

    }

    public void selectCustomerOrder(CustomerVO customerVO) {
        ArrayList<CustomerOrderJoinProductVO> orderArr = new ArrayList<CustomerOrderJoinProductVO>();
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
        Set<Integer> orderIdArrSet = new HashSet<Integer>();
        orderArr = customerOrderDAO.selectCustomerOrder(customerVO);
        Map<Integer, List<CustomerOrderJoinProductVO>> orderBybundleId = orderArr.stream().collect(Collectors.groupingBy(CustomerOrderJoinProductVO::getOrderBundleId));
        for(int i=0; i<orderArr.size(); i++){
            orderIdArrSet.add(orderArr.get(i).getOrderBundleId());
        }

        List<Integer> orderIdArr =new ArrayList<Integer>(orderIdArrSet);

        for (int i = 0; i < orderIdArr.size(); i++) {
            int bundleId = orderIdArr.get(i);
            System.out.println();
            System.out.println();
            System.out.print("주문번호: " + bundleId + "  ");
            System.out.println("주문시각: " + orderArr.get(i).getOrderInputDate());
            System.out.printf("%5s | %3s | %3s", "제품이름", "개수", "총가격\n");
            System.out.println("-------------------------");
            List<CustomerOrderJoinProductVO> cojpLists = orderBybundleId.get(bundleId);

            for(int j=0; j<cojpLists.size(); j++) {
                String productName = cojpLists.get(j).getProductName();
                int productCount =  cojpLists.get(j).getOrderProductCount();
                int productSumPrice = cojpLists.get(j).getProductPrice();
                System.out.printf("%5s | %3s | %5s\n", productName, productCount, productSumPrice);

            }
        }
    }

}
