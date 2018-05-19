package lk.candelalearning.candelalearning;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tharu on 4/19/2018.
 */

public class GradeFragment extends Fragment {

        View myView;
        ListView answer_list_view;
        ArrayAdapter adapter;
        TextView tv ;
        CountDownTimer TheTimer;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
        savedInstanceState) {
        myView = inflater.inflate(R.layout.grade_fragment, container, false);
        tv = (TextView) myView.findViewById(R.id.textView2);
        answer_list_view = (ListView) myView.findViewById(R.id.answer_list_view);
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
                adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, StringArray);
                answer_list_view.setAdapter(adapter);
                MainActivity.getnext_button().setVisibility(View.GONE);
                MainActivity.GetProgressBar().setProgress(0);
                //MainActivity.getTextViewTimer().setText("Lets Start");
            }else{
                tv.setText("Click next to start");
            }
        }catch (Exception ex){
            tv.setText("Some Error Occured Please Restart Again");
        }

            answer_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
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
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame , Frag, "SubjectFragment").addToBackStack("SubjectFragment").commit();
                }
            });
            return  myView;
    }
}

