package lk.candelalearning.candelalearning;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Hashtable;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.DatabaseUtils;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.sqlite.SQLiteDatabase;

/**
 * Created by tharu on 4/7/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME="Student";
   /* public static final String TABLE_NAME="Student_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="AGE";*/

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //SQLiteDatabase db =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(String.format("create table %s(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, AGE TEXT)", TABLE_NAME));
        db.execSQL(String.format("create table User (Ref_Id integer primary key AUTOINCREMENT, User_Id integer, Name text, Phone text)"));
        db.execSQL(String.format("create table Result (Ref_Id integer primary key AUTOINCREMENT, Attempt_Id integer, User_Id integer, Ppr_No integer, Marks integer, Time_Value text)"));
        db.execSQL(String.format("create table Question (Ref_Id integer primary key AUTOINCREMENT, Question_Id integer, Ppr_No integer, User_Id integer, Time integer, Question text)"));
        db.execSQL(String.format("create table Answer (Ref_Id integer primary key AUTOINCREMENT, Answer_Id integer, Question_Id integer, Answer text, Correct integer)"));

        //Newly added table
        db.execSQL(String.format("create table Grade (Ref_Id integer primary key AUTOINCREMENT, Grade_Id integer, Grade integer)"));
        db.execSQL(String.format("create table Subject (Ref_Id integer primary key AUTOINCREMENT, Subject_Id integer, Grade_Id integer, Subject text)"));
        db.execSQL(String.format("create table Paper_Year (Ref_Id integer primary key AUTOINCREMENT, Year_Id integer, Subject_Id integer, Paper_No integer, Year integer)"));

        //Newly added image table
        db.execSQL(String.format("create table Question_Img (Ref_Id integer primary key AUTOINCREMENT, img_Id integer, photo_Id integer,  img_Name text, Paper_Year_Id integer, Question_Id integer)"));
        db.execSQL(String.format("create table Answr_Img (Ref_Id integer primary key AUTOINCREMENT, img_Id integer, photo_Id integer,  img_Name text, Paper_Year_Id integer, Question_Id integer, Answer_Id integer )"));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS User"));
        db.execSQL(String.format("DROP TABLE IF EXISTS Result"));
        db.execSQL(String.format("DROP TABLE IF EXISTS Question"));
        db.execSQL(String.format("DROP TABLE IF EXISTS Answer"));

        db.execSQL(String.format("DROP TABLE IF EXISTS Grade"));
        db.execSQL(String.format("DROP TABLE IF EXISTS Subject"));
        db.execSQL(String.format("DROP TABLE IF EXISTS Paper_Year"));

        db.execSQL(String.format("DROP TABLE IF EXISTS Question_Img"));
        db.execSQL(String.format("DROP TABLE IF EXISTS Answr_Img"));

        onCreate(db);
    }

    public  boolean insertQuestion_Img ( Integer img_Id, Integer photo_Id, String img_Name, Integer  Paper_Year_Id, Integer Question_Id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("img_Id", img_Id);
        contentValues.put("photo_Id", photo_Id);
        contentValues.put("img_Name", img_Name);
        contentValues.put("Paper_Year_Id", Paper_Year_Id);
        contentValues.put("Question_Id", Question_Id);
        db.insert("Question_Img", null, contentValues);
        return true;
    }

    public  boolean insertAnswer_Img (Integer img_Id , Integer photo_Id, String img_Name , Integer Paper_Year_Id , Integer Question_Id , Integer Answer_Id ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("img_Id", img_Id);
        contentValues.put("photo_Id", photo_Id);
        contentValues.put("img_Name", img_Name);
        contentValues.put("Paper_Year_Id", Paper_Year_Id);
        contentValues.put("Question_Id", Question_Id);
        contentValues.put("Answer_Id", Answer_Id);
        db.insert("Answr_Img", null, contentValues);
        return true;
    }

    public Cursor getImgDataForQuestionId(int Qid, int Paper_Year_Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Question_Img where Question_Id="+Qid+" and Paper_Year_Id = " +Paper_Year_Id+" ", null );
        return res;
    }

    public Cursor getImgDataForAnswerId(int Qid, int Paper_Year_Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Answr_Img where Question_Id="+Qid+" and Paper_Year_Id = " +Paper_Year_Id+" ", null );
        return res;
    }

    public boolean insertUser ( Integer User_Id, String Name, String  Phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("User_Id", User_Id);
        contentValues.put("Name", Name);
        contentValues.put("Phone", Phone);
        db.insert("User", null, contentValues);
        return true;
    }

    public boolean insertResult ( Integer Attempt_Id, Integer User_Id, Integer Ppr_No, Integer Marks, String Time_Value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Attempt_Id", Attempt_Id);
        contentValues.put("User_Id", User_Id);
        contentValues.put("Ppr_No", Ppr_No);
        contentValues.put("Marks", Marks);
        contentValues.put("Time_Value", Time_Value);
        db.insert("Result", null, contentValues);
        return true;
    }

    public boolean insertQuestion ( Integer Question_Id, Integer Ppr_No, Integer User_Id, Integer Time, String Question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Question_Id", Question_Id);
        contentValues.put("Ppr_No", Ppr_No);
        contentValues.put("User_Id", User_Id);
        contentValues.put("Time", Time);
        contentValues.put("Question", Question);
        db.insert("Question", null, contentValues);
        return true;
    }

    public boolean insertAnswer ( Integer Answer_Id, Integer Question_Id, String Answer, Integer Correct) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Answer_Id", Answer_Id);
        contentValues.put("Question_Id", Question_Id);
        contentValues.put("Answer", Answer);
        contentValues.put("Correct", Correct);
        db.insert("Answer", null, contentValues);
        return true;
    }

    public boolean insertGrade ( Integer Grade_Id, Integer Grade) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Grade_Id", Grade_Id);
        contentValues.put("Grade", Grade);
        db.insert("Grade", null, contentValues);
        return true;
    }

    public boolean insertSubject ( Integer Subject_Id, Integer Grade_Id, String Subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Subject_Id", Subject_Id);
        contentValues.put("Grade_Id", Grade_Id);
        contentValues.put("Subject", Subject);
        db.insert("Subject", null, contentValues);
        return true;
    }

    public boolean insertYear ( Integer Year_Id, Integer Subject_Id, Integer Paper_No, Integer Year) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Year_Id", Year_Id);
        contentValues.put("Subject_Id", Subject_Id);
        contentValues.put("Paper_No", Paper_No);
        contentValues.put("Year", Year);
        db.insert("Paper_Year", null, contentValues);
        return true;
    }

    public Cursor getAllGradeData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Grade", null );
        return res;
    }

    public Cursor getAllGradeSubjectData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Subject where Grade_Id="+id+"", null );
        return res;
    }


    public Cursor getAllQuestionData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Question where Question_Id="+id+"", null );
        return res;
    }

    public Cursor getAnswerDataForQuestionId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Answer where Question_Id="+id+"", null );
        return res;
    }

    public Cursor getUserPhoneNo() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select Phone from User", null );
        return res;
    }

    public Cursor getPaperYear() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Paper_Year ", null );
        return res;
    }

/*    public String[] getQuestionData(int id, String TName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor restt =  db.rawQuery( "select * from " + TName +" where Question_Id="+id+"", null );
        String[] res = db.rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});
        return res;
    }*/



    public int numberOfRows(String TName){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TName);
        return numRows;
    }

    public Integer deleteTableDataRow (Integer id, String TName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TName,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
}
