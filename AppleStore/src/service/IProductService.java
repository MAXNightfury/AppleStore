package src.service;

import src.vo.BasketVO;
import src.vo.CustomerOrderVO;
import src.vo.ProductVO;

public interface IProductService {
    public void insertProduct();

    public void selectCategoryProduct(ProductVO productVO);

    public void selectPopularProduct();

    public void selectLowPriceProduct();

    public void selectHighPriceProduct();

    public void updateProduct();


    public void deleteProduct();
}
