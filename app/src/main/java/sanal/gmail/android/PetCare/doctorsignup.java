package sanal.gmail.android.PetCare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class doctorsignup extends AppCompatActivity {

    String username;
    String password;
    String fullname;
    String email;
    String hospital;
    String specialized ;

    EditText edit;
    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;


    RadioGroup rb;
    RadioButton rb1;
    String gender;


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorsignup);

         db = openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);
        edit = (EditText) findViewById(R.id.editText2);
        edit1 = (EditText) findViewById(R.id.editText4);
        edit2 = (EditText) findViewById(R.id.editText15);
        edit3 = (EditText) findViewById(R.id.editText11);
        edit4 = (EditText) findViewById(R.id.editText12);
        edit5 = (EditText) findViewById(R.id.editText13);

        rb =(RadioGroup) findViewById(R.id.radioGroup);


    }

    public void signup(View view) {
        String s;
        boolean b;
        String dat = null;
        username = edit.getText().toString();
        password = edit1.getText().toString();
        fullname = edit2.getText().toString();
        email = edit3.getText().toString();
        hospital = edit4.getText().toString();
        specialized = edit5.getText().toString();




        if (!(username.matches("") || password.matches("") || fullname.matches("") || email.matches("") || hospital.matches("") || specialized.matches("") || rb.getCheckedRadioButtonId()==-1 )) {

            int selectedID= rb.getCheckedRadioButtonId();
            rb1=(RadioButton)findViewById(selectedID);
            gender= (String) rb1.getText();


            Cursor dusername = db.rawQuery("select Username from doctor1", null);
            if (dusername.moveToFirst()) {
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
                db.execSQL("INSERT INTO doctor1 VALUES('"+fullname+"','"+gender+"','" + username + "','"+email+"','" + password + "','"+hospital+"','"+specialized+"');");
                Toast.makeText(this, "signed up succesfully , please login to continue.. ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this , doctorlogin.class);
                startActivity(intent);
            }
            dusername.close();

        } else {
            Toast.makeText(this, "please enter the required details", Toast.LENGTH_SHORT).show();
        }
    }

}