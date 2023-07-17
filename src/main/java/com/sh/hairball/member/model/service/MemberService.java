
package com.sh.hairball.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.sh.hairball.animal.model.vo.AnimalEntity;
import com.sh.hairball.board.adoptboard.model.vo.AdopBoard;
import com.sh.hairball.member.model.dao.MemberDao;
import com.sh.hairball.member.model.vo.Member;
import com.sh.hairball.member.model.vo.MemberRole;

import static com.sh.hairball.common.JdbcTemplate.*;

public class MemberService {

    MemberDao memberDao = new MemberDao();

    public Member findById(String memberId) {
        Connection conn = getConnection();
        Member member = memberDao.findById(conn, memberId);
        close(conn);
        return member;
    }

    public int insertMember(Member newMember) {
        int result = 0;

        Connection conn = getConnection();

        try {
            result = memberDao.insertMember(conn, newMember);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }

    public int updateMember(Member member) {
        int result = 0;
        Connection conn = getConnection();
        try {
            result = memberDao.updateMember(conn, member);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }

    public int deleteMember(String memberId) {
        int result = 0;
        Connection conn = getConnection();
        try {
            result = memberDao.deleteMember(conn, memberId);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }

    public List<Member> findAll() {
        Connection conn = getConnection();
        List<Member> members = memberDao.findAll(conn);
        close(conn);
        return members;
    }

    public int updateMemberRole(String memberId, MemberRole memberRole) {
        Connection conn = getConnection();
        int result = 0;
        try {
            result = memberDao.updateMemberRole(conn, memberId, memberRole);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }

    public List<Member> searchMember(String searchType, String searchKeyword) {
        Connection conn = getConnection();
        List<Member> members = memberDao.searchMember(conn, searchType, searchKeyword);
        close(conn);
        return members;
    }

    public List<Member> findPage(int start, int end) {
        Connection conn = getConnection();
        List<Member> boards = memberDao.findPage(conn, start, end);
        close(conn);
        return boards;
    }

    public int getTotalContent() {
        Connection conn = getConnection();
        int totalContent = memberDao.getTotalContent(conn);
        close(conn);
        return totalContent;
    }

    public Member findByEmail(String email) {
        Connection conn = getConnection();
        Member member = memberDao.findByEmail(conn, email);
        close(conn);
        return member;
    }

	public Member findByName(String loginMember) {
        Connection conn = getConnection();
        Member member = memberDao.findByName(conn, loginMember);
        close(conn);
        return member;
    }
}
