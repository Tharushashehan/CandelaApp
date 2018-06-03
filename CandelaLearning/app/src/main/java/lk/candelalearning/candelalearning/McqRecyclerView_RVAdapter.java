package lk.candelalearning.candelalearning;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import static java.security.AccessController.getContext;

/*
*
 * Created by tharu on 4/17/2018.

*/


public class McqRecyclerView_RVAdapter extends RecyclerView.Adapter<McqRecyclerView_RVAdapter.PersonViewHolder>{

    private int selectedPosition = 10;

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;

        ImageView img;
        //Switch switch_Paper_status;
        //TextView personAge;
        //ImageView personPhoto;



        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            img = (ImageView)itemView.findViewById(R.id.imageViewAnswer);
            //switch_Paper_status = (Switch)itemView.findViewById(R.id.switch_Paper_status);
            //personAge = (TextView)itemView.findViewById(R.id.person_age);
            //personPhoto = (ImageView)itemView.findViewById(R.id.person_photo);

        }


    }
    List<McqRecyclerView_Person> persons;

    McqRecyclerView_RVAdapter(List<McqRecyclerView_Person> persons){
        this.persons = persons;
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_mcq_list_item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, final int i) {
        personViewHolder.personName.setText(persons.get(i).name);
        //String my_image = persons.get(i).photoName;
        if(persons.get(i).photoName =="candelalogo"){
            personViewHolder.img.setImageResource(android.R.color.transparent);
        }else{
            int resID = MainLoadFirstActivity.getAppContext().getResources().getIdentifier( persons.get(i).photoName , "drawable", MainLoadFirstActivity.getAppContext().getPackageName());
            personViewHolder.img.setImageResource(resID);
        }

        //personViewHolder.img.setImageResource(R.drawable.candelalogo);
        //personViewHolder.switch_Paper_status.setText(persons.get(i).switch_Paper_status);
        //personViewHolder.personAge.setText(persons.get(i).age);
        //personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
        //personViewHolder.itemView.setBackgroundColor(Color.parseColor("#000000"));

        if(selectedPosition==i){
            personViewHolder.itemView.setBackgroundColor(Color.parseColor("#000000"));
            personViewHolder.personName.setTextColor(Color.parseColor("#ff0000"));
        }
        else{
            personViewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            personViewHolder.personName.setTextColor(Color.parseColor("#000000"));
        }

        personViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=i;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
