package src.dao;

import src.vo.AppleStoreDataSource;
import src.vo.CustomerVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO implements ICustomerDAO {
    static boolean isConfirmedPassword = false;



    public CustomerVO customerLogin(CustomerVO customerVO) {
        int count = 0;
        String sql = "select customer_id, customer_delete_date from customer where customer_id = ? and customer_password=?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            pstmt.setString(2, customerVO.getCustomerPassword());
            rs = pstmt.executeQuery();

            // vo 를 여기서 새로 하나 만들까 아니면 위에 있는거에 덮어 쓸까?
            // 일단 위에 있는거 덮어써봄
            if (rs.next()) {
                customerVO.setCustomerId(rs.getString("customer_id"));
                customerVO.setCustomerDeleteDate(rs.getDate("customer_delete_date"));
                return customerVO;
            }else{
                return null;
            }
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


    }


    public int customerJoin(CustomerVO customerVO) {
        int count = 0;
        String sql = "insert into customer (customer_id, customer_password, customer_name, customer_phone_number, customer_address, customer_born_date, customer_sex,customer_join_date) " +
                "values(?,?, ?, ?, ?, ?, ?, systimestamp)";

        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            pstmt.setString(2, customerVO.getCustomerPassword());
            pstmt.setString(3, customerVO.getCustomerName());
            pstmt.setInt(4, customerVO.getCustomerPhoneNumber());
            pstmt.setString(5, customerVO.getCustomerAddress());
            pstmt.setDate(6, customerVO.getCustomerBornDate());
            pstmt.setString(7, customerVO.getCustomerSex());
            count = pstmt.executeUpdate();
//			finally 에서 connection 닫으면 같이 반납 되니까 안닫아도 된다.
//			pstmt.close();

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

        return count;
    }

    public boolean checkCustomerPassword(CustomerVO customerVO) { // 유저가 개인정보를 변경할 때 기존 비밀번호를 확인하는 것
        int count = 0;
        String sql = "select customer_id,customer_password from customer where customer_id = ? and customer_password=?";

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            pstmt.setString(2, customerVO.getCustomerPassword());
            count = pstmt.executeUpdate(); //TODO executeQuery 로 바꿔봐

            if (count == 1) {
                isConfirmedPassword = true;
            } else {
                isConfirmedPassword = false;
            }

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
        return isConfirmedPassword;
    }


    public int customerUpdatePassword(CustomerVO customerVO) {
        int count = 0;
        String sql = "  update customer set customer_password =?, customer_update_date = systimestamp where customer_id=?";

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, customerVO.getCustomerPassword());
            pstmt.setString(2, customerVO.getCustomerId());
            count = pstmt.executeUpdate();

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

        return count;
    }

    public int customerUpdateName(CustomerVO customerVO) {
        int count = 0;

        String sql = "  update customer set customer_name =?, customer_update_date = systimestamp where customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, customerVO.getCustomerName());
            pstmt.setString(2, customerVO.getCustomerId());
            count = pstmt.executeUpdate();

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

        return count;
    }

    public int customerUpdatePhoneNumber(CustomerVO customerVO) {
        int count = 0;

        String sql = "  update customer set customer_Phone_number =?, customer_update_date = systimestamp where customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, customerVO.getCustomerPhoneNumber());
            pstmt.setString(2, customerVO.getCustomerId());
            count = pstmt.executeUpdate();

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

        return count;
    }

    public int customerUpdateAddress(CustomerVO customerVO) {
        int count = 0;

        String sql = "  update customer set customer_address =?, customer_update_date = systimestamp where customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, customerVO.getCustomerAddress());
            pstmt.setString(2, customerVO.getCustomerId());
            count = pstmt.executeUpdate();

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

        return count;
    }

    public int customerDelete(CustomerVO customerVO) {
        int count = 0;
        // 회원 탈퇴 날짜만 박으면 됨  로그인에 탈퇴된 회원인지 확인하는 로직 있어야할 듯?
        String sql = "  update customer set customer_delete_date =systimestamp where customer_id=?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerVO.getCustomerId());
            count = pstmt.executeUpdate();

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

        return count;
    }
}
