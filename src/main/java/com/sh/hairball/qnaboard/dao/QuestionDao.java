package com.sh.hairball.qnaboard.dao;


import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.hairball.qnaboard.exception.QuestionException;
import com.sh.hairball.qnaboard.model.AnswerVo;
import com.sh.hairball.qnaboard.model.QuestionVo;

public class QuestionDao {
    private Properties prop = new Properties();
    public QuestionDao() {
        String filename = QuestionVo.class.getResource("/sql/question/question-query.properties").getPath();;
        try {
            prop.load(new FileReader(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getTotalContent(Connection conn) {
        int totalContent = 0;
        String sql = prop.getProperty("getTotalContent");
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try(ResultSet rset = pstmt.executeQuery()) {
                while(rset.next()) {
                    totalContent = rset.getInt(1); // 1은 첫번째
                }
            }
        } catch (SQLException e) {
            throw new QuestionException(e);
        }

        return totalContent;
    }


    public List<QuestionVo> findAll(Connection conn, int start, int end) {
        List<QuestionVo> questions = new ArrayList<>(); // 여러개를 만들때는 ArrayList로 초기화해서 만든다.
        String sql = prop.getProperty("findAll");
        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, start);
            pstmt.setInt(2, end);
            try(ResultSet rset = pstmt.executeQuery()) {
                while(rset.next()) {
                    QuestionVo question = handleQuestionResultSet(rset);
                    question.setAnswerCnt(rset.getInt("answer_cnt"));
                    questions.add(question);
                }
            }
        } catch (SQLException e) {
            throw new QuestionException(e);
        }
        return questions;

    }

    private QuestionVo handleQuestionResultSet(ResultSet rset) throws SQLException {
        QuestionVo question = new QuestionVo();
        question.setId(rset.getInt("id"));
        question.setMemberId(rset.getString("member_id"));
        question.setTitle(rset.getString("title"));
        question.setContent(rset.getString("content"));
        question.setRegDate(rset.getDate("reg_date"));
        question.setAnswerCnt(rset.getInt("answer_cnt"));
        return question;
    }

    public int insertQuestion(Connection conn, QuestionVo question) {
        int result = 0;
        String sql = prop.getProperty("insertQuestion");

//insert into question (id,title,member_id,content) values (seq_question_id.nextval, ?, ?, ?)
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getMemberId());
            pstmt.setString(3, question.getContent());

            result = pstmt.executeUpdate(); // 실행
        } catch (SQLException e) {
            throw new QuestionException(e); // 전환해서 예외만 던져준다.
        }

        return result;
    }

    public int getLastQuestionId(Connection conn) {
        int questionId = 0;
        String sql = prop.getProperty("getLastQuestionId");
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            try(ResultSet rset = pstmt.executeQuery()){
                if(rset.next()) {
                    questionId = rset.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new QuestionException(e);
        }
        return questionId;
    }

    public QuestionVo findById(Connection conn, int id) {
        QuestionVo question = null;
        String sql = prop.getProperty("findById");
        // select * from question where id = ?
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rset = pstmt.executeQuery()) {
                if (rset.next())
                    question = handleQuestionResultSet(rset);
            }
        } catch (SQLException e) {
            throw new QuestionException(e);
        }
        return question;
    }

    public int updateQuestion(Connection conn, QuestionVo question) {
        int result = 0;
        String sql = prop.getProperty("updateQuestion");
        // update question set title = ?, content = ? where id = ?
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question.getTitle());
            pstmt.setString(2, question.getContent());
            pstmt.setInt(3, question.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new QuestionException(e);
        }
        return result;
    }


    public List<AnswerVo> findAnswerByQuestionId(Connection conn, int questionId) {
        List<AnswerVo> answers = new ArrayList<>();
        String sql = prop.getProperty("findAnswerByQuestionId");
        // select * from answer where question_id = ?
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, questionId);
            try (ResultSet rset = pstmt.executeQuery()) {
                while(rset.next()) {
                    AnswerVo answer = handleAnswerResultSet(rset);
                    answers.add(answer);
                }
            }
        } catch (SQLException e) {
            throw new QuestionException(e);
        }
        return answers;
    }

    private AnswerVo handleAnswerResultSet(ResultSet rset) throws SQLException {
        int id = rset.getInt("id");
        String adminName = rset.getString("admin_name");
        String content = rset.getString("content");
        int questionId = rset.getInt("question_id");
        Date regDate = rset.getDate("reg_date");

        return new AnswerVo(id, adminName, content, regDate, questionId);
    }

    public int deleteQuestion(Connection conn, int id) {
        int result = 0;
        String sql = prop.getProperty("deleteQuestion");
        // delete from question where id = ?
        try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new QuestionException(e);
        }
        return result;
    }

    public int deleteAnswer(Connection conn, int id) {
        int result = 0;
        String sql = prop.getProperty("deleteAnswer");
        // delete from answer where id = ?
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new QuestionException(e);
        }

        return result;
    }

    public int insertAnswer(Connection conn, AnswerVo answer) {
        int result = 0;
        String sql = prop.getProperty("insertAnswer");
        // insert into answer values(seq_answer_id.nextval, default, ?, ?, default)
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, answer.getQuestionId());
            pstmt.setString(2, answer.getContent());
            
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new QuestionException(e);
        }

        return result;
    }
}
