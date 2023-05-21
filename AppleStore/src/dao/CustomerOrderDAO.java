package src.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import src.dao.ICustomerOrderDAO;
import src.vo.AppleStoreDataSource;
import src.vo.CustomerOrderVO;
import src.vo.CustomerVO;


public class CustomerOrderDAO implements ICustomerOrderDAO { // 이거하고있어

    public int insertCustomerOrder(CustomerVO customerVO) {
        return 0;
    }

    public int selectCustomerOrderId(String customerId) {
        String sql = "select co.order_id, co.customer_id, bs.product_id, bs.count"
                + "from customer_order co"
                + "join order_detail od on co.order_id = od.order_id"
                + "join basket bs on co.customer_id = bs.customer_id And od.product_id = bs.product_id and od.order_detail_product_count = bs. basket_product_count"
                + "where co.customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int coOrderId = 0;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                coOrderId = rs.getInt("co.order_id");
                return coOrderId;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return coOrderId;
    }

    public void selectCustomerOrder(String customerId) {
        int sum = 0;
        ResultSet rs = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            String sql = "select co.order_id, co.order_date, p.product_name, od.order_detail_product_count count, p.product_price price, count*price"
                    + "from customer_order co" + "join order_detail od on co.order_id = od.order_id"
                    + "join product p on od.product_id = p.product_id" + "where co.customer_id=?";
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("co.order_id");
                Date orderDate = rs.getDate("co.order_date");
                String productname = rs.getString("p.product");
                int count = rs.getInt("od.order_detail_product_count");
                int price = rs.getInt("p.product_price");
                sum = count * price;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = format.format(orderDate);
                System.out.printf("주문번호: %d\n 주문날짜: %d\\t, 주문상품이름: %s/n 총가격: %d/n", orderId, date, productname, sum);
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            AppleStoreDataSource.closeConnection(connection);
        }
    }

    public int deleteCustomerOrder(CustomerOrderVO customerOrderVO) { // 테스트 아직 안해봄
        int rowCount = 0;
        Connection connection = null;
        PreparedStatement pstmt = null;
        String sql = "update customer_order set order_status_id=?, order_delete_date=systimestamp where order_id=?";

        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "주문취소");
            pstmt.setInt(2, customerOrderVO.getOrderId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // runtimeException예외를 던저라
            throw new RuntimeException(e);
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


