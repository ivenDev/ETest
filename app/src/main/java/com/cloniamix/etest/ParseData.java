package com.cloniamix.etest;


import android.content.res.AssetManager;
import android.util.Log;

import com.cloniamix.etest.dataBase.room.AnswerForRoom;
import com.cloniamix.etest.dataBase.room.QuestionForRoom;
import com.cloniamix.etest.dataBase.room.TicketForRoom;
import com.cloniamix.etest.pojo.Answer;
import com.cloniamix.etest.pojo.GSONObj;
import com.cloniamix.etest.pojo.Question;
import com.cloniamix.etest.pojo.Ticket;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public  class ParseData {

    private static String getStringFromJSON(String fileName){

        String result;

        try {
            AssetManager assetManager = MyApp.getInstance().getAssets();
            InputStream inputStream = assetManager.open(fileName);
            StringBuilder buffer = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            result = buffer.toString();

            Log.d("MyTAG", result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void initDataWithGSON(String fileName){

        String json = getStringFromJSON(fileName);
        Gson mGson = new Gson();
        GSONObj gsonObject = mGson.fromJson(json,GSONObj.class);

        int groupNum = gsonObject.getGroupNum();
        List<QuestionForRoom> questions = new ArrayList<>();
        List<AnswerForRoom> answers = new ArrayList<>();
        List<TicketForRoom> tickets = new ArrayList<>();

        //region преобразование в объекты для ROOM
        for (Question question: gsonObject.getQuestions()){
            QuestionForRoom questionForRoom = new QuestionForRoom();

            int questionNum = question.getQuestionNum();
            String questionText = question.getQuestionText();
            boolean correct = question.isCorrect();

            questionForRoom.setQuestionNum(questionNum);
            questionForRoom.setGroupNum(groupNum);
            questionForRoom.setQuestionText(questionText);
            questionForRoom.setUsed(false);// added 9.11.18
            questionForRoom.setCorrect(correct);
            questions.add(questionForRoom);

            for (Answer answer: question.getAnswers()){
                AnswerForRoom answerForRoom = new AnswerForRoom();
                answerForRoom.setGroupNum(groupNum);
                answerForRoom.setAnswerText(answer.getAnswerText());
                answerForRoom.setIdFromQuestion(questionNum);
                answerForRoom.setTrueOrFalse(answer.isTrueOrFalse());
                answers.add(answerForRoom);
            }
        }

        for (Ticket ticket: gsonObject.getTickets()){
            for (int questionNum: ticket.getQuestionsNumList()){

                TicketForRoom ticketForRoom = new TicketForRoom();
                ticketForRoom.setGroupNum(groupNum);
                ticketForRoom.setTicketNum(ticket.getTicketNum());
                ticketForRoom.setNumFromQuestion(questionNum);
                ticketForRoom.setUsed(false);
                ticketForRoom.setCorrectAnswered(false);

                tickets.add(ticketForRoom);
            }
        }
        //endregion

        MyApp.getInstance().getDatabase().mQuestionDao().addQuestions(questions);
        MyApp.getInstance().getDatabase().mQuestionDao().addAnswers(answers);
        MyApp.getInstance().getDatabase().mQuestionDao().addTickets(tickets);

    }
}

