package lk.candelalearning.candelalearning;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainLoadFirstActivity extends AppCompatActivity {

    static Context context = null;
    Dialog dialog;
    LinearLayout DialogimageView5LinearLayAbort;
    DataBaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_load_first);
        Main2Activity mainFrag = new Main2Activity();
        MainLoadFirstActivity.context = getApplicationContext();
        myDb = new DataBaseHelper(this);
        AddData add = new AddData();
        add.dataEnter(myDb);
        dialog = new Dialog(MainLoadFirstActivity.this);
        dialog.setContentView(R.layout.dialog_layout);//popup view is the layout you created
        DialogimageView5LinearLayAbort = (LinearLayout) dialog.findViewById(R.id.imageView5LinearLay);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame , mainFrag, "GradeFragment").commit();

        DialogimageView5LinearLayAbort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Here we move again to papers
                //START
                int Year_Id, index;
                String Year;
                String Subject = "Please select a paper from of a year";
                myDb = new DataBaseHelper(MainLoadFirstActivity.getAppContext());
                Cursor GradeCursorForPaperYear = myDb.getPaperYear();
                List<Person> persons = new ArrayList<>();

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
//                MainActivity.GetProgressBar().setVisibility(View.GONE);
                //MainActivity.getTextViewTimer().setVisibility(View.GONE);
                dialog.hide();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    FragmentManager fragmentManager = getFragmentManager();
                    SubjectFragment mySubjectFragment = (SubjectFragment)getFragmentManager().findFragmentByTag("SubjectFragment");
                    PaperYearFragment myPaperYearFragment = (PaperYearFragment)getFragmentManager().findFragmentByTag("PaperYearFragment");
                    McqRecyclerView_mcqFragment myMcqFragment = (McqRecyclerView_mcqFragment)getFragmentManager().findFragmentByTag("MCQFragment");
                    InstructionFragment myInsFragmnt = (InstructionFragment)getFragmentManager().findFragmentByTag("InstructionFragment");

                    if (mySubjectFragment != null && mySubjectFragment.isVisible()) {
                        fragmentManager.beginTransaction().replace(R.id.content_frame , mySubjectFragment.SetFragmentData() , "GradeFragment").addToBackStack("GradeFragment").commit();
                    }else if (myPaperYearFragment != null && myPaperYearFragment.isVisible()) {
                        fragmentManager.beginTransaction().replace(R.id.content_frame , myPaperYearFragment.SetFragmentData() , "SubjectFragment").addToBackStack("SubjectFragment").commit();
                    }else if (myInsFragmnt != null && myInsFragmnt.isVisible()) {
                        fragmentManager.beginTransaction().replace(R.id.content_frame , myPaperYearFragment.SetFragmentData() , "SubjectFragment").addToBackStack("SubjectFragment").commit();
                    }else if (myMcqFragment != null && myMcqFragment.isVisible()){
                        Window window = dialog.getWindow();
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT); //
                        dialog.show();
                    }else{
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public static Context getAppContext() {
        return MainLoadFirstActivity.context;
    }
}
