package lk.candelalearning.candelalearning;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tharu on 4/20/2018.
 */

public class Persons implements Parcelable {

    List<Person> pers;

    Persons(List<Person> p){
        this.pers = p;
    }

    public List<Person> getPersonData(){
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

    protected Persons(Parcel in) {
        this.pers = new ArrayList<Person>();
        in.readList(this.pers, Person.class.getClassLoader());
    }

    public static final Parcelable.Creator<Persons> CREATOR = new Parcelable.Creator<Persons>() {
        @Override
        public Persons createFromParcel(Parcel source) {
            return new Persons(source);
        }

        @Override
        public Persons[] newArray(int size) {
            return new Persons[size];
        }
    };
}
