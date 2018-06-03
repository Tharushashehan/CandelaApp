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
import android.support.v7.widget.CardView;
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

public class Main2Activity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        super.onCreate(savedInstanceState);
        View myView = inflater.inflate(R.layout.activity_main2, container, false);

        CardView card_view04 = (CardView) myView.findViewById(R.id.card_view04);
        final DataBaseHelper myDb = new DataBaseHelper(MainLoadFirstActivity.getAppContext());
        final FragmentManager fragmentManager = getFragmentManager();

        card_view04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Integer FirstArrayIndex =0;
                    int VALUE1 = 0;
                    int index, Grade_Id;
                    String Grade, QuestionData = "These are the Grades, Please Select Relevent Grade";


                    Cursor GradeCursor = myDb.getAllGradeData();
                    ArrayList<String> TmpArryLst = new ArrayList<String>();

                    try{
                        while (GradeCursor.moveToNext()) {
                            index = GradeCursor.getColumnIndexOrThrow("Grade_Id");
                            Grade_Id = Integer.parseInt(GradeCursor.getString(index));

                            index = GradeCursor.getColumnIndexOrThrow("Grade");
                            Grade = GradeCursor.getString(index);

                            TmpArryLst.add("Grade "+Grade);
                        }
                    }finally {
                        GradeCursor.close();
                    }

                    String[] GradeArray = new String[TmpArryLst.size()];
                    GradeArray = TmpArryLst.toArray(GradeArray);
                    //mcqFragment MCQFragment = new mcqFragment();
                    GradeFragment GradeFrag = new GradeFragment();
                    Bundle arguments = new Bundle();
                    arguments.putInt("VALUE1", VALUE1);
                    arguments.putString("VALUE2", QuestionData);
                    arguments.putStringArray("VALUE3", GradeArray); //bundle.getStringArray("VALUE3");
                    GradeFrag.setArguments(arguments);
                    fragmentManager.beginTransaction().replace(R.id.content_frame , GradeFrag, "GradeFragment").commit();
            }
        });
        return  myView;
    }

}
