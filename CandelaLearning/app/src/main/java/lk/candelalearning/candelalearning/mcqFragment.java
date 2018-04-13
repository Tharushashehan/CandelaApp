package lk.candelalearning.candelalearning;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by tharusha Shehan on 4/7/2018.
 */
public class mcqFragment extends Fragment{


    View myView;
    ListView answer_list_view; // = (ListView) myView.findViewById(R.id.answer_list_view);
    ArrayAdapter adapter;
    DataBaseHelper myDb;
    Integer Ativityposition;
    TextView tv ;
    int QuestionNumber = 1;
    int Marks = 0;
    int LastCorrectness = 35;
    CountDownTimer TheTimer;
    //Context context = getActivity();
    //MainActivity MA = new MainActivity();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mcq, container, false);
        tv = (TextView) myView.findViewById(R.id.textView2);
        answer_list_view = (ListView) myView.findViewById(R.id.answer_list_view);
        //MainActivity.getnext_button().setVisibility(View.GONE);


        try{
            int value1=0;
            String value2=" ";
            String[] StringArray = {};

            Bundle bundle = this.getArguments();
            if (bundle != null) {
                value1 = bundle.getInt("VALUE1", -1);
                value2 = bundle.getString("VALUE2", "h");
                StringArray = bundle.getStringArray("VALUE3");
                tv.setText(value2);
                //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.second_layout, StringArray);
                adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, StringArray);
                Ativityposition = 1;
                answer_list_view.setAdapter(adapter);
                MainActivity.getnext_button().setVisibility(View.GONE);
                MainActivity.GetProgressBar().setProgress(0);
                TheTimer.cancel();
                MainActivity.getTextViewTimer().setText("Lets Start");

            }else{
                tv.setText("Click next to start");
            }
        }catch (Exception ex){
            tv.setText("Click next to start");
        }

        MainActivity.getnext_button().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Ativityposition >= 2 & QuestionNumber <= 10){
                    MainActivity.getnext_button().setVisibility(View.VISIBLE);
                    //ShowData SD = new ShowData();
                    //adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, SD.SetMCQStringArrayData( new DataBaseHelper(MainActivity.getAppContext()), Ativityposition));
                    //Ativityposition++;
                    //answer_list_view.setAdapter(adapter);
                    ShowData SD = new ShowData();
                    mcqFragment fragment =  SD.SetMCQFragmentData(new DataBaseHelper(MainActivity.getAppContext()), Ativityposition);

                    try{
                        int value1=0;
                        String value2=" ";
                        String[] StringArray = {};

                        Bundle bundle = fragment.getArguments();
                        if (bundle != null) {
                           /* if (LastCorrectness==position){
                                Marks++;
                            }*/
                            value1 = bundle.getInt("VALUE1", -1);
                            value2 = bundle.getString("VALUE2", "h");
                            StringArray = bundle.getStringArray("VALUE3");
                            LastCorrectness = bundle.getInt("VALUE4", -1);
                            tv.setText("Q: " + QuestionNumber + ") " + value2);
                            //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.second_layout, StringArray);
                            adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, StringArray);
                            MainActivity.GetProgressBar().setProgress(QuestionNumber*10);
                            Ativityposition++;
                            QuestionNumber++;
                            answer_list_view.setAdapter(adapter);


                        }else{
                            tv.setText("Click next to start");
                        }
                    }catch (Exception ex){
                        tv.setText("Click next to start");
                    }
                }else if (QuestionNumber > 10){

                    int index, Subject_Id;
                    String Subject;
                    MainActivity.getnext_button().setVisibility(View.GONE);

                    tv.setText("Your result is");
                    myDb = new DataBaseHelper(MainActivity.getAppContext());
                    Cursor GradeCursor = myDb.getAllGradeSubjectData(5);
                    ArrayList<String> TmpArryLst = new ArrayList<String>();

                    try{
                        while (GradeCursor.moveToNext()) {
                            index = GradeCursor.getColumnIndexOrThrow("Subject_Id");
                            Subject_Id = Integer.parseInt(GradeCursor.getString(index));

                            index = GradeCursor.getColumnIndexOrThrow("Subject");
                            Subject = GradeCursor.getString(index);

                            //TmpArryLst.add(Subject);
                        }
                    }finally {
                        GradeCursor.close();
                    }

                    //String[] ResultArray = new String[TmpArryLst.size()];
                    //ResultArray = TmpArryLst.toArray(ResultArray);
                    String[] ResultArray = {Integer.toString(Marks) + " out of 10"};
                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, ResultArray);
                    answer_list_view.setAdapter(adapter);
                }

            }
        });

        answer_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (Ativityposition == 1){
                    int index, Subject_Id;
                    String Subject;
                    myDb = new DataBaseHelper(MainActivity.getAppContext());
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

                    String[] GradeArray = new String[TmpArryLst.size()];
                    GradeArray = TmpArryLst.toArray(GradeArray);
                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, GradeArray);
                    Ativityposition = 2;



                    tv.setText("Please select a subject to go");
                    answer_list_view.setAdapter(adapter);
                //}else if (Ativityposition == 2){
                }else if (Ativityposition==2){
                    int index, Year_Id;
                    String Year;
                    myDb = new DataBaseHelper(MainActivity.getAppContext());
                    Cursor GradeCursor = myDb.getPaperYear();
                    ArrayList<String> TmpArryLst = new ArrayList<String>();

                    try{
                        while (GradeCursor.moveToNext()) {
                            index = GradeCursor.getColumnIndexOrThrow("Year_Id");
                            Year_Id = Integer.parseInt(GradeCursor.getString(index));

                            index = GradeCursor.getColumnIndexOrThrow("Year");
                            Year = GradeCursor.getString(index);

                            TmpArryLst.add("Paper of year "+Year);
                        }
                    }finally {
                        GradeCursor.close();
                    }

                    String[] GradeArray = new String[TmpArryLst.size()];
                    GradeArray = TmpArryLst.toArray(GradeArray);
                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, GradeArray);
                    Ativityposition = 3;
                    tv.setText("Please select a paper from of a year");
                    answer_list_view.setAdapter(adapter);

                }else  if (Ativityposition > 2 & QuestionNumber <= 10){
                    if (Ativityposition == 3){
                        TheTimer = new CountDownTimer(30000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                MainActivity.getTextViewTimer().setTextColor(Color.RED);
                                MainActivity.getTextViewTimer().setText("Seconds remaining: " + millisUntilFinished / 1000);
                            }
                            public void onFinish() {
                                MainActivity.getTextViewTimer().setText("Done");
                                QuestionNumber = 11;
                            }
                        }.start();
                    }
                    MainActivity.getnext_button().setVisibility(View.VISIBLE);
                    //ShowData SD = new ShowData();
                    //adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, SD.SetMCQStringArrayData( new DataBaseHelper(MainActivity.getAppContext()), Ativityposition));
                    //Ativityposition++;
                    //answer_list_view.setAdapter(adapter);
                    ShowData SD = new ShowData();
                    mcqFragment fragment =  SD.SetMCQFragmentData(new DataBaseHelper(MainActivity.getAppContext()), Ativityposition);

                    try{
                        int value1=0;
                        String value2=" ";
                        String[] StringArray = {};

                        Bundle bundle = fragment.getArguments();
                        if (bundle != null) {
                            if (LastCorrectness==position){
                                Marks++;
                            }
                            value1 = bundle.getInt("VALUE1", -1);
                            value2 = bundle.getString("VALUE2", "h");
                            StringArray = bundle.getStringArray("VALUE3");
                            LastCorrectness = bundle.getInt("VALUE4", -1);
                            tv.setText("Q: " + QuestionNumber + ") " + value2);
                            //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.second_layout, StringArray);
                            adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, StringArray);
                            MainActivity.GetProgressBar().setProgress(QuestionNumber*10);
                            Ativityposition++;
                            QuestionNumber++;
                            answer_list_view.setAdapter(adapter);


                        }else{
                            tv.setText("Click next to start");
                        }
                    }catch (Exception ex){
                        tv.setText("Click next to start");
                    }
                }else if (QuestionNumber > 10){

                    int index, Subject_Id;
                    String Subject, Phone = "0";
                    MainActivity.getnext_button().setVisibility(View.GONE);

                    tv.setText("Your result is");

                    myDb = new DataBaseHelper(MainActivity.getAppContext());
                    Cursor GradeCursor = myDb.getUserPhoneNo();


                    try{
                        while (GradeCursor.moveToNext()) {
                            index = GradeCursor.getColumnIndexOrThrow("Phone");
                            Phone = GradeCursor.getString(index);
                        }
                    }finally {
                        GradeCursor.close();
                    }

                    //SMS send
                    //START
                    /*startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"
                            + "094710793080")));

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
                    intent.putExtra("sms_body", message);
                    startActivity(intent);*/

                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + Phone ) );
                    intent.putExtra( "sms_body", "Your result is" + Integer.toString(Marks) + " out of 10" );
                    startActivity( intent );

                    //END



                    //String[] ResultArray = new String[TmpArryLst.size()];
                    //ResultArray = TmpArryLst.toArray(ResultArray);
                    String[] ResultArray = {Integer.toString(Marks) + " out of 10"};
                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, ResultArray);
                    answer_list_view.setAdapter(adapter);
                }

            }
        });

        return  myView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
