package src.dao;

import src.vo.CustomerOrderVO;
import src.vo.ProductVO;

public interface IProductDAO {
    public int insertProduct(ProductVO productVO);
    public void selectCategoryProduct(int categoryId);
    public void selectPopularProduct();
    public void selectlowPriceProduct();
    public void selectHighPriceProduct();
    public int updateProduct(ProductVO productVO);
    public int updateProductCountIncrease(CustomerOrderVO customerOrderVO);
    public int updateProductCountDecrease(CustomerOrderVO customerOrderVO);
    public int deleteProduct(ProductVO productVO);

}


