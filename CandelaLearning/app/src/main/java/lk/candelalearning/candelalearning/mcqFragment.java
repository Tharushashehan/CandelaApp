package lk.candelalearning.candelalearning;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tharusha Shehan on 4/7/2018.
 */
public class mcqFragment extends Fragment{


    View myView;
    ListView answer_list_view;
    ArrayAdapter adapter;
    DataBaseHelper myDb;
    int Ativityposition = 2;
    TextView tv ;
    int QuestionNumber = 1;
    int Marks = 0;
    int LastCorrectness = 35;
    CountDownTimer TheTimer;
    FloatingActionButton fab_select;
    int position;
    boolean selected = false;
    private List<Person> persons;
    boolean SMSViewed = false;
    int SELECTEDPAPER;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mcq, container, false);
        tv = (TextView) myView.findViewById(R.id.textView2);
        answer_list_view = (ListView) myView.findViewById(R.id.answer_list_view);
        fab_select = (FloatingActionButton) myView.findViewById(R.id.fab_select);
        //MainActivity.getTextViewTimer().setVisibility(View.VISIBLE);
//        MainActivity.GetProgressBar().setVisibility(View.VISIBLE);


        try{

            TheTimer = new CountDownTimer(30000, 1000) {
                public void onTick(long millisUntilFinished) {

                    //MainActivity.getTextViewTimer().setText("Seconds: " + millisUntilFinished / 1000);
                }
                public void onFinish() {
                    //MainActivity.getTextViewTimer().setText("Done");
                    QuestionNumber = 11;
                }
            }.start();

            fab_select.setVisibility(View.VISIBLE);

            int value1=0;
            String value2=" ";
            String[] StringArray = {};

            Bundle bundle = this.getArguments();
            if (bundle != null) {

                    value1 = bundle.getInt("VALUE1", -1);
                    value2 = bundle.getString("VALUE2", "h");
                    StringArray = bundle.getStringArray("VALUE3");
                    LastCorrectness = bundle.getInt("VALUE4", -1);
                    tv.setText("Q: " + QuestionNumber + ") " + value2);

                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, StringArray);
//                    MainActivity.GetProgressBar().setProgress(QuestionNumber*10);
                    Ativityposition++;
                    QuestionNumber++;
                    answer_list_view.setAdapter(adapter);

            }else{
                tv.setText("Click next to start");
            }
        }catch (Exception ex){
            tv.setText("Click next to start");
        }

