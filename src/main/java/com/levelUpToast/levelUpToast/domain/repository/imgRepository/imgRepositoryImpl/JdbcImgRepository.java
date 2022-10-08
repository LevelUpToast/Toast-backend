package com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.data.img.ImgItem;
import com.levelUpToast.levelUpToast.domain.repository.imgRepository.imgRepositoryInf.ImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
@Primary
@Repository
@RequiredArgsConstructor
public class JdbcImgRepository implements ImgRepository {

    private final DataSource dataSource;

    @Override
    public ImgItem add(ImgItem imgItem) throws LevelUpToastEx {
        String sql = "insert into Image_TB (upload_file_name, store_file_name) values(?,?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, imgItem.getUploadFileName());
            pstmt.setString(2, imgItem.getStoreFileName());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                imgItem.setManageSeq(rs.getLong(1));
            }else {
                log.warn("[DB_Image_TB log] : DB_Image_TB manageSeq 생성 실패 imgItem = {} ", imgItem);
                throw new LevelUpToastEx("DB Image_TB manageSeq 반환 실패", 10101);
            }

        } catch (Exception e) {
            log.warn("[DB_Image_TB log] : DB Image_TB (add) 에러 imgItem = {} ", imgItem);
            throw new LevelUpToastEx("DB Member_TB (save) 에러", 10102);
        }finally {
            close(conn,pstmt,rs);
        }
        return imgItem;
    }

    @Override
    public ImgItem findBySeq(Long manageSeq) throws LevelUpToastEx {
        String sql = "select * from Image_TB where Image_TB.image_sequence = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, manageSeq);
            rs = pstmt.executeQuery();

            if(rs.next()){
                ImgItem imgItem = new ImgItem(
                        rs.getString("upload_file_name"),
                        rs.getString("store_file_name")
                );
                imgItem.setManageSeq(rs.getLong("image_sequence"));
                return imgItem;
            }else {
                log.warn("[DB_Image_TB log] : DB_Image_TB manageSeq 미존재 imgage_sequence = {} ", manageSeq);
                throw new LevelUpToastEx("DB Image_TB manageSeq 일치 이미지 없음", 10103);
            }

        } catch (Exception e) {
            log.warn("[DB_Image_TB log] : DB_Image_TB manageSeq 미존재 imgage_sequence = {} ", manageSeq);
            throw new LevelUpToastEx("DB Image_TB manageSeq 일치 이미지 없음", 10103);
        }finally {
            close(conn,pstmt,rs);
        }

    }

    @Override
    public ImgItem findByImgUUID(String imgName) throws LevelUpToastEx {
        String sql = "select * from Image_TB where Image_TB.store_file_name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,imgName);
            rs = pstmt.executeQuery();

            if(rs.next()){
                ImgItem imgItem = new ImgItem(
                        rs.getString("upload_file_name"),
                        rs.getString("store_file_name")
                );
                imgItem.setManageSeq(rs.getLong("image_sequence"));
                return imgItem;
            }else {
                log.warn("[DB_Image_TB log] : DB_Image_TB uuid 미존재 imgage_sequence = {} ", imgName);
                throw new LevelUpToastEx("DB Image_TB uuid 일치 이미지 없음", 10104);
            }

        } catch (Exception e) {
            log.warn("[DB_Image_TB log] : DB_Image_TB uuid 미존재 imgage_sequence = {} ", imgName);
            throw new LevelUpToastEx("DB Image_TB uuid 일치 이미지 없음", 10104);
        }finally {
            close(conn,pstmt,rs);
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
