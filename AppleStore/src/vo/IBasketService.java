package src.vo;

public interface IBasketService {
    public void insertBasket(CustomerVO customerVO);

    public void selectBaskets(CustomerVO customerVO);

    public BasketVO selectOneBasket(BasketVO basketVO);

    public void updateBasket();

    public void deleteBasket();

    public void deleteAllBaskets(CustomerVO custmoerVO);
}
