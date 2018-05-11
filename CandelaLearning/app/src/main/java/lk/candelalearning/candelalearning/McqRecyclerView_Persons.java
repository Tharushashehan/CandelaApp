package lk.candelalearning.candelalearning;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tharu on 4/20/2018.
 */

public class McqRecyclerView_Persons implements Parcelable {

    List<McqRecyclerView_Person> pers;

    McqRecyclerView_Persons(List<McqRecyclerView_Person> p){
        this.pers = p;
    }

    public List<McqRecyclerView_Person> getPersonData(){
        return pers;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.pers);
    }

    protected McqRecyclerView_Persons(Parcel in) {
        this.pers = new ArrayList<McqRecyclerView_Person>();
        in.readList(this.pers, Person.class.getClassLoader());
    }

    public static final Creator<McqRecyclerView_Persons> CREATOR = new Creator<McqRecyclerView_Persons>() {
        @Override
        public McqRecyclerView_Persons createFromParcel(Parcel source) {
            return new McqRecyclerView_Persons(source);
        }

        @Override
        public McqRecyclerView_Persons[] newArray(int size) {
            return new McqRecyclerView_Persons[size];
        }
    };
}
