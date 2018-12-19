package com.cloniamix.etest.dataBase.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.cloniamix.etest.pojo.Question;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questionforroom")
    List<QuestionForRoom> getAllQuestions();

    @Query("SELECT * FROM questionforroom WHERE groupNum = :groupNum")
    List<QuestionForRoom> getGroupQuestions(int groupNum);

    @Query("SELECT * FROM questionforroom WHERE groupNum = :groupNum AND questionNum = :questionNum" )
    QuestionForRoom getQuestionByNum(int groupNum, int questionNum);
//
//    @Transaction
//    @Query("SELECT * FROM questionforroom WHERE questionId = :id")
//    Question getQuestionObject(int id);

    @Query("SELECT * FROM AnswerForRoom WHERE groupNum = :groupNum AND idFromQuestion = :questionNum ")
    List<AnswerForRoom> getAllAnswers(int groupNum, int questionNum);

    @Query("SELECT * FROM ticketforroom WHERE groupNum = :groupNum AND ticketNum = :ticketNum")
    List<TicketForRoom> getTicket(int groupNum,int ticketNum);

    @Query("SELECT * FROM ticketforroom WHERE groupNum = :groupNum AND ticketNum = :ticketNum AND idFromQuestion =:questionNum")
    TicketForRoom getTicketByQuestionNum(int groupNum,int ticketNum, int questionNum);

    @Query("SELECT * FROM ticketforroom WHERE groupNum = :groupNum")
    List<TicketForRoom> getAllTickets(int groupNum);

    @Query("UPDATE questionforroom SET isCorrect =:correct, isUsed =:used  WHERE questionId =:id" )
    void updateQuestion(int id, boolean correct, boolean used);

    @Query("UPDATE ticketforroom SET isCorrectAnswered =:correct, isUsed =:used  WHERE ticketId =:id" )
    void updateTicket(int id, boolean correct, boolean used);









    @Insert/*(onConflict = OnConflictStrategy.REPLACE)*/
    void addQuestion(QuestionForRoom question);

    @Insert/*(onConflict = OnConflictStrategy.REPLACE)*/
    void addQuestions(List<QuestionForRoom> questions);

    @Insert
    void addAnswers(List<AnswerForRoom> answers);

    @Insert
    void addTickets(List<TicketForRoom> tickets);

    @Insert
    void addTicket(TicketForRoom ticket);

    /*@Update
    void updateDB(QuestionForRoom question);*/

    @Update
    void updateTicket(TicketForRoom ticket);

    @Delete
    void deleteQuestion(QuestionForRoom question);
}
