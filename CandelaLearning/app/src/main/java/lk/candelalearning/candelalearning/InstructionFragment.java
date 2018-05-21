package lk.candelalearning.candelalearning;



import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import static android.R.attr.enabled;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;



public class InstructionFragment extends Fragment {

    View myView;
    FloatingActionButton fab_select;
    DataBaseHelper myDb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_instruction, container, false);
        fab_select = (FloatingActionButton) myView.findViewById(R.id.fab_select);

        fab_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myDb = new DataBaseHelper(MainActivity.getAppContext());
                int VALUE1 = 1;
                int index;
                int Question_Id, Ppr_No, User_Id, Time, Answer_Id, Correct;
                String QuestionData = " ", Answer;
                Question_Id = 1;

                Cursor QuestionCursor = myDb.getAllQuestionData(1);
                try{
                    if(QuestionCursor != null && QuestionCursor.moveToFirst()) {

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
//                                    TmpArryLst.add(numb + ") " + Answer);
                        TmpArryLst.add(Answer);
                        numb++;
                    }
                }finally {
                    AnswerCursor.close();
                }

                //Get the image details from DB
                //START
                Cursor QuestionimgCursor = myDb.getImgDataForQuestionId(Question_Id, 1);
                String img_Name = " ";
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
                String Answr_img_Name = " ";
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

                String[] AnswerArray = new String[TmpArryLst.size()];
                AnswerArray = TmpArryLst.toArray(AnswerArray);

                int[] AnswerIDArry = new int[TmpAnswrIdLst.size()];
                Iterator<Integer> iterator = TmpAnswrIdLst.iterator();
                for (int i = 0; i < AnswerIDArry.length; i++)
                {
                    AnswerIDArry[i] = iterator.next().intValue();
                }

                String[] AnswerImgArry = new String[TmpAnswerImgArryLst.size()];
                AnswerImgArry = TmpAnswerImgArryLst.toArray(AnswerImgArry);

                //AnswerImgArry = TmpAnswerImgArryLst.toArray(AnswerImgArry);
                McqRecyclerView_mcqFragment Frag = new McqRecyclerView_mcqFragment();
                Bundle arguments = new Bundle();
                arguments.putInt("VALUE1", VALUE1);
                arguments.putString("VALUE2", QuestionData);
                arguments.putStringArray("VALUE3", AnswerArray);
                arguments.putInt("VALUE4", CorrectAnswerPosition);
                arguments.putString("VALUE5", img_Name);
                arguments.putStringArray("VALUE6", AnswerImgArry);
                arguments.putIntArray("VALUE7", AnswerIDArry);
                Frag.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame , Frag, "MCQFragment").addToBackStack("MCQFragment").commit();
                MainActivity.getnext_button().setVisibility(View.VISIBLE);
            }
            });
        return  myView;
    }
}
