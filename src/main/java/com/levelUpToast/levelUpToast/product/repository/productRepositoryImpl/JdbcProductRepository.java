package com.levelUpToast.levelUpToast.product.repository.productRepositoryImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.util.utilInf.adapter.Img.ImgAdapter;
import com.levelUpToast.levelUpToast.util.utilInf.adapter.product.ProductAdapter;
import com.levelUpToast.levelUpToast.product.domain.DataBaseProductTable;
import com.levelUpToast.levelUpToast.product.domain.ResponseProductTable;
import com.levelUpToast.levelUpToast.product.domain.data.buyoption.BuyOption;
import com.levelUpToast.levelUpToast.product.domain.data.fundinginfo.FundingInfo;
import com.levelUpToast.levelUpToast.product.domain.data.productinfo.ResponseProductInfo;
import com.levelUpToast.levelUpToast.product.domain.data.reviwe.Review;
import com.levelUpToast.levelUpToast.product.domain.data.tag.Tag;
import com.levelUpToast.levelUpToast.product.repository.productRepositoryInf.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@Primary
@RequiredArgsConstructor
public class JdbcProductRepository implements ProductRepository {

    private final ImgAdapter simpleImgAdapter;

    private final ProductAdapter productAdapter;
    private final DataSource dataSource;

    private DataBaseProductTable changeImgToSEQ(ResponseProductTable responseProductTable) throws LevelUpToastEx {
        return productAdapter.toProductSEQ(responseProductTable);
    }

    private ResponseProductTable changeImgToUUID(Long seq, DataBaseProductTable product) throws LevelUpToastEx {
       return productAdapter.toProductUUID(seq, product);
    }


