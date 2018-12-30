package com.cloniamix.etest.model;

import com.cloniamix.etest.Contract;
import com.cloniamix.etest.MyApp;
import com.cloniamix.etest.dataBase.room.AnswerForRoom;
import com.cloniamix.etest.dataBase.room.AppDatabase;
import com.cloniamix.etest.dataBase.room.QuestionDao;
import com.cloniamix.etest.dataBase.room.QuestionForRoom;
import com.cloniamix.etest.dataBase.room.TicketForRoom;
import com.cloniamix.etest.pojo.Answer;
import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.pojo.Ticket;
import java.util.ArrayList;
import java.util.List;

public class Model implements Contract.Model {

    private AppDatabase db;
    private QuestionDao mQuestionDao;


    public Model() {
        db = MyApp.getInstance().getDatabase();
        mQuestionDao = db.mQuestionDao();

    }

    @Override
    public List<Ticket> getTickets(int groupNumber) {

        List<Ticket> mTickets = new ArrayList<>();

        int i = 1;
        for (TicketForRoom ticketForRoom : mQuestionDao.getAllTickets(groupNumber)) {

            if (i == ticketForRoom.getTicketNum()) {
                mTickets.add(getTicket(groupNumber, ticketForRoom.getTicketNum()));
                i++;
            }
        }

        return mTickets;
    }

    @Override
    public Ticket getTicket(int groupNumber, int ticketNumber) {

        int usedCount = 0;
        int isCorrect = 0;

        Ticket ticket = new Ticket();
        List<Integer> questionsNumList = new ArrayList<>();

        for (TicketForRoom ticketForRoom : mQuestionDao.getTicket(groupNumber, ticketNumber)) {

            ticket.setTicketNum(ticketForRoom.getTicketNum());
            questionsNumList.add(ticketForRoom.getNumFromQuestion());
            if (ticketForRoom.isUsed()){
                usedCount++;
            }
            if (ticketForRoom.isCorrectAnswered()){
                isCorrect++;
            }
        }

        ticket.setQuestionsNumList(questionsNumList);
        ticket.setUsedTicketCount(usedCount);
        ticket.setCorrectAnsweredCount(isCorrect);
        ticket.setPercentAnswered(isCorrect*100/questionsNumList.size());



        return ticket;
    }

    public List<Question> getTicketQuestions(int groupNumber, int ticketNumber) {

        List<Question> questions = new ArrayList<>();
        Ticket ticket = getTicket(groupNumber, ticketNumber);
        for (int questionNum : ticket.getQuestionsNumList()) {
            Question question = new Question();
            QuestionForRoom questionForRoom = mQuestionDao.getQuestionByNum(groupNumber, questionNum);

            int id = questionForRoom.getQuestionId();
            int num = questionForRoom.getQuestionNum();
            String questionText = questionForRoom.getQuestionText();
            boolean correct = questionForRoom.isCorrect();

            question.setQuestionId(id);
            question.setQuestionNum(num);
            question.setQuestionText(questionText);
            question.setCorrect(correct);

            List<Answer> answers = new ArrayList<>();
            for (AnswerForRoom answerForRoom : mQuestionDao.getAllAnswers(groupNumber, questionNum)) {
                Answer answer = new Answer();
                answer.setAnswerText(answerForRoom.getAnswerText());
                answer.setTrueOrFalse(answerForRoom.isTrueOrFalse());
                answers.add(answer);
            }
            question.setAnswers(answers);
            questions.add(question);
        }

        return questions;

    }


    public Question getTicketQuestion(int groupNumber, int ticketNumber, int questionNumInTicket) {


        return getTicketQuestions(groupNumber, ticketNumber).get(questionNumInTicket - 1);
    }

    public List<Question> getGroupQuestions(int groupNum){
        List<Question> questions = new ArrayList<>();
        List<QuestionForRoom> questionForRoomList = db.mQuestionDao().getGroupQuestions(groupNum);

        for (QuestionForRoom questionForRoom: questionForRoomList){
            Question question = new Question();

            int id = questionForRoom.getQuestionId();
            int questionNum = questionForRoom.getQuestionNum();
            String questionText = questionForRoom.getQuestionText();
            boolean correct = questionForRoom.isCorrect();
            boolean used = questionForRoom.isUsed();

            List<Answer> answers = new ArrayList<>();
            for (AnswerForRoom answerForRoom : mQuestionDao.getAllAnswers(groupNum, questionNum)) {
                Answer answer = new Answer();
                answer.setAnswerText(answerForRoom.getAnswerText());
                answer.setTrueOrFalse(answerForRoom.isTrueOrFalse());
                answers.add(answer);
            }

            question.setQuestionId(id);
            question.setQuestionNum(questionNum);
            question.setQuestionText(questionText);
            question.setCorrect(correct);
            question.setUsed(used);
            question.setAnswers(answers);

            questions.add(question);
        }

        return questions;
    }

    @Override
    public void updateQuestionInDB(Question question) {
        db.mQuestionDao().updateQuestion(question.getQuestionId(), question.isCorrect(), question.isUsed());
    }

    @Override
    public void updateTicketInDB(Question question, int groupNum, int ticketNum) {
        TicketForRoom ticketForRoom = db.mQuestionDao().getTicketByQuestionNum(groupNum, ticketNum , question.getQuestionNum());
        ticketForRoom.setCorrectAnswered(question.isCorrect());
        ticketForRoom.setUsed(question.isUsed());
        db.mQuestionDao().updateTicket(ticketForRoom);
    }


    public void resetQuestionUsed(List<Question> questions){
        for (Question question: questions){
            question.setUsed(false);
            updateQuestionInDB(question);
        }
    }
}
