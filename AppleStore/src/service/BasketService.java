package src.service;

import src.dao.BasketDAO;
import src.vo.BasketVO;
import src.vo.CustomerVO;

import java.util.Scanner;

public class BasketService {
    Scanner scanner = new Scanner(System.in);
    static BasketDAO basketDAO = new BasketDAO(); //TODO static으로  올려 놓는게 맞나..? user id 는 이렇게 갖고 있어야 하는게 맞나?

    public void insertBasket(CustomerVO customerVO) {
        System.out.print("추가할 상품 ID를 입력해주세요 > ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        System.out.print("추가할 상품 개수를 입력해주세요 > ");
        int productCount = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        BasketVO basketVO = new BasketVO();
        basketVO.setProductId(productId);
        basketVO.setBasketProductCount(productCount);

        basketDAO.insertBasket(customerVO, basketVO);
    }

    public void selectBaskets(CustomerVO customerVO) {
        basketDAO.selectBaskets(customerVO);
    }

    public BasketVO selectOneBasket(BasketVO basketVO) {
        // vo 에 basketId set 된 상태여야해
        basketVO=basketDAO.selectOneBasket(basketVO);
        return basketVO;
    }
    public void updateBasket() {
        System.out.print("수정할 ID를 입력해주세요 > ");
        int basketId = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        System.out.print("수정할 상품 개수를 입력해주세요 > ");
        int productCount = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        BasketVO basketVO = new BasketVO();
        basketVO.setProductId(basketId);
        basketVO.setBasketProductCount(productCount);
        basketDAO.updateBasket(basketVO);
    }

    public void deleteBasket() {
        System.out.print("삭제할 ID를 입력해주세요 > ");
        int basketId = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        BasketVO basketVO = new BasketVO();
        basketVO.setBasketId(basketId);
        basketDAO.deleteBasket(basketVO);
    }
}
