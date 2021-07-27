package surajitascreations.epizy.sqlitedb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    databasehelper mydb;
    Button buttonadd,buttondelete,buttongetalldata,buttonupdate,buttonview,deleteall;
    EditText editTextid,editTextname,editTextemail,editTextcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb=new databasehelper(this);

        buttonadd=findViewById(R.id.button_add);
        buttondelete=findViewById(R.id.button_delete);
        buttongetalldata=findViewById(R.id.button_viewAll);
        buttonupdate=findViewById(R.id.button_update);
        buttonview=findViewById(R.id.button_view);
        deleteall=findViewById(R.id.button_deleteall);

        editTextcc=findViewById(R.id.editText_CC);
        editTextemail=findViewById(R.id.editText_email);
        editTextid=findViewById(R.id.editText_id);
        editTextname=findViewById(R.id.editText_name);

        Adddata();
        getdata();
        getalldata();
        updatedata();
        delete();







    }

    public void Adddata(){
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id=editTextid.getText().toString();
                String name=editTextname.getText().toString();
                String email=editTextemail.getText().toString();
                String Coursecount=editTextcc.getText().toString();
                boolean isinserted=mydb.insertdata(id,name,email,Coursecount);


                if (isinserted==true){
                    Toast.makeText(MainActivity.this,"Data inserted successfully!!",Toast.LENGTH_SHORT).show();
                }
                else
                {Toast.makeText(MainActivity.this,"Some thing went wrong!!",Toast.LENGTH_SHORT).show();}
                //Toast.makeText(MainActivity.this,"Testing done successfully",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void getdata(){
        buttonview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editTextid.getText().toString();
                if (id.equals(String.valueOf(""))){
                    editTextid.setError("Enter ID");
                    return;
                }
                Cursor cursor=mydb.getdata(id);
                String data=null;
                if (cursor.moveToNext()){
                    data="ID: "+cursor.getString(0)+"\n"
                            +"Name: "+cursor.getString(1)+"\n"
                            +"Email: "+cursor.getString(2)+"\n"
                            +"Course Count: "+cursor.getString(3);
                }
                showmessage("Data Entered",data);
            }
        });

    }

    public void getalldata(){
        buttongetalldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=mydb.getalldata();
                //Safety and small check
                if (cursor.getCount()==0){
                    showmessage("Error!","Nothing found inside data base");
                    return;
                }
                StringBuffer buffer=new StringBuffer();

                    while (cursor.moveToNext()){

                        buffer.append("ID: "+cursor.getString(0)+"\n");
                        buffer.append("Name: "+cursor.getString(1)+"\n");
                        buffer.append("Email: "+cursor.getString(2)+"\n");
                        buffer.append("CourseCount: "+cursor.getString(3)+"\n\n");
                    }


                showmessage("All data",buffer.toString());
            }
        });
    }

    public void updatedata(){
        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=editTextid.getText().toString();
                String name=editTextname.getText().toString();
                String email=editTextemail.getText().toString();
                String Coursecount=editTextcc.getText().toString();
                boolean isupdate=mydb.updatedata(id,name,email,Coursecount);
                if (isupdate==true){


                    Toast.makeText(MainActivity.this,"Updated successfully!",Toast.LENGTH_SHORT).show();

                }
                else {

                    Toast.makeText(MainActivity.this,"Something went wrong!",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void delete(){
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((editTextid.getText().toString()).isEmpty()){
                    editTextid.setError("No data is inserted!!");
                    Toast.makeText(MainActivity.this, "Dont just mess up the app", Toast.LENGTH_SHORT).show();
                    return;
                }

                Integer id=mydb.deletedata(editTextid.getText().toString());
                if (id>0){
                    Toast.makeText(MainActivity.this,"Data deleted Successfully!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Something went Wrong in deletion", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void deletealldataplease(View view){

        Toast.makeText(MainActivity.this,"Auro You have successfully dleleted",Toast.LENGTH_SHORT).show();
        mydb.deleteAlldatafromAurodb();
    }



    private void showmessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



//    public void deletealldataplease(View view) {
//        Cursor cursor=mydb.deletealldata(); //Safety and small check
//        if (cursor.getCount()==0){
//            showmessage("Error!","EveryThing is deleted!!");
//            return;
//        }
//
//
//        while(cursor.moveToNext()){
//            StringBuffer buffer=new StringBuffer(cursor.getString(i));
//            buffer.delete(cursor.getString(1).charAt(0),cursor.
//            getString(1).length());
//            buffer.delete(cursor.getString(2).charAt(0),cursor.
//            getString(2).length());
//            buffer.delete(cursor.getString(3).charAt(0),cursor.
//            getString(3).length());
//            buffer.delete(cursor.getString(4).charAt(0),cursor.
//            getString(4).length());
//            }
//        }
//        showmessage("Data Info","Empty");
//
//
//    }
}



//A SQL cursor is a set of rows together with a pointer that identifies a current row.