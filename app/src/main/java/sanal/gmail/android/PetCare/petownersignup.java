package sanal.gmail.android.PetCare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class petownersignup extends AppCompatActivity {
    SQLiteDatabase db1;
    Spinner spinner;
    ArrayList<String> ls = new ArrayList<>();
    boolean b;



    EditText edit1,edit,edit2,edit3,edit4,edit5,edit6,edit7,edit8,edit9,edit10,edit11;


    String ss=null,username,password,fullname,Hospital,email,gender,petname,breed,colour,age,phonenumber,address,gender1,doctordata,spin;
    RadioGroup rb,rbb;
    RadioButton rb1,rb2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petownersignup);

        db1= openOrCreateDatabase("mydatabase",MODE_PRIVATE,null);
        edit = (EditText) findViewById(R.id.editText5);
        edit1 = (EditText) findViewById(R.id.editText6);
        edit2 = (EditText) findViewById(R.id.editText7);
        edit3 = (EditText) findViewById(R.id.editText8);
        edit4 = (EditText) findViewById(R.id.editText9);

        edit6 = (EditText) findViewById(R.id.editText17);
        edit7 = (EditText) findViewById(R.id.editText16);
        edit8 = (EditText) findViewById(R.id.editText14);
        edit9 = (EditText) findViewById(R.id.editText21);
        edit10 = (EditText) findViewById(R.id.editText19);
        edit11 = (EditText) findViewById(R.id.editText18);

        spinner = (Spinner) findViewById(R.id.spinner1);

        ls.add("select doctor");
        try {
            Cursor ddusername = db1.rawQuery("select Username from doctor1", null);

            if (ddusername.moveToFirst()) {
                do {
                    doctordata = ddusername.getString(ddusername.getColumnIndex("Username"));
                    ls.add(doctordata);
                    b = ddusername.moveToNext();

                } while (b);
                ddusername.close();
            }
        }

        catch (Exception e)
        {
            Toast.makeText(this, "No Doctor available  ", Toast.LENGTH_LONG).show();
        }


        ArrayAdapter<String> adapter  = new ArrayAdapter<String>(petownersignup.this,android.R.layout.simple_spinner_dropdown_item,ls);
        spinner.setAdapter(adapter);

        rb =(RadioGroup) findViewById(R.id.radioGroup);
        rbb=(RadioGroup) findViewById(R.id.radioGroup1);

    }

    public void signup(View view) {
        try{
            boolean b;
            String dat = null;
            username = edit.getText().toString();
            password = edit1.getText().toString();
            fullname = edit2.getText().toString();
            email = edit3.getText().toString();
            Hospital = edit4.getText().toString();
            spin = spinner.getSelectedItem().toString();

            petname = edit6.getText().toString();
            breed = edit7.getText().toString();
            colour = edit8.getText().toString();
            age = edit9.getText().toString();
            phonenumber = edit10.getText().toString();
            address = edit11.getText().toString();


            if (!(username.matches("") || password.matches("") || fullname.matches("") || email.matches("") || Hospital.matches("")  || (rb.getCheckedRadioButtonId() == -1) || petname.matches("") || breed.matches("") || colour.matches("") || age.matches("") || phonenumber.matches("") || address.matches("") || (rbb.getCheckedRadioButtonId() == -1) || spin.equals("select doctor"))) {

                int selectedID = rb.getCheckedRadioButtonId();
                rb1 = (RadioButton) findViewById(selectedID);
                gender = (String) rb1.getText();

                int selectedID1 = rb.getCheckedRadioButtonId();
                rb2 = (RadioButton) findViewById(selectedID1);
                gender1 = (String) rb2.getText();

                Cursor dusername = db1.rawQuery("select Username from petowner2", null);
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
                    db1.execSQL("INSERT INTO petowner2 VALUES('" + petname + "','" + breed + "','" + colour + "','" + age + "','" + gender1 + "','" + phonenumber + "','" + address + "','" + fullname + "','" + gender + "','" + username + "','" + email + "','" + password + "','" + Hospital + "','" + spin + "');");
                     Toast.makeText(this, "signed up successfully , please login to continue..", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, petownerlogin.class);
                    startActivity(intent);
                }
                dusername.close();

            } else {
                Toast.makeText(this, "please enter the required details", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
