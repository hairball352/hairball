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
import com.sh.hairball.member.model.vo.Provider;

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
                }
            }
        } catch (SQLException e ){
            throw new MemberException(e);
        }
        
        return member;
    }

    private Member handleMemberResultSet(ResultSet rset) throws SQLException {
    	int id = rset.getInt("id");
        String memberId = rset.getString("member_id");
        String password = rset.getString("password");
        String name = rset.getString("name");
        String email = rset.getString("email");
        String phone = rset.getString("phone");
        String address = rset.getString("address");
        MemberRole memberRole = MemberRole.valueOf(rset.getString("member_role"));
        return new Member(id, memberId, password, name, email, phone, address, memberRole, null);
    }
    

    public int insertMember(Connection conn, Member newMember) {
        int result = 0;
        String sql = prop.getProperty("insertMember");
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,newMember.getMemberId());
            pstmt.setString(2,newMember.getPassword());
            pstmt.setString(3,newMember.getName());
            pstmt.setString(4, newMember.getEmail());
            pstmt.setString(5, newMember.getPhone());
            pstmt.setString(6, newMember.getAddress());
            
            if( newMember.getProvider() != null) {
            	pstmt.setString(7, newMember.getProvider().toString());
            } else {
            	pstmt.setString(7, null);
            }
            

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

    public List<Member> findPage(Connection conn, int start, int end) {
        List<Member> members = new ArrayList<>();
        String sql = prop.getProperty("findPage");
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
            try (ResultSet rset = pstmt.executeQuery()) {
                while (rset.next()) {
                    Member member = handleMemberResultSet(rset);
                    members.add(member);
                }
            }
        } catch (SQLException e) {
            throw new MemberException(e);
        }
        return members;
    }
    

	public int getTotalContent(Connection conn) {
		int totalContent = 0;
		String sql = prop.getProperty("getTotalContent"); // select count(*) from board
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next())
					totalContent = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new MemberException(e);
		}
		return totalContent;
	}



}




