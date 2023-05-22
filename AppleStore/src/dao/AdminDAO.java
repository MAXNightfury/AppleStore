package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import src.dao.IAdminDAO;
import src.vo.AdminVO;
import src.vo.AppleStoreDataSource;

public class AdminDAO implements IAdminDAO {
    public Scanner scanner = null;

    @Override
    public int selectAdmin(String adminId, String adminPassword) {
        // 등록되어 있는 관리자 계정에 일치하는 개수가 1개인지 확인
        String sql = "select count(*) from admin where admin_id=? and admin_password=?";
        int rowCount = 0;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            connection = AppleStoreDataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, adminId);
            pstmt.setString(2, adminPassword);
            rs = pstmt.executeQuery();
            rs.next();
            rowCount = rs.getInt(1);
        } catch (SQLException e) {
            // runtimeException예외 던져라
            throw new RuntimeException();
        } finally {
            if (rs != null) // 연 반대 순서로 닫는다.
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
        return rowCount;
    }

}