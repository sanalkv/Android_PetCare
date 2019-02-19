package sanal.gmail.android.PetCare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class doctorlogin extends AppCompatActivity {

    SQLiteDatabase db;
    String username;
    String password;
    String data;
    String data1;
    //String u;
    //String p;
    TextView text2, text3, text4;
    EditText edit;
     EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorlogin);

        db = openOrCreateDatabase("mydatabase", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS doctor1(fullname varchar ,gender varchar, Username VARCHAR,email VARCHAR,password varchar,hospital varchar,specialized varchar);");

       // text2 = (TextView) findViewById(R.id.textView2);
        edit = (EditText) findViewById(R.id.editText);
        edit1 = (EditText) findViewById(R.id.editText3);
    }

    public void signup(View view) {
        Intent intent = new Intent(this , doctorsignup.class);
        startActivity(intent);
    }
        /*String s;
        boolean b;
        String dat=null;
        username = edit.getText().toString();
        password = edit1.getText().toString();



        if (!(username.matches("") || password.matches(""))) {
            Cursor dusername = db.rawQuery("select Username from doctor", null);
            if(dusername.moveToFirst()) {
                do {

                    dat = dusername.getString(dusername.getColumnIndex("Username"));
                    if (username.equals(dat)) {
                        Toast.makeText(this, "username already exist", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    b = dusername.moveToNext();
                    //dat = dusername.getString(dusername.getColumnIndex("Username"));

                } while (b);
            }

            if (!(username.equals(dat))) {
                db.execSQL("INSERT INTO doctor VALUES('" + username + "','" + password + "');");
                Toast.makeText(this, "signed up succesfully", Toast.LENGTH_SHORT).show();
            }
            dusername.close();

        } else {
            Toast.makeText(this, "please enter the required details", Toast.LENGTH_SHORT).show();
        }
    }*/
    public void login(View view)
    {

        username = edit.getText().toString();
        password = edit1.getText().toString();

        if(!(username.matches("")||password.matches(""))) {

            Cursor dusername = db.rawQuery("select Username from doctor1", null);
            Cursor dpassword = db.rawQuery("select password from doctor1", null);

            if(dusername.moveToFirst())
            {
                data = dusername.getString(dusername.getColumnIndex("Username"));
                //text3.setText(username);
                dpassword.moveToFirst();
                data1 = dpassword.getString(dpassword.getColumnIndex("password"));
                //text4.setText(password);

                //dusername.close();
                //dpassword.close();

                // if (p && q) {
                // do {
                /*data = dusername.getString(dusername.getColumnIndex("Username"));
                data1 = dpassword.getString(dpassword.getColumnIndex("Username"));
                text3.setText(data);
                text4.setText(data1);*/
                while (true) {
                    if (username.equals(data)) {
                        if (password.equals(data1)) {
                            //Toast.makeText(this, "logged in succesfully", Toast.LENGTH_SHORT).show();
                           try {
                               Intent intent = new Intent(this, doctor.class);
                               Bundle bundle = new Bundle();
                               bundle.putString("username", username);
                               intent.putExtras(bundle);
                               startActivity(intent);
                               break;
                           }
                           catch(Exception e)
                           {
                               Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                           }
                        } else {
                            Toast.makeText(this, "password is wrong", Toast.LENGTH_SHORT).show();
                            break;
                        }

                    } else if (dusername.moveToNext()) {
                        data = dusername.getString(dusername.getColumnIndex("Username"));
                        dpassword.moveToNext();
                        data1 = dpassword.getString(dpassword.getColumnIndex("password"));
                    } else {
                        Toast.makeText(this, "username incorrect", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                // }while ((dusername.moveToNext() && dpassword.moveToNext()));

                dusername.close();
                dpassword.close();
            }
            else
            {
                Toast.makeText(this, "please sign up before continuing ", Toast.LENGTH_SHORT).show();
            }
        }


        else
        {
            Toast.makeText(this, "Please enter the required details", Toast.LENGTH_SHORT).show();
        }

       /* if (dpassword.moveToFirst()){
            do{
                 data1 = dpassword.getString(dpassword.getColumnIndex("Username"));
                // do what ever you want here
            }while(dpassword.moveToNext());
        }

        */


    }
    /*public void delete(View view)
    {
        db.execSQL("delete from doctor");
        Toast.makeText(this, "database cleared", Toast.LENGTH_SHORT).show();
    }*/
}

