package src.dao;

import src.vo.BasketVO;
import src.vo.CustomerOrderVO;
import src.vo.CustomerVO;

public interface ICustomerOrderDAO {
    public int insertCustomerOrder(CustomerVO customerVO, BasketVO basketVO);
    public int selectCustomerOrderId(String customerId);
    public void selectCustomerOrder(String customerId);
    public int deleteCustomerOrder(CustomerOrderVO customerOrderVO);

}
