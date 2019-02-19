package sanal.gmail.android.PetCare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class petownerlogin extends AppCompatActivity {

    SQLiteDatabase db1;
    String username;
    String password;
    EditText edit1;
    EditText edit;
    String data;
    String data1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petownerlogin);

        db1 = openOrCreateDatabase("mydatabase", MODE_PRIVATE, null);
        db1.execSQL("CREATE TABLE IF NOT EXISTS petowner2(petname varchar ,breed varchar ,colour varchar,age varchar , gender1 varchar , phonenumber varchar , address varchar , fullname varchar,gender varchar,Username VARCHAR,email varchar,password VARCHAR,hospital varchar,doctor varchar);");

        edit = (EditText) findViewById(R.id.editText);
        edit1 = (EditText) findViewById(R.id.editText3);



    }

    public void signup(View view) {
        Intent intent = new Intent(this , petownersignup.class);
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

            Cursor dusername = db1.rawQuery("select Username from petowner2", null);
            Cursor dpassword = db1.rawQuery("select password from petowner2", null);

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
                            Toast.makeText(this, "logged in succesfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this, pettable.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("username", username);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;

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
        db1.execSQL("delete from petowner");
        Toast.makeText(this, "database cleared", Toast.LENGTH_SHORT).show();
    }*/

}