//        MainActivity.getnext_button().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (Ativityposition >= 2 & QuestionNumber <= 10){
//                    ShowData SD = new ShowData();
//                    mcqFragment fragment =  SD.SetMCQFragmentData(new DataBaseHelper(MainActivity.getAppContext()), Ativityposition);
//                    try{
//                        int value1=0;
//                        String value2=" ";
//                        String[] StringArray = {};
//
//                        Bundle bundle = fragment.getArguments();
//                        if (bundle != null) {
//
//                            value1 = bundle.getInt("VALUE1", -1);
//                            value2 = bundle.getString("VALUE2", "h");
//                            StringArray = bundle.getStringArray("VALUE3");
//                            LastCorrectness = bundle.getInt("VALUE4", -1);
//                            tv.setText("Q: " + QuestionNumber + ") " + value2);
//
//                            adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, StringArray);
//                            MainActivity.GetProgressBar().setProgress(QuestionNumber*10);
//                            Ativityposition++;
//                            QuestionNumber++;
//                            answer_list_view.setAdapter(adapter);
//                            selected = false;
//                        }else{
//                            tv.setText("Click next to start");
//                        }
//                    }catch (Exception ex){
//                        tv.setText("Click next to start");
//                    }
//                }else if (QuestionNumber > 10){
//
//                    int index, Subject_Id;
//                    String Subject, Phone="001";
////                    MainActivity.getnext_button().setVisibility(View.GONE);
//                    tv.setText("Your result is " + Integer.toString(Marks) + " out of 10");
//                    answer_list_view.setAdapter(null);
//                    myDb = new DataBaseHelper(MainActivity.getAppContext());
//                    Cursor GradeCursor = myDb.getUserPhoneNo();
//
//                    try{
//                        while (GradeCursor.moveToNext()) {
//                            index = GradeCursor.getColumnIndexOrThrow("Phone");
//                            Phone = GradeCursor.getString(index);
//                        }
//                    }finally {
//                        GradeCursor.close();
//                    }
//                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + Phone ) );
//                    intent.putExtra( "sms_body", "Your result is" + Integer.toString(Marks) + " out of 10" );
//                    startActivity( intent );
//                    SMSViewed = true;
//                }
//            }
//        });

        answer_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (Ativityposition >= 2){
                    selected = true;
                    answer_list_view.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    answer_list_view.setSelector(android.R.color.holo_green_dark);
                    answer_list_view.setSelector(android.R.color.background_dark);
                }
            }
        });


        fab_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (Ativityposition >=2 & QuestionNumber <= 10){

                        if(selected){
                            ShowData SD = new ShowData();
                            mcqFragment fragment =  SD.SetMCQFragmentData(new DataBaseHelper(MainLoadFirstActivity.getAppContext()), Ativityposition, SELECTEDPAPER);

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

                                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, StringArray);
//                                    MainActivity.GetProgressBar().setProgress(QuestionNumber*10);
                                    Ativityposition++;
                                    QuestionNumber++;
                                    answer_list_view.setAdapter(adapter);
                                    selected = false;
                                }else{
                                    tv.setText("Click next to start");
                                }
                            }catch (Exception ex){
                                tv.setText("Click next to start");
                            }
                        }else {
                            Toast.makeText(myView.getContext(), "Please select an answer first", Toast.LENGTH_LONG).show();
                        }

                    }else if (QuestionNumber > 10){

                        int index, Subject_Id;
                        String Subject, Phone="001";
//                        MainActivity.getnext_button().setVisibility(View.GONE);
                        tv.setText("Your result is " + Integer.toString(Marks) + " out of 10");
                        answer_list_view.setAdapter(null);
                        myDb = new DataBaseHelper(MainLoadFirstActivity.getAppContext());
                        Cursor GradeCursor = myDb.getUserPhoneNo();

                        try{
                            while (GradeCursor.moveToNext()) {
                                index = GradeCursor.getColumnIndexOrThrow("Phone");
                                Phone = GradeCursor.getString(index);
                            }
                        }finally {
                            GradeCursor.close();
                        }
                        if(!SMSViewed){
                            Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( "sms:" + Phone ) );
                            intent.putExtra( "sms_body", "Your result is" + Integer.toString(Marks) + " out of 10" );
                            startActivity( intent );
                            SMSViewed = true;
                        }else {
                            //Here we move again to papers
                            //START
                            int Year_Id;
                            String Year;
                            Subject = "Please select a paper from of a year";
                            myDb = new DataBaseHelper(MainLoadFirstActivity.getAppContext());
                            Cursor GradeCursorForPaperYear = myDb.getPaperYear();
                            persons = new ArrayList<>();

                            answer_list_view.setVisibility(View.GONE);

                            try {
                                while (GradeCursorForPaperYear.moveToNext()) {
                                    index = GradeCursorForPaperYear.getColumnIndexOrThrow("Year_Id");
                                    Year_Id = Integer.parseInt(GradeCursorForPaperYear.getString(index));

                                    index = GradeCursorForPaperYear.getColumnIndexOrThrow("Year");
                                    Year = GradeCursorForPaperYear.getString(index);

                                    persons.add(new Person("Paper of year " + Year, Year_Id));
                                }
                            } finally {
                                GradeCursorForPaperYear.close();
                            }

                            PaperYearFragment Frag = new PaperYearFragment();
                            Bundle arguments = new Bundle();
                            arguments.putString("VALUE2", Subject);
                            //arguments.putSerializable("VALUE3", (Serializable) persons);
                            Persons pp = new Persons(persons);
                            arguments.putParcelable("VALUE3", pp);
                            Frag.setArguments(arguments);
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.content_frame, Frag, "PaperYearFragment").addToBackStack("PaperYearFragment").commit();
                            //END
//                            MainActivity.GetProgressBar().setVisibility(View.GONE);
                            //MainActivity.getTextViewTimer().setVisibility(View.GONE);
                        }
                    }
            }
        });
        return  myView;
    }
}
