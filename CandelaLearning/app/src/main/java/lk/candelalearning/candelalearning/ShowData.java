package lk.candelalearning.candelalearning;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by tharu on 4/12/2018.
 */

public class ShowData {

    mcqFragment SetMCQFragmentData(DataBaseHelper myDb, Integer val, int SELECTEDPAPER){

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
        Cursor QuestionCursor = myDb.getAllQuestionData(val, SELECTEDPAPER);
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

        //Get the image details from DB
        //START
        Cursor QuestionimgCursor = myDb.getImgDataForQuestionId(Question_Id, 1);
        String img_Name = "";
        try{
            while (QuestionimgCursor.moveToNext()) {
                index = QuestionimgCursor.getColumnIndexOrThrow("img_Name");
                img_Name = QuestionimgCursor.getString(index);
            }
        }finally {
            QuestionimgCursor.close();
        }

        Cursor AnswerimgCursor = myDb.getImgDataForAnswerId(Question_Id, 1);
        ArrayList<String> TmpAnswerImgArryLst = new ArrayList<String>();
        String Answr_img_Name = "";
        try{
            while (AnswerimgCursor.moveToNext()) {
                index = AnswerimgCursor.getColumnIndexOrThrow("img_Name");
                Answr_img_Name = AnswerimgCursor.getString(index);
                TmpAnswerImgArryLst.add(Answr_img_Name);
            }
        }finally {
            AnswerimgCursor.close();
        }

        //END

        //String[] AnswerArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

        String[] AnswerArray = new String[TmpArryLst.size()];
        AnswerArray = TmpArryLst.toArray(AnswerArray);
        String[] AnswerImgArry = new String[TmpAnswerImgArryLst.size()];
        AnswerImgArry = TmpAnswerImgArryLst.toArray(AnswerImgArry);

        mcqFragment fragment = new mcqFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("VALUE1", VALUE1);
        arguments.putString("VALUE2", QuestionData);
        arguments.putStringArray("VALUE3", AnswerArray); //bundle.getStringArray("VALUE3");
        arguments.putInt("VALUE4", CorrectAnswerPosition);
        arguments.putString("VALUE5", img_Name);
        arguments.putStringArray("VALUE6", AnswerImgArry);
        fragment.setArguments(arguments);

        return fragment;
    }

    McqRecyclerView_mcqFragment SetMCQ_RecyclerView_FragmentData(DataBaseHelper myDb, Integer val, int SELECTEDPAPER){

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
        Cursor QuestionCursor = myDb.getAllQuestionData(val, SELECTEDPAPER);
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

        //Get the image details from DB
        //START
        Cursor QuestionimgCursor = myDb.getImgDataForQuestionId(Question_Id, 1);
        String img_Name = "";
        try{
            while (QuestionimgCursor.moveToNext()) {
                index = QuestionimgCursor.getColumnIndexOrThrow("img_Name");
                img_Name = QuestionimgCursor.getString(index);
            }
        }finally {
            QuestionimgCursor.close();
        }

        Cursor AnswerimgCursor = myDb.getImgDataForAnswerId(Question_Id, 1);
        ArrayList<String> TmpAnswerImgArryLst = new ArrayList<String>();
        String Answr_img_Name = "";
        try{
            while (AnswerimgCursor.moveToNext()) {
                index = AnswerimgCursor.getColumnIndexOrThrow("img_Name");
                Answr_img_Name = AnswerimgCursor.getString(index);
                TmpAnswerImgArryLst.add(Answr_img_Name);
            }
        }finally {
            AnswerimgCursor.close();
        }

        //END

        //String[] AnswerArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

        String[] AnswerArray = new String[TmpArryLst.size()];
        AnswerArray = TmpArryLst.toArray(AnswerArray);
        String[] AnswerImgArry = new String[TmpAnswerImgArryLst.size()];
        AnswerImgArry = TmpAnswerImgArryLst.toArray(AnswerImgArry);

        McqRecyclerView_mcqFragment fragment = new McqRecyclerView_mcqFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("VALUE1", VALUE1);
        arguments.putString("VALUE2", QuestionData);
        arguments.putStringArray("VALUE3", AnswerArray); //bundle.getStringArray("VALUE3");
        arguments.putInt("VALUE4", CorrectAnswerPosition);
        arguments.putString("VALUE5", img_Name);
        arguments.putStringArray("VALUE6", AnswerImgArry);
        fragment.setArguments(arguments);

        return fragment;
    }

