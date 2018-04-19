package lk.candelalearning.candelalearning;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tharu on 4/19/2018.
 */

public class SubjectFragment extends Fragment {

        View myView;
        ListView answer_list_view;
        ArrayAdapter adapter;
        DataBaseHelper myDb;
        TextView tv ;
        CountDownTimer TheTimer;
        private List<Person> persons;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
        savedInstanceState) {
        myView = inflater.inflate(R.layout.subject_fragment, container, false);
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
                MainActivity.getTextViewTimer().setText("Lets Start");

            }else{
                tv.setText("Click next to start");
            }
        }catch (Exception ex){
            tv.setText("Click next to start");
        }

        answer_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                int index, Year_Id;
                String Year, Subject = "Please select a paper from of a year";
                myDb = new DataBaseHelper(MainActivity.getAppContext());
                Cursor GradeCursor = myDb.getPaperYear();
                persons = new ArrayList<>();

                answer_list_view.setVisibility(View.GONE);

                try {
                    while (GradeCursor.moveToNext()) {
                        index = GradeCursor.getColumnIndexOrThrow("Year_Id");
                        Year_Id = Integer.parseInt(GradeCursor.getString(index));

                        index = GradeCursor.getColumnIndexOrThrow("Year");
                        Year = GradeCursor.getString(index);

                        persons.add(new Person("Paper of year " + Year, Year_Id));
                    }
                } finally {
                    GradeCursor.close();
                }

                PaperYearFragment Frag = new PaperYearFragment();
                Bundle arguments = new Bundle();
                arguments.putString("VALUE2", Subject);
                //arguments.putSerializable("VALUE3", (Serializable) persons);
                Persons pp = new Persons(persons);
                arguments.putParcelable("VALUE3", pp);
                Frag.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame , Frag, "TAG_FRAGMENT").commit();


            }
        });
        return  myView;
    }

}
