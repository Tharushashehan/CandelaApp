package lk.candelalearning.candelalearning;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tharu on 4/19/2018.
 */

public class PaperYearFragment extends Fragment implements OnBackPressedListener {
        View myView;
        ListView answer_list_view;
        DataBaseHelper myDb;
        TextView tv ;
        int QuestionNumber = 1;
        CountDownTimer TheTimer;
        FloatingActionButton fab_select;
        RecyclerView answer_recycle_view;
        RecyclerView.LayoutManager mLayoutManager;
        private List<Person> persons;


        /**
         * {@inheritDoc}
         */
        @Override
        public boolean onBackPressedCheck() {
            return true;
        }

        @Override
        public Fragment SetFragmentData() {
            int index, Subject_Id = 1;
            String SubjectMassage = "Please select a subject to go", Subject;
            DataBaseHelper myDb = new DataBaseHelper(MainActivity.getAppContext());
            Cursor GradeCursor = myDb.getAllGradeSubjectData(5);
            ArrayList<String> TmpArryLst = new ArrayList<String>();
            try{
                while (GradeCursor.moveToNext()) {
                    index = GradeCursor.getColumnIndexOrThrow("Subject_Id");
                    Subject_Id = Integer.parseInt(GradeCursor.getString(index));
                    index = GradeCursor.getColumnIndexOrThrow("Subject");
                    Subject = GradeCursor.getString(index);
                    TmpArryLst.add(Subject);
                }
            }finally {
                GradeCursor.close();
            }

            String[] SubjectArray = new String[TmpArryLst.size()];
            SubjectArray = TmpArryLst.toArray(SubjectArray);

            SubjectFragment Frag = new SubjectFragment();
            Bundle arguments = new Bundle();
            arguments.putInt("VALUE1", Subject_Id);
            arguments.putString("VALUE2", SubjectMassage);
            arguments.putStringArray("VALUE3", SubjectArray);
            Frag.setArguments(arguments);
            return Frag;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
        savedInstanceState) {
        myView = inflater.inflate(R.layout.mcq, container, false);
        tv = (TextView) myView.findViewById(R.id.textView2);
        fab_select = (FloatingActionButton) myView.findViewById(R.id.fab_select);
        answer_list_view = (ListView) myView.findViewById(R.id.answer_list_view);
        answer_recycle_view = (RecyclerView) myView.findViewById(R.id.answer_recycle_view);
        answer_recycle_view.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(myView.getContext());
        answer_recycle_view.setLayoutManager(mLayoutManager);
        answer_recycle_view.setVisibility(View.VISIBLE);
        answer_list_view.setVisibility(View.GONE);

        try{
            int value1=0;
            String value2=" ";

            Bundle bundle = this.getArguments();
            if (bundle != null) {
                value1 = bundle.getInt("VALUE1", -1);
                value2 = bundle.getString("VALUE2", "h");
                //Persons pp = new Persons(persons);
                Persons pp = bundle.getParcelable("VALUE3");
                //persons = bundle.getParcelable("VALUE3");
                tv.setText(value2);
                persons = pp.getPersonData();
                RVAdapter Recycleadapter = new RVAdapter(persons);
                answer_recycle_view.setAdapter(Recycleadapter);

                MainActivity.getnext_button().setVisibility(View.GONE);
                MainActivity.GetProgressBar().setProgress(0);
                //TheTimer.cancel();
                MainActivity.getTextViewTimer().setText("Lets Start");

            }else{
                tv.setText("Click next to start");
            }
        }catch (Exception ex){
            tv.setText("Click next to start");
        }

        answer_recycle_view.addOnItemTouchListener(
                new RecyclerItemClickListener(myView.getContext(), answer_recycle_view ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                            answer_list_view.setVisibility(View.VISIBLE);
                            answer_recycle_view.setVisibility(View.GONE);


                            MainActivity.getnext_button().setVisibility(View.VISIBLE);

                            /*int VALUE1 = myDb.numberOfRows("Question");
                            int VALUE2 = myDb.numberOfRows("Answer");*/

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
                                    TmpArryLst.add(numb + ") " + Answer);
                                    numb++;
                                }
                            }finally {
                                AnswerCursor.close();
                            }
                            String[] AnswerArray = new String[TmpArryLst.size()];
                            AnswerArray = TmpArryLst.toArray(AnswerArray);
                            mcqFragment Frag = new mcqFragment();
                            Bundle arguments = new Bundle();
                            arguments.putInt("VALUE1", VALUE1);
                            arguments.putString("VALUE2", QuestionData);
                            arguments.putStringArray("VALUE3", AnswerArray);
                            arguments.putInt("VALUE4", CorrectAnswerPosition);
                            Frag.setArguments(arguments);
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.content_frame , Frag, "MCQFragment").addToBackStack("MCQFragment").commit();

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(myView.getContext(), "Don't Long Press", Toast.LENGTH_LONG).show();
                    }
                })
        );

        return  myView;
    }

}
