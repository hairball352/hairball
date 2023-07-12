package com.sh.hairball.qnaboard.service;


import java.sql.Connection;
import java.util.List;

import com.sh.hairball.qnaboard.dao.QuestionDao;
import com.sh.hairball.qnaboard.model.AnswerVo;
import com.sh.hairball.qnaboard.model.QuestionVo;
import static com.sh.hairball.common.JdbcTemplate.*;

public class QuestionService {
    private final QuestionDao questionDao = new QuestionDao();
    public int getTotalContent() {
        Connection conn = getConnection();
        int totalContent = questionDao.getTotalContent(conn);
        close(conn);
        return totalContent;
    }

    public List<QuestionVo> findAll(int start, int end) {
        Connection conn = getConnection();
        List<QuestionVo> questions = questionDao.findAll(conn, start, end);
        close(conn);
        return questions;
    }

    public int insertQuestion(QuestionVo question) {
        int result = 0;
        Connection conn = getConnection();

        try {
            // board 테이블 추가
            result = questionDao.insertQuestion(conn, question);

            // 발급된 id를 조회
            int questionId = questionDao.getLastQuestionId(conn);
            question.setId(questionId); // servlet에서 redirect시 사용
            System.out.println("questionId = " + questionId);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }

    public QuestionVo findById(int id) {
        Connection conn = getConnection();
        QuestionVo question = questionDao.findById(conn, id);
        close(conn);
        return question;
    }

    public int updateQuestion(QuestionVo question) {
        int result = 0;
        Connection conn = getConnection();
        try {
            // board 테이블 추가
            result = questionDao.updateQuestion(conn, question);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }

        return result;
    }

    public List<AnswerVo> findAnswerByQuestionId(int questionId) {
        Connection conn = getConnection();
        List<AnswerVo> answers = questionDao.findAnswerByQuestionId(conn, questionId);
        close(conn);
        return answers;
    }

    public int deleteQuestion(int id) {
        Connection conn = getConnection();
        int result = 0;
        try {
            result = questionDao.deleteQuestion(conn, id);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }
        return result;
    }

    public int deleteAnswer(int id) {
        Connection conn = getConnection();
        int result = 0;
        try {
            result = questionDao.deleteAnswer(conn, id);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }

        return result;
    }

    public int insertAnswer(AnswerVo answer) {
        Connection conn = getConnection();
        int result = 0;
        try {
            result = questionDao.insertAnswer(conn, answer);
            commit(conn);
        } catch (Exception e) {
            rollback(conn);
            throw e;
        } finally {
            close(conn);
        }

        return result;
    }
}
