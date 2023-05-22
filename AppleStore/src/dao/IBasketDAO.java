package src.dao;

import src.vo.BasketVO;
import src.vo.CustomerVO;

public interface IBasketDAO {
    public int insertBasket(CustomerVO customerVO, BasketVO basketVO);
    public void selectBasket(CustomerVO customerVO);
    public int updateBasket(BasketVO basketVO);
    public int deleteBasket(BasketVO basketVO);
}
