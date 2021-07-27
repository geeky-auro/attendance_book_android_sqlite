package surajitascreations.epizy.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databasehelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME="My_students.db";
    public final static String TABLE_NAME="Mystudents_table";
    public final static String Col_1="ID";
    public final static String Col_2="NAME";
    public final static String Col_3="EMAIL";
    public final static String Col_4="COURSE_COUNT";




    public databasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+
                TABLE_NAME+
                " (ID INTEGER ," +
                "NAME TEXT," +
                "EMAIL TEXT," +
                "COURSE_COUNT INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertdata(String id,String name,String email,String coursecount){
        SQLiteDatabase dbase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(Col_1,id);
        contentValues.put(Col_2,name);
        contentValues.put(Col_3,email);
        contentValues.put(Col_4,coursecount);

        long result=dbase.insert(TABLE_NAME,null,contentValues);

        if (result==-1){
            return false;
        }else
        {
            return true;
        }


    }

    public boolean updatedata(String id,String name,String email,String coursecount){

        SQLiteDatabase dbase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(Col_1,id);
        contentValues.put(Col_2,name);
        contentValues.put(Col_3,email);
        contentValues.put(Col_4,coursecount);

        dbase.update(TABLE_NAME,contentValues,"ID=?", new String[]{id});
        return true;


    }

    public Cursor getdata(String id){
        SQLiteDatabase dbase=this.getWritableDatabase();/*Get me an instance of database*/
        String query="SELECT * FROM "+TABLE_NAME+" WHERE ID='"+id+"'";

        Cursor cursor=dbase.rawQuery(query,null);

        return cursor;

    }

    public int deletedata(String id){
        SQLiteDatabase dbase=this.getWritableDatabase();

        int delData=dbase.delete(TABLE_NAME,"ID=?",new String[]{id});
        return delData;

    }

    public Cursor getalldata(){
        SQLiteDatabase dbase=this.getWritableDatabase();
        Cursor cursor=dbase.rawQuery
                ("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }


    public void deleteAlldatafromAurodb()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();

    }
}





