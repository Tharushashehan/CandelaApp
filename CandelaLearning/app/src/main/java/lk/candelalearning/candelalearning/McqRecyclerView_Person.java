package lk.candelalearning.candelalearning;

/**
 * Created by tharu on 4/17/2018.
 */

class McqRecyclerView_Person implements java.io.Serializable {
    String name;
    //String age;
    int photoId;
    //String photoName;
    int switch_Paper_status;

    McqRecyclerView_Person(String name, int switch_Paper_status, int photoId) {
        this.name = name;
        //this.photoName = photoName;
        //this.age = age;
        this.photoId = photoId;
        this.switch_Paper_status = switch_Paper_status;
    }

}

