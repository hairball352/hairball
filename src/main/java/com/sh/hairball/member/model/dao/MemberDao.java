package com.sh.hairball.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.hairball.member.model.exception.MemberException;
import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.member.model.vo.MemberRole;

public class MemberDao {
    private Properties prop = new Properties();

    public MemberDao(){
        String filename =
                MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
        try {
            prop.load(new FileReader(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Member findById(Connection conn, String memberId) {
        String sql = prop.getProperty("findById"); // select * from member where member_id = ?
        Member member = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, memberId);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    member = handleMemberResultSet(rset);
                    System.out.println("memberDao@member = " + member);
                }
            }
        } catch (SQLException e ){
            throw new MemberException(e);
        }
        
        return member;
    }

    private Member handleMemberResultSet(ResultSet rset) throws SQLException {
        String memberId = rset.getString("member_id");
        String password = rset.getString("password");
        String name = rset.getString("name");
        String email = rset.getString("email");
        String phone = rset.getString("phone");
        String address = rset.getString("address");
        MemberRole memberRole = MemberRole.valueOf(rset.getString("member_role"));
        return new Member(0,memberId, password, name, email, phone, address, memberRole, null);
    }

    public int insertMember(Connection conn, Member newMember) {
        int result = 0;
        String sql = "insert into member (password, name,member_role, email, phone, provider) value (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,"password");
            pstmt.setString(2,"name");
            pstmt.setString(3,"U");
            pstmt.setString(4, newMember.getEmail());
            pstmt.setString(5, newMember.getPhone());
            pstmt.setString(6, newMember.getProvider().toString());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new MemberException(e);
        }
        return result;
    }

    public int updateMember(Connection conn, Member member) {
        int result = 0;
        String sql = prop.getProperty("updateMember");
        // update member set name = ?, gender = ?, birthday = ?, email = ?, phone = ?, hobby = ? where member_id = ?
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getMemberId());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddress());
            result = pstmt.executeUpdate();
        } catch (SQLException e ) {
            throw new MemberException(e);
        }
        return result;
    }

    public int deleteMember(Connection conn, String memberId) {
        int result = 0;
        String sql = prop.getProperty("deleteMember");
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, memberId);
            result = pstmt.executeUpdate();
        } catch (SQLException e ) {
            throw new MemberException(e);
        }
        return result;
    }

    public List<Member> findAll(Connection conn) {
        List<Member> members = new ArrayList<>();
        String sql = prop.getProperty("findAll");

        try (
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rset = pstmt.executeQuery();
        ) {
            while (rset.next()) {
            	Member member = handleMemberResultSet(rset);
                members.add(member);
            }
        } catch (SQLException e ) {
            throw new MemberException(e);
        }
        return members;
    }


    public int updateMemberRole(Connection conn, String memberId, MemberRole memberRole) {
        int result = 0;
        String sql = prop.getProperty("updateMemberRole");
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, memberRole.name());
            pstmt.setString(2, memberId);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new MemberException(e);
        }
        return result;
    }



    public List<Member> searchMember(Connection conn, String searchType, String searchKeyword) {
        List<Member> members = new ArrayList<>();
        String sql = prop.getProperty("searchMember"); // select * from member where # like ?
        sql = sql.replace("#", searchType);
        System.out.println("sql@dao = " + sql);

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchKeyword + "%");
            try (ResultSet rset = pstmt.executeQuery()) {
                while(rset.next()) {
                	Member member = handleMemberResultSet(rset);
                    members.add(member);
                }
            }
        } catch (SQLException e) {
            throw new MemberException(e);
        }

        return members;
    }



}




