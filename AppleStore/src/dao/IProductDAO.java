package src.dao;

import src.vo.BasketVO;
import src.vo.CustomerOrderVO;
import src.vo.ProductVO;

public interface IProductDAO {
    public int insertProduct(ProductVO productVO);
    public void selectCategoryProduct(int categoryId);
    public ProductVO selectProductByBasket(ProductVO productVO) ;
    public void selectPopularProduct();
    public void selectlowPriceProduct();
    public void selectHighPriceProduct();
    public int updateProduct(ProductVO productVO);
    public int updateProductCountIncrease(CustomerOrderVO customerOrderVO);
    public int updateProductCountDecrease(BasketVO basketVO);
    public int deleteProduct(ProductVO productVO);

}


