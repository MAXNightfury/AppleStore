package src.dao;

import src.vo.BasketVO;
import src.vo.CustomerOrderJoinProductVO;
import src.vo.CustomerOrderVO;
import src.vo.CustomerVO;

import java.util.ArrayList;

public interface ICustomerOrderDAO {
    public int insertCustomerOrder(CustomerVO customerVO, BasketVO basketVO, int orderBundleId);

    public ArrayList<CustomerOrderJoinProductVO> selectCustomerOrder(CustomerVO customerVO);

    public int deleteCustomerOrder(CustomerOrderVO customerOrderVO);
    public int updateOrderStatusId(CustomerOrderVO customerOrderVO);
    public ArrayList<CustomerOrderJoinProductVO> selectCustomerOrderByCustomerIdCanDelete(CustomerVO customerVO);

    public ArrayList<CustomerOrderJoinProductVO> selectCustomerOrderIdByBundleIdCanDelete(CustomerOrderVO customerOrderVO);
}
