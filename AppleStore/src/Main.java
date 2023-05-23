package src;

import src.service.*;
import src.vo.CustomerVO;
import src.vo.ProductVO;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static CustomerVO customerVO =new CustomerVO();

    // g해수 -----------------------------------------
    // customer --------------------------------------
    public void customerJoin() {
        CustomerService customerService = new CustomerService(); // TODO 이렇게 매번 객체를 새로 생성하는게 맞나?
        customerService.customerJoin();
    }

    public void customerLogin() {
        CustomerService customerService = new CustomerService();
        customerService.customerLogin();
    }

    public void cunstomerPasswordUpdate() {
        CustomerService customerService = new CustomerService();
        // 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
        customerVO.setCustomerId("test");
        customerService.customerUpdatePassword(customerVO);
    }

    public void customerNameUpdate() {
        CustomerService customerService = new CustomerService();
        // 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
        customerVO.setCustomerId("test");
        customerService.customerUpdateName(customerVO);
    }

    public void customerPhoneNumberUpdate() {
        CustomerService customerService = new CustomerService();
        // 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
        customerVO.setCustomerId("test");
        customerService.customerUpdatePhoneNumber(customerVO);
    }

    public void customerUpdateAddress() {
        CustomerService customerService = new CustomerService();
        // 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
        customerVO.setCustomerId("test");
        customerService.customerUpdateAddress(customerVO);
    }

    public void customerDelete() {
        CustomerService customerService = new CustomerService();
        // 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
        customerVO.setCustomerId("test");
        customerService.customerDelete(customerVO);
    }
    // customer end--------------------------------------

    // productCategory --------------------------------------
    public void insertProductCategory() {
        ProductCategoryService productCategoryService = new ProductCategoryService();
        productCategoryService.insertProductCategory();
    }

    public static void selectAllProductCategory() {
        ProductCategoryService productCategoryService = new ProductCategoryService();
        productCategoryService.selectAllProductCategory();
    }

    public void updateProductCategory() {
        ProductCategoryService productCategoryService = new ProductCategoryService();
        productCategoryService.updateProductCategory();
    }

    public void deleteProductCategory() {
        ProductCategoryService productCategoryService = new ProductCategoryService();
        productCategoryService.deleteProductCategory();
    }
    // productCategory end --------------------------------------


    // basket ---------------------------------
    public static void insertBasket() {
        BasketService basketService = new BasketService();
        customerVO.setCustomerId("test");
        // 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함

        basketService.insertBasket(customerVO);
    }
    public static void selectBaskets() {
        BasketService basketService = new BasketService();
        // 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
        customerVO.setCustomerId("test");
        basketService.selectBaskets(customerVO);
    }
    public static void updateBasket() {
        BasketService basketService = new BasketService();
        basketService.updateBasket();
    }

    public static void deleteBasket() {
        BasketService basketService = new BasketService();
        basketService.deleteBasket();
    }
    public static void deleteAllBaskets() {
        BasketService basketService = new BasketService();
        customerVO.setCustomerId("test");
        basketService.deleteAllBaskets(customerVO);
    }

    // basket end ---------------------------------

    // customerOrder -------------------------------------------
    public static void insertCustomerOrder() {
        CustomerVO customerVO = new CustomerVO();
        CustomerOrderService customerOrderService = new CustomerOrderService();
        // 로그인 했을 때 고객 아이디를 전역으로 저장하고 있어야 함
        customerVO.setCustomerId("test");
        customerOrderService.insertCustomerOrder(customerVO);
    }
    public static void selectCustomerOrder() {
        CustomerVO customerVO = new CustomerVO();
        CustomerOrderService customerOrderService = new CustomerOrderService();
        customerVO.setCustomerId("test");
        customerOrderService.selectCustomerOrder(customerVO);
    }
    public static void  deleteCustomerOrder(){
        CustomerVO customerVO = new CustomerVO();
        CustomerOrderService customerOrderService = new CustomerOrderService();
        customerVO.setCustomerId("test");
        customerOrderService.deleteCustomerOrder(customerVO);
    }

    // customerOrder end -------------------------------------------
    //product -----------------------------------
    public static void  selectCategoryProduct(){
        ProductService productService = new ProductService();
        ProductVO productVO = new ProductVO();
        productVO.setCategoryId(1);
        productService.selectCategoryProduct(productVO);
    }
    public static void  selectPopularProduct(){
        ProductService productService = new ProductService();
        productService.selectPopularProduct();
    }
    //product end-----------------------------
    public static void main(String[] args) {
//        insertBasket();
//        deleteBasket();
//        deleteAllBaskets();
        selectPopularProduct();
    }

}