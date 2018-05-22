package lk.candelalearning.candelalearning;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
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
import java.util.Iterator;
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

//                MainActivity.getnext_button().setVisibility(View.GONE);
//                MainActivity.GetProgressBar().setProgress(0);
                //TheTimer.cancel();
                //MainActivity.getTextViewTimer().setText("Lets Start");

            }else{
                tv.setText("Click next to start");
            }
        }catch (Exception ex){
            tv.setText("Click next to start");
        }

        answer_recycle_view.addOnItemTouchListener(
                new RecyclerItemClickListener(myView.getContext(), answer_recycle_view ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        //Going in to the instruction fragment
                        //START
                            answer_list_view.setVisibility(View.VISIBLE);
                            answer_recycle_view.setVisibility(View.GONE);
                            InstructionFragment Frag = new InstructionFragment();
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.content_frame , Frag, "InstructionFragment").addToBackStack("InstructionFragment").commit();
                        //END

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(myView.getContext(), "Don't Long Press", Toast.LENGTH_LONG).show();
                    }
                })
        );

        return  myView;
    }

}
