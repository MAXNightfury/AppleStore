package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import src.vo.*;

public class ProductDAO implements IProductDAO {
    public static Scanner scanner = new Scanner(System.in);

    @Override
    public int insertProduct(ProductVO productVO) {
        String sql = "INSERT INTO product (product_id, product_name, category_id, product_price, product_image, product_count, product_input_date) "
                + "VALUES (Applestore_seq.NEXTVAL, ?, ?, ?, ?, ?, systimestamp)";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, productVO.getProductName());
            pstmt.setInt(2, productVO.getCategoryId());
            pstmt.setInt(3, productVO.getProductPrice());
            pstmt.setString(4, productVO.getProductImage());
            pstmt.setInt(5, productVO.getProductCount());
            rowCount = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return rowCount;
    }

    @Override
    public ProductVO selectProductByBasket(ProductVO productVO) {
        String sql = "select product_id, product_count  from product where product_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, productVO.getProductId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                productVO.setProductId(rs.getInt("product_id"));
                productVO.setProductCount(rs.getInt("product_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return productVO;
    }

    @Override
    public ArrayList<ProductVO> selectCategoryProduct(ProductVO productVO) {
        String sql = "select product_id, category_id, product_name, product_price, product_input_date, product_image , product_count "
                + "from product "
                + "where category_id=? and product_delete_date is null";
        // 어래이에 담아야지
        ArrayList<ProductVO> categoryProductArr = new ArrayList<ProductVO>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, productVO.getCategoryId());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                productVO = new ProductVO();
                productVO.setProductId(rs.getInt("product_id"));
                productVO.setCategoryId(rs.getInt("category_id"));
                productVO.setProductName(rs.getString("product_name"));
                productVO.setProductPrice(rs.getInt("product_price"));
                productVO.setProductInputDate(rs.getDate("product_input_date"));
                productVO.setProductImage(rs.getString("product_image"));
                productVO.setProductCount(rs.getInt("product_count"));
                categoryProductArr.add(productVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return categoryProductArr;
    }

    @Override
    public ArrayList<ProductVO> selectPopularProduct() { // TODO 해수 테스트 테이블 밀고 해봐
        String sql = "select p.product_id, p.category_id, p.product_name, p.product_price, p.product_count, " +
                "(select sum(order_product_count) from customer_order group by customer_order.product_id) as sum " +
                "from product p " +
                "left outer join customer_order od on p.product_id = od.product_id " +
                "where p.product_delete_date is null " +
                "order by sum";

        ArrayList<ProductVO> popularProductArr = new ArrayList<ProductVO>();
        ProductVO productVO = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int count = 0;

            while (rs.next()) {
                productVO = new ProductVO();
                productVO.setProductId(rs.getInt("product_id"));
                productVO.setCategoryId(rs.getInt("category_id"));
                productVO.setProductName(rs.getString("product_name"));
                productVO.setProductPrice(rs.getInt("product_price"));
                productVO.setProductCount(rs.getInt("product_count"));
                productVO.setTotalOrderCount(rs.getInt("sum"));
                popularProductArr.add(productVO);
//
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return popularProductArr;
    }

    @Override
    public ArrayList<ProductVO> selectLowPriceProduct() {
        String sql = "select product_id, category_id, product_name, product_price, product_input_date, product_image , product_count "
                + "from product "
                + "where product_delete_date is null "
                + "order by product_price";
        ArrayList<ProductVO> lowPriceProductArr = new ArrayList<ProductVO>();
        ProductVO productVO = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                productVO = new ProductVO();
                productVO.setProductId(rs.getInt("product_id"));
                productVO.setCategoryId(rs.getInt("category_id"));
                productVO.setProductName(rs.getString("product_name"));
                productVO.setProductPrice(rs.getInt("product_price"));
                productVO.setProductInputDate(rs.getDate("product_input_date"));
                productVO.setProductImage(rs.getString("product_image"));
                productVO.setProductCount(rs.getInt("product_count"));
                lowPriceProductArr.add(productVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return lowPriceProductArr;
    }

    @Override
    public ArrayList<ProductVO> selectHighPriceProduct() {
        String sql = "select product_id, category_id, product_name, product_price, product_input_date, product_image , product_count "
                + "from product "
                + "where product_delete_date is null "
                + "order by product_price desc";
        ArrayList<ProductVO> highPriceProductArr = new ArrayList<ProductVO>();
        ProductVO productVO = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                productVO = new ProductVO();
                productVO.setProductId(rs.getInt("product_id"));
                productVO.setCategoryId(rs.getInt("category_id"));
                productVO.setProductName(rs.getString("product_name"));
                productVO.setProductPrice(rs.getInt("product_price"));
                productVO.setProductInputDate(rs.getDate("product_input_date"));
                productVO.setProductImage(rs.getString("product_image"));
                productVO.setProductCount(rs.getInt("product_count"));
                highPriceProductArr.add(productVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return highPriceProductArr;
    }

    @Override
    public int updateProduct(ProductVO productVO) {
        String sql = "update product set " +
                "product_name = ?, " +
                "category_id = ?, " +
                "product_count = ?, " +
                "product_price = ?, " +
                "product_update_date = systimestamp " +
                "where product_id = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;

        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, productVO.getProductName());
            pstmt.setInt(2, productVO.getCategoryId());
            pstmt.setInt(3, productVO.getProductPrice());
            pstmt.setInt(4, productVO.getProductCount());
            pstmt.setInt(5, productVO.getProductId());
            rowCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return rowCount;
    }

    @Override
    public int updateProductCountIncrease(CustomerOrderVO customerOrderVO) {
        String sql = "update product set product_count = product_count+( select order_product_count from customer_order where order_id= ? ) " +
                "where product_id=( select product_id from customer_order where order_id= ? )";
        int rowCount = 0;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, customerOrderVO.getOrderId());
            pstmt.setInt(2, customerOrderVO.getOrderId());
            rowCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            AppleStoreDataSource.closeConnection(connection);
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return rowCount;
    }

    @Override
    public int updateProductCountDecrease(BasketVO basketVO) {
        int rowCount = 0;
        String sql = "update product set product_count = product_count-( select basket_product_count from basket where basket_id= ? ) " + // 니 왜 안돌아가
                "where product_id=( select product_id from basket where basket_id= ? )";

        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            int basketId = basketVO.getBasketId();
            System.out.println(basketId);
            pstmt.setInt(1, basketId);
            pstmt.setInt(2, basketId);
            rowCount = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        System.out.println("rowCount " + rowCount);
        return rowCount;
    }

    @Override
    public int deleteProduct(ProductVO productVO) {
        String sql = "update product set product_delete_date = systimestamp where product_id = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, productVO.getProductId());
            rowCount = pstmt.executeUpdate();
            System.out.println(rowCount);
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            if (pstmt != null)
                try {
                    pstmt.close();
                } catch (Exception e) {
                }
            AppleStoreDataSource.closeConnection(connection);
        }
        return rowCount;
    }
}