    McqRecyclerView_mcqFragment SetMCQ_RecyclerView_FragmentData_Edit(DataBaseHelper myDb, Integer val, int SELECTEDPAPER){

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
        Cursor QuestionCursor = myDb.getAllQuestionData(val,SELECTEDPAPER);
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
                //TmpArryLst.add(numb + ") " + Answer);
                TmpArryLst.add(Answer);
                numb++;
            }
        }finally {
            AnswerCursor.close();
        }
        //END

        //Get the image details from DB
        //START
        Cursor QuestionimgCursor = myDb.getImgDataForQuestionId(Question_Id, 1);
        String img_Name = "";
        try{
            while (QuestionimgCursor.moveToNext()) {
                index = QuestionimgCursor.getColumnIndexOrThrow("img_Name");
                img_Name = QuestionimgCursor.getString(index);
            }
        }finally {
            QuestionimgCursor.close();
        }

        Cursor AnswerimgCursor = myDb.getImgDataForAnswerId(Question_Id, 1);
        ArrayList<String> TmpAnswerImgArryLst = new ArrayList<String>();
        ArrayList<Integer> TmpAnswrIdLst = new ArrayList<Integer>();
        String Answr_img_Name = "";
        int Tmp_Answer_Id = 0;
        try{
            while (AnswerimgCursor.moveToNext()) {
                index = AnswerimgCursor.getColumnIndexOrThrow("img_Name");
                Answr_img_Name = AnswerimgCursor.getString(index);
                TmpAnswerImgArryLst.add(Answr_img_Name);

                index = AnswerimgCursor.getColumnIndexOrThrow("Answer_Id");
                Tmp_Answer_Id =  Integer.parseInt(AnswerimgCursor.getString(index));
                TmpAnswrIdLst.add(Tmp_Answer_Id);
            }
        }finally {
            AnswerimgCursor.close();
        }

        //END

        //String[] AnswerArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};

        String[] AnswerArray = new String[TmpArryLst.size()];
        AnswerArray = TmpArryLst.toArray(AnswerArray);
        String[] AnswerImgArry = new String[TmpAnswerImgArryLst.size()];
        AnswerImgArry = TmpAnswerImgArryLst.toArray(AnswerImgArry);

        int[] AnswerIDArry = new int[TmpAnswrIdLst.size()];
        Iterator<Integer> iterator = TmpAnswrIdLst.iterator();
        for (int i = 0; i < AnswerIDArry.length; i++)
        {
            AnswerIDArry[i] = iterator.next().intValue();
        }

        McqRecyclerView_mcqFragment fragment = new McqRecyclerView_mcqFragment();
        Bundle arguments = new Bundle();
//        arguments.putInt("VALUE1", VALUE1);
//        arguments.putString("VALUE2", QuestionData);
//        arguments.putStringArray("VALUE3", AnswerArray); //bundle.getStringArray("VALUE3");
//        arguments.putInt("VALUE4", CorrectAnswerPosition);
//        arguments.putString("VALUE5", img_Name);
//        arguments.putStringArray("VALUE6", AnswerImgArry);


        arguments.putInt("VALUE1", VALUE1);
        arguments.putString("VALUE2", QuestionData);
        arguments.putStringArray("VALUE3", AnswerArray);
        arguments.putInt("VALUE4", CorrectAnswerPosition);
        arguments.putString("VALUE5", img_Name);
        arguments.putStringArray("VALUE6", AnswerImgArry);
        arguments.putIntArray("VALUE7", AnswerIDArry);
        fragment.setArguments(arguments);

        return fragment;
    }

    String[] SetMCQStringArrayData(DataBaseHelper myDb, Integer val, int SELECTEDPAPER){

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
        Cursor QuestionCursor = myDb.getAllQuestionData(val, SELECTEDPAPER);
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