    @Override
    public ResponseProductTable saveProduct(ResponseProductTable responseProductTable) throws LevelUpToastEx, SQLException {

        //Product_TB insert 구문
//        String sql = "insert into Product_TB (title, liked, member_sequence, initial_image_sequence) values(?,?,?,?)";
        DataBaseProductTable dataBaseProductTable = changeImgToSEQ(responseProductTable);


        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Savepoint savepoint1 = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Product_TB
            String sql1 = "insert into Product_TB (title, liked, member_sequence, initial_image_sequence,tag) values(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, dataBaseProductTable.getTitle());
            pstmt.setLong(2, dataBaseProductTable.getLike());
            pstmt.setLong(3, dataBaseProductTable.getVendorSeq());
            List<String> s = dataBaseProductTable.getInitialImgUrl().stream().map(Object::toString)
                    .collect(Collectors.toList());
            pstmt.setString(4, String.join(",", s));
            pstmt.setString(5, dataBaseProductTable.getTag().toString());

            savepoint1 = conn.setSavepoint("Savepoint1");
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                long product_sequence = rs.getLong(1);
                responseProductTable.setProductSeq(product_sequence);

                //product_information_TB
                String sql2 = "insert into Product_information_TB (product_sequence,product_information,product_info_image_sequence) values(?,?,?)";
                pstmt = conn.prepareStatement(sql2);
                pstmt.setLong(1, product_sequence);
                pstmt.setString(2, dataBaseProductTable.getProductInfo().getText());
                List<String> s2 = dataBaseProductTable.getProductInfo().getProductImgUrl().stream().map(Object::toString)
                        .collect(Collectors.toList());
                pstmt.setString(3, String.join(",", s2));
                pstmt.executeUpdate();

                // Product_funding_option_TB
                String sql3 = "insert into Product_funding_option_TB (product_sequence, option_number, option_information, option_price)";
                ArrayList<BuyOption> buyOption = dataBaseProductTable.getBuyOption();
                for (int i = 0; i < buyOption.size(); i++) {
                    if (i == 0) {
                        sql3 += " values(?,?,?,?)";
                    } else {
                        sql3 += ",(?,?,?,?)";
                    }
                }
                pstmt = conn.prepareStatement(sql3);
                int count = 1;
                for (BuyOption bo : buyOption) {
                    pstmt.setLong(count, rs.getLong(1));
                    count++;
                    pstmt.setLong(count, buyOption.indexOf(bo));
                    count++;
                    pstmt.setString(count, bo.getOptionInfo());
                    count++;
                    pstmt.setLong(count, bo.getOptionPrice());
                    count++;
                }
                log.info("sql3 : {}", sql3);
                pstmt.executeUpdate();


                // Product_funding_information_TB
                String sql4 = "insert into Product_funding_information_TB (product_sequence,current_amount,final_amount,deadline) values(?,?,?,?)";
                pstmt = conn.prepareStatement(sql4);
                pstmt.setLong(1, rs.getLong(1));
                pstmt.setLong(2, dataBaseProductTable.getFunding().getCurrentAmount());
                pstmt.setLong(3, dataBaseProductTable.getFunding().getFinalAmount());
                // deadline 은 date 형식으로 YYYY-MM-DD 형식 유지
                pstmt.setString(4, dataBaseProductTable.getFunding().getDeadline());
                pstmt.executeUpdate();

                //Product_review_TB
                String sql5 = "insert into Product_review_TB (star1, star2, star3, star4, star5, product_sequence) values(?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql5);
                pstmt.setLong(1, dataBaseProductTable.getReview().getStar1());
                pstmt.setLong(2, dataBaseProductTable.getReview().getStar2());
                pstmt.setLong(3, dataBaseProductTable.getReview().getStar3());
                pstmt.setLong(4, dataBaseProductTable.getReview().getStar4());
                pstmt.setLong(5, dataBaseProductTable.getReview().getStar5());
                pstmt.setLong(6, rs.getLong(1));
                pstmt.executeUpdate();
                conn.commit();
            }
            return responseProductTable;
        } catch (Exception e) {
            conn.rollback(savepoint1);
            log.warn("[DB_Product_TB log] : Product 저장 실패", dataBaseProductTable);
            log.info("Error ={}", e);
            throw new LevelUpToastEx("Product_TB 제품 등록 실패", 10002);


        } finally {
            close(conn, pstmt, rs);
        }

    }


    @Override
    public Optional<ResponseProductTable> findProductBySeq(Long productSeq) throws LevelUpToastEx, SQLException {

        String productSql =
                "select * from Product_TB, Product_funding_information_TB, Product_information_TB, Product_review_TB where Product_TB.product_sequence = ?" +
                " and Product_TB.product_sequence = Product_funding_information_TB.product_sequence" +
                " and Product_TB.product_sequence = Product_information_TB.product_sequence" +
                " and Product_TB.product_sequence = Product_review_TB.product_sequence;";

        String optionCountSql = "select count(*) from Product_funding_option_TB where product_sequence = ?;";
        String optionSql = "select * from Product_funding_option_TB where product_sequence = ? and option_number = ? ";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Savepoint savepoint1 = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(optionCountSql);
            pstmt.setLong(1, productSeq);

            savepoint1 = conn.setSavepoint("Savepoint1");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String optionCount = rs.getString(1);

                ArrayList<BuyOption> buyOptionList = new ArrayList<>();
                for (int i = 0; i < Integer.parseInt(optionCount); i++) {
                    pstmt = conn.prepareStatement(optionSql);
                    pstmt.setLong(1, productSeq);
                    pstmt.setLong(2, i);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        buyOptionList.add(new BuyOption(rs.getString("option_information"), rs.getInt("option_price")));
                    }
                }

                pstmt = conn.prepareStatement(productSql);
                pstmt.setLong(1, productSeq);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    List<Long> initial_image_sequence_list = new ArrayList<>();
                    List<Long> product_info_image_sequence_list = new ArrayList<>();
                    String[] initial_image_sequences = rs.getString("initial_image_sequence").split(",");
                    String[] product_info_image_sequences = rs.getString("product_info_image_sequence").split(",");
                    for (String s : initial_image_sequences)
                        initial_image_sequence_list.add(Long.valueOf(s));
                    for (String s : product_info_image_sequences)
                        product_info_image_sequence_list.add(Long.valueOf(s));


                    String tagString = rs.getString("tag");
                    Tag tag;
                    if (tagString.equals("FRUIT")) {
                        tag = Tag.FRUIT;
                    } else if (tagString.equals("VEGETABLE")) {
                        tag = Tag.VEGETABLE;
                    } else {
                        tag = null;
                    }


                    ResponseProductTable responseProductTable = new ResponseProductTable(
                            rs.getString("title"),
                            simpleImgAdapter.extractImgUUID(initial_image_sequence_list),
                            tag,
                            new FundingInfo(rs.getInt("current_amount"), rs.getInt("final_amount"), rs.getString("deadline")),
                            rs.getInt("liked"),
                            rs.getLong("member_sequence"),
                            new ResponseProductInfo(rs.getString("product_information"), simpleImgAdapter.extractImgUUID(product_info_image_sequence_list)),
                            buyOptionList,
                            new Review(rs.getInt("star5"), rs.getInt("star4"), rs.getInt("star3"), rs.getInt("star2"), rs.getInt("star1")));
                    responseProductTable.setProductSeq(productSeq);

                    return Optional.of(responseProductTable);
                }
            }

        } catch (Exception e) {
            conn.rollback(savepoint1);
            log.info("error = {}", e);
            log.warn("[DB_Product_TB log] : Product (findProductBySeq) 에러 productSeq ={}", productSeq);
            throw new LevelUpToastEx("Product_TB 제품 찾기 실패", 10002);
        } finally {
            close(conn, pstmt, rs);
        }

        return Optional.empty();
    }

    @Override
    public List<ResponseProductTable> findProductByTag(Tag tag) throws LevelUpToastEx {

        String sql = "select product_sequence from Product_TB where tag = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tag.toString());
            rs = pstmt.executeQuery();
            List<ResponseProductTable> responseProductTables = new ArrayList<>();
            while (rs.next()) {
                responseProductTables.add(findProductBySeq(rs.getLong(1)).get());
            }
            return responseProductTables;

        } catch (Exception e) {
            log.warn("[DB_Product_TB log] : Product (findProductByTag) 에러 tag ={}", tag.toString());
            throw new LevelUpToastEx("Product_TB Tag를 통한 제품 찾기 실패", 10002);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<ResponseProductTable> findProductByTitle(String title) throws LevelUpToastEx {

        String sql = "select product_sequence from Product_TB where title like'%" + title + "%';";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, title);
            rs = pstmt.executeQuery();
            List<ResponseProductTable> responseProductTables = new ArrayList<>();
            while (rs.next()) {
                responseProductTables.add(findProductBySeq(rs.getLong(1)).get());
            }
            return responseProductTables;

        } catch (Exception e) {
            log.warn("[DB_Product_TB log] : Product (findProductByTitle) 에러 title ={}", title);
            throw new LevelUpToastEx("Product_TB title을 통한 제품 찾기 실패", 10002);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public ArrayList<ResponseProductTable> findAllProduct() throws LevelUpToastEx {

        String sql = "select product_sequence from Product_TB";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ArrayList<ResponseProductTable> responseProductTables = new ArrayList<>();
            while (rs.next()) {
                responseProductTables.add(findProductBySeq(rs.getLong(1)).get());
            }
            return responseProductTables;
        } catch (Exception e) {
            log.warn("[DB_Product_TB log] : Product (findAllProduct) 에러 ");
            throw new LevelUpToastEx("Product_TB findAllProduct 에러", 10002);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void updateProduct(Long productSeq, ResponseProductTable newResponseProductTable) throws LevelUpToastEx, SQLException {
        String sql = "UPDATE Reservation SET RoomNum = 2002 WHERE Name = '홍길동';";

        DataBaseProductTable dataBaseProductTable = changeImgToSEQ(newResponseProductTable);


        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Savepoint savepoint1 = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            // Product_TB
            String sql1 = "update Product_TB set title = ?, liked = ?, member_sequence = ?, initial_image_sequence = ?,tag = ? where product_sequence = ?";
            pstmt = conn.prepareStatement(sql1);

            pstmt.setString(1, dataBaseProductTable.getTitle());
            pstmt.setLong(2, dataBaseProductTable.getLike());
            pstmt.setLong(3, dataBaseProductTable.getVendorSeq());
            List<String> s = dataBaseProductTable.getInitialImgUrl().stream().map(Object::toString)
                    .collect(Collectors.toList());
            pstmt.setString(4, String.join(",", s));
            pstmt.setString(5, dataBaseProductTable.getTag().toString());
            pstmt.setLong(6, productSeq);

            savepoint1 = conn.setSavepoint("Savepoint1");
            pstmt.executeUpdate();


            //product_information_TB
            String sql2 = "update Product_information_TB set product_information = ? ,product_info_image_sequence = ?, where product_sequence = ? ";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, dataBaseProductTable.getProductInfo().getText());
            List<String> s2 = dataBaseProductTable.getProductInfo().getProductImgUrl().stream().map(Object::toString)
                    .collect(Collectors.toList());
            pstmt.setString(2, String.join(",", s2));
            pstmt.setLong(3, productSeq);
            pstmt.executeUpdate();

            // Product_funding_option_TB
            String sql3 = "delete from Product_funding_option_TB where product_sequence = ?";
            String sql4 = "insert into Product_funding_option_TB (product_sequence, option_number, option_information, option_price)";
            pstmt = conn.prepareStatement(sql3);
            pstmt.setLong(1, productSeq);
            pstmt.executeUpdate();

            ArrayList<BuyOption> buyOption = dataBaseProductTable.getBuyOption();
            for (int i = 0; i < buyOption.size(); i++) {
                if (i == 0) {
                    sql3 += " values(?,?,?,?)";
                } else {
                    sql3 += ",(?,?,?,?)";
                }
            }
            pstmt = conn.prepareStatement(sql4);
            int count = 1;
            for (BuyOption bo : buyOption) {
                pstmt.setLong(count, rs.getLong(1));
                count++;
                pstmt.setLong(count, buyOption.indexOf(bo));
                count++;
                pstmt.setString(count, bo.getOptionInfo());
                count++;
                pstmt.setLong(count, bo.getOptionPrice());
                count++;
            }
            pstmt.executeUpdate();


            // Product_funding_information_TB
            String sql5 = "update Product_funding_information_TB set current_amount = ?,final_amount =?, deadline =? where product_sequence =?";
            pstmt = conn.prepareStatement(sql5);
            pstmt.setLong(1, dataBaseProductTable.getFunding().getCurrentAmount());
            pstmt.setLong(2, dataBaseProductTable.getFunding().getFinalAmount());
            // deadline 은 date 형식으로 YYYY-MM-DD 형식 유지
            pstmt.setString(3, dataBaseProductTable.getFunding().getDeadline());
            pstmt.setLong(4, productSeq);
            pstmt.executeUpdate();

            //Product_review_TB
            String sql6 = "update Product_review_TB set star1 = ?, star2 = ? , star3 = ?, star4 = ?, star5 = ? where product_sequence = ?";
            pstmt = conn.prepareStatement(sql6);
            pstmt.setLong(1, dataBaseProductTable.getReview().getStar1());
            pstmt.setLong(2, dataBaseProductTable.getReview().getStar2());
            pstmt.setLong(3, dataBaseProductTable.getReview().getStar3());
            pstmt.setLong(4, dataBaseProductTable.getReview().getStar4());
            pstmt.setLong(5, dataBaseProductTable.getReview().getStar5());
            pstmt.setLong(6, productSeq);
            pstmt.executeUpdate();
            conn.commit();

        } catch (Exception e) {
            conn.rollback(savepoint1);
            log.warn("[DB_Product_TB log] : Product update error productSeq = {}", productSeq);
            throw new LevelUpToastEx("Product update error productSeq ", 10002);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public void removeProductBySeq(Long productSeq) throws LevelUpToastEx {

        String sql1 = "delete from Product_TB where product_sequence = ?";
        String sql2 = "delete from Product_funding_option_TB where product_sequence = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setLong(1, productSeq);
            pstmt.executeUpdate();

            pstmt = conn.prepareStatement(sql2);
            pstmt.setLong(1, productSeq);
            pstmt.executeUpdate();
        } catch (Exception e) {
            log.warn("[DB_Product_TB log] : Product (removeProductBySeq) 에러 productSeq = {} ", productSeq);
            throw new LevelUpToastEx("Product_TB removeProductBySeq 에러", 10002);
        } finally {
            close(conn, pstmt, rs);
        }

    }

    // DB connect
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    // DB close
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    // DB close
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                close(conn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
