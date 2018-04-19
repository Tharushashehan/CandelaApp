package lk.candelalearning.candelalearning;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tharu on 4/17/2018.
 */

class Person {
    String name;
    //String age;
    //int photoId;
    int switch_Paper_status;

    Person(String name, int switch_Paper_status) {
        this.name = name;
        //this.age = age;
        //this.photoId = photoId;
        this.switch_Paper_status = switch_Paper_status;
    }
/*
    static List<Person> persons;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.candelalogo));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.next));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.next2));
    }

    public static List<Person> returnPersons(){
        return Person.persons;
    }*/
}

