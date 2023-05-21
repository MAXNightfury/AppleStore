package src.dao;

public class ProductDAO implements IPRoductDAO{
//    @Override
//    public ArrayList<ProductVO> getProductDAO() {
//        ArrayList<ProductVO> ProductList = new ArrayList<>();
//        String sql = "select prduct_id, category_id, product_name, product_price, product_image  from product";
//        Connection con = null;
//
//        try {
//            con = AppleStoreDataSource.getConnection();
//            PreparedStatement stmt = con.prepareStatement(sql);
//
//            ResultSet rs = stmt.executeQuery();
//            while(rs.next()) {
//                ProductVO pro = new ProductVO();
//                pro.setProductId(rs.getInt("prduct_id"));
//                pro.setCategoryId(rs.getInt("category_id"));
//                pro.setProductName(rs.getString("product_name"));
//                pro.setPrice(rs.getString("product_price"));
//                //날짜관련 함수
//                pro.setProductInputDate();
//                pro.setProductUpdateDate();
//                pro.setProducDeleteDate();
//                pro.setString(rs.getString("product_image"));
//                ProductList.add(pro);
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            AppleStoreDataSource.closeConnection(con);
//        }
//        return ProductList;
//    }
}
