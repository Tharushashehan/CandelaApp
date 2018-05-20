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
    Bundle arguments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_instruction, container, false);
        fab_select = (FloatingActionButton) myView.findViewById(R.id.fab_select);
        arguments = this.getArguments();
        fab_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                McqRecyclerView_mcqFragment Frag = new McqRecyclerView_mcqFragment();
                Frag.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame , Frag, "MCQFragment").addToBackStack("MCQFragment").commit();
                MainActivity.getnext_button().setVisibility(View.VISIBLE);
            }
            });
        return  myView;
    }
}
