package com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryImpl;

import com.levelUpToast.levelUpToast.config.exception.LevelUpToastEx;
import com.levelUpToast.levelUpToast.domain.model.member.Authority;
import com.levelUpToast.levelUpToast.domain.model.member.Member;
import com.levelUpToast.levelUpToast.domain.repository.memberRepository.memberRepositoryInf.MemberRepository;
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


@Slf4j
//@Primary
//@Repository
@RequiredArgsConstructor
public class JdbcMemberRepository implements MemberRepository {

    private final DataSource dataSource;

    @Override
    public Member save(Member member) throws LevelUpToastEx {

        String sql = "insert into Member_TB (auth, name, gender, tel, e_mail, address, locked) values(?,?,?,?,?,?,?)";

        String sql2 = "insert into Login_TB (id, pw, member_sequence) values(?,?,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Connection conn2 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;

        member.setLock(false);
        try {

            conn = getConnection();
            conn2 = getConnection();

            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt2 = conn2.prepareStatement(sql2);

            pstmt.setString(1, member.getAuthority().toString());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getGender());
            pstmt.setString(4, member.getPhoneNumber());
            pstmt.setString(5, member.getE_mail());
            pstmt.setString(6, member.getAddress());
            pstmt.setString(7, member.getLock().toString());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                member.setManageSeq(rs.getLong(1));
            } else {
                log.warn("[DB_Member_TB log] : Member_TB manageSeq 생성 실패 Member = {} ", member);
                throw new LevelUpToastEx("Member_TB manageSeq 반환 실패", 10000);
            }

            pstmt2.setString(1, member.getId());
            pstmt2.setString(2, member.getPassword());
            pstmt2.setLong(3,member.getManageSeq());

            log.info("nen = {}", member);

            pstmt2.executeUpdate();

            log.info("mem = {}", member);

            log.info("[DB_Member_TB log] : Member_TB {} 계정 , ID = {} 저장 성공", member.getAuthority(), member.getId());
            return member;
        } catch (Exception e) {
            log.warn("[DB_Member_TB log] : DB Member_TB (save) 에러 Member = {} ", member);
            throw new LevelUpToastEx("DB Member_TB (save) 에러", 10000);
        } finally {
            close(conn, pstmt, rs);
            close(conn2,pstmt2,rs2);
        }

    }


    @Override
    public Optional<Member> findByManageSeq(Long manageSeq) throws LevelUpToastEx {

        String sql = "select * from Member_TB inner join Login_TB on Member_TB.member_sequence = Login_TB.member_sequence where Member_TB.member_sequence = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, manageSeq);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = new Member(
                        rs.getString("id"),
                        rs.getString("pw"),
                        Authority.MEM,
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("tel"),
                        rs.getString("e_mail"),
                        rs.getString("address")
                );
                member.setManageSeq(rs.getLong("member_sequence"));
                if (rs.getString("auth").equals("admin")) {
                    member.setAuthority(Authority.ADMIN);
                    return Optional.of(member);
                } else if (rs.getString("auth").equals("member")) {
                    return Optional.of(member);
                } else {
                    log.warn("[DB_Member_TB log] : Member_TB Authority 매칭 오류 ID = {}", member.getId());
                    throw new LevelUpToastEx("Member_TB Authority 매칭 오류", 10001);
                }
            }
            return Optional.empty();

        } catch (Exception e) {
            log.warn("[DB_Member_TB log] : Member_TB 에러 ");
            throw new LevelUpToastEx("DB Member_TB (findByManageSeq) 에러", 10001);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAllMember() throws LevelUpToastEx {

        String sql = "select * from Member_TB inner join Login_TB on Member_TB.member_sequence = Login_TB.member_sequence";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while(rs.next()){
                Member member = new Member(
                        rs.getString("id"),
                        rs.getString("pw"),
                        Authority.MEM,
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("tel"),
                        rs.getString("e_mail"),
                        rs.getString("address")
                );
                member.setManageSeq(rs.getLong("member_sequence"));
                if(rs.getString("auth").equals("admin")){
                    member.setAuthority(Authority.ADMIN);
                }
                member.setManageSeq(rs.getLong("manage_sequence"));
                members.add(member);
            }
            return members;

        }catch (Exception e){
            throw new LevelUpToastEx("DB Member_TB (findAllMember) 에러", 10001);
        }finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByloginId(String loginId) throws LevelUpToastEx {
        String sql = "select * from Member_TB inner join Login_TB on Member_TB.member_sequence = Login_TB.member_sequence where Login_TB.id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,loginId);
            rs = pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member(
                        rs.getString("id"),
                        rs.getString("pw"),
                        Authority.MEM,
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("tel"),
                        rs.getString("e_mail"),
                        rs.getString("address")
                );
                member.setManageSeq(rs.getLong("member_sequence"));
                if(rs.getString("auth").equals("admin")){
                    member.setAuthority(Authority.ADMIN);
                }
                return Optional.of(member);
            }
            return Optional.empty();

        }catch (Exception e){
            throw new LevelUpToastEx("DB Member_TB (findByLoginId) 에러", 10001);
        }finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Member update(Long memberSeq, Member updatedMember) throws LevelUpToastEx {

        String sql = "update Member_TB inner join Login_TB on Member_TB.member_sequence = Login_TB.member_sequence" +
                " set Login_TB.id = ?, Login_TB.pw = ?, Member_TB.auth = ? , Member_TB.gender = ?, Member_TB.tel = ?, Member_TB.e_mail = ?, Member_TB.address = ?  " +
                "where Member_TB.member_sequence = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, updatedMember.getId());
            pstmt.setString(2, updatedMember.getPassword());
            pstmt.setString(3, updatedMember.getAuthority().toString());
            pstmt.setString(4, updatedMember.getGender());
            pstmt.setString(5, updatedMember.getPhoneNumber());
            pstmt.setString(6, updatedMember.getE_mail());
            pstmt.setString(7, updatedMember.getAddress());
            pstmt.setLong(8,memberSeq);
            rs = pstmt.executeQuery();
        }catch (Exception e){
            throw new LevelUpToastEx("DB Member_TB (update) 에러", 10001);
        }finally {
            close(conn, pstmt, rs);
        }
        return updatedMember;
    }

    @Override
    public void remove(Long manageSeq) throws LevelUpToastEx {
        String sql = "delete from Member_TB where member_sequence = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,manageSeq);
            rs = pstmt.executeQuery();
        }catch (Exception e){
            throw new LevelUpToastEx("DB Member_TB (remove) 에러", 10001);
        }finally {
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
