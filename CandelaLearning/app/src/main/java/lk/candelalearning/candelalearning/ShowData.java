package lk.candelalearning.candelalearning;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by tharu on 4/12/2018.
 */

public class ShowData {

    mcqFragment SetMCQFragmentData(DataBaseHelper myDb, Integer val){

        //Here we pass question and answer data for each question
        //START
        /*DataBaseHelper myDb;
        myDb = new DataBaseHelper(this);*/
        int VALUE1 = myDb.numberOfRows("Question");
        int VALUE2 = myDb.numberOfRows("Answer");
        int index;
        int Question_Id, Ppr_No, User_Id, Time, Answer_Id, Correct;
        String QuestionData = " ", Answer;
        Question_Id = 1;

        //Here this used to add question list to the string Array
        //START
        Cursor QuestionCursor = myDb.getAllQuestionData(val);
        try{
            if(QuestionCursor != null && QuestionCursor.moveToFirst()) {
             /*   //String id = cursor.getString(cursor.getColumnIndex(Question._ID));
                index = cursor.getColumnIndexOrThrow("Question");
                QuestionData = cursor.getString(index);*/

                index = QuestionCursor.getColumnIndexOrThrow("Question_Id");
                Question_Id = Integer.parseInt(QuestionCursor.getString(index));

                index = QuestionCursor.getColumnIndexOrThrow("Ppr_No");
                Ppr_No = Integer.parseInt(QuestionCursor.getString(index));

                index = QuestionCursor.getColumnIndexOrThrow("User_Id");
                User_Id = Integer.parseInt(QuestionCursor.getString(index));

                index = QuestionCursor.getColumnIndexOrThrow("Time");
                Time = Integer.parseInt(QuestionCursor.getString(index));

                index = QuestionCursor.getColumnIndexOrThrow("Question");
                QuestionData = QuestionCursor.getString(index);
            }
        }finally {
            QuestionCursor.close();
        }
        //END

        //Here this used to add answer list to the string Array
        //START
        Cursor AnswerCursor = myDb.getAnswerDataForQuestionId(Question_Id);
        ArrayList<String> TmpArryLst = new ArrayList<String>();
        int CorrectAnswerPositionTemp =0 , CorrectAnswerPosition = 0;
        char  numb = 'a';
        try{
            while (AnswerCursor.moveToNext()) {
                index = AnswerCursor.getColumnIndexOrThrow("Answer_Id");
                Answer_Id = Integer.parseInt(AnswerCursor.getString(index));

                index = AnswerCursor.getColumnIndexOrThrow("Answer");
                Answer = AnswerCursor.getString(index);

                index = AnswerCursor.getColumnIndexOrThrow("Correct");
                Correct = Integer.parseInt(AnswerCursor.getString(index));
                if (Correct == 0){
                    CorrectAnswerPosition = CorrectAnswerPositionTemp;
                }
                CorrectAnswerPositionTemp++;
                TmpArryLst.add(numb + ") " + Answer);
                numb++;
            }
        }finally {
            AnswerCursor.close();
        }
        //END

        //String[] AnswerArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

        String[] AnswerArray = new String[TmpArryLst.size()];
        AnswerArray = TmpArryLst.toArray(AnswerArray);

        mcqFragment fragment = new mcqFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("VALUE1", VALUE1);
        arguments.putString("VALUE2", QuestionData);
        arguments.putStringArray("VALUE3", AnswerArray); //bundle.getStringArray("VALUE3");
        arguments.putInt("VALUE4", CorrectAnswerPosition);
        fragment.setArguments(arguments);

        return fragment;
    }

    String[] SetMCQStringArrayData(DataBaseHelper myDb, Integer val){

        //Here we pass question and answer data for each question
        //START
        /*DataBaseHelper myDb;
        myDb = new DataBaseHelper(this);*/
        int VALUE1 = myDb.numberOfRows("Question");
        int VALUE2 = myDb.numberOfRows("Answer");
        int index;
        int Question_Id, Ppr_No, User_Id, Time, Answer_Id, Correct;
        String QuestionData = " ", Answer;
        Question_Id = 1;

        //Here this used to add question list to the string Array
        //START
        Cursor QuestionCursor = myDb.getAllQuestionData(val);
        try{
            if(QuestionCursor != null && QuestionCursor.moveToFirst()) {
             /*   //String id = cursor.getString(cursor.getColumnIndex(Question._ID));
                index = cursor.getColumnIndexOrThrow("Question");
                QuestionData = cursor.getString(index);*/

                index = QuestionCursor.getColumnIndexOrThrow("Question_Id");
                Question_Id = Integer.parseInt(QuestionCursor.getString(index));

                index = QuestionCursor.getColumnIndexOrThrow("Ppr_No");
                Ppr_No = Integer.parseInt(QuestionCursor.getString(index));

                index = QuestionCursor.getColumnIndexOrThrow("User_Id");
                User_Id = Integer.parseInt(QuestionCursor.getString(index));

                index = QuestionCursor.getColumnIndexOrThrow("Time");
                Time = Integer.parseInt(QuestionCursor.getString(index));

                index = QuestionCursor.getColumnIndexOrThrow("Question");
                QuestionData = QuestionCursor.getString(index);
            }
        }finally {
            QuestionCursor.close();
        }
        //END

        //Here this used to add answer list to the string Array
        //START
        Cursor AnswerCursor = myDb.getAnswerDataForQuestionId(Question_Id);
        ArrayList<String> TmpArryLst = new ArrayList<String>();

        try{
            while (AnswerCursor.moveToNext()) {
                index = AnswerCursor.getColumnIndexOrThrow("Answer_Id");
                Answer_Id = Integer.parseInt(AnswerCursor.getString(index));

                index = AnswerCursor.getColumnIndexOrThrow("Answer");
                Answer = AnswerCursor.getString(index);

                index = AnswerCursor.getColumnIndexOrThrow("Correct");
                Correct = Integer.parseInt(AnswerCursor.getString(index));

                TmpArryLst.add(Answer);
            }
        }finally {
            AnswerCursor.close();
        }
        //END

        //String[] AnswerArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

        String[] AnswerArray = new String[TmpArryLst.size()];
        AnswerArray = TmpArryLst.toArray(AnswerArray);

        mcqFragment fragment = new mcqFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("VALUE1", VALUE1);
        arguments.putString("VALUE2", QuestionData);
        arguments.putStringArray("VALUE3", AnswerArray); //bundle.getStringArray("VALUE3");
        fragment.setArguments(arguments);

        return AnswerArray;
    }
}
