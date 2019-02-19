package sanal.gmail.android.PetCare;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class petowner extends AppCompatActivity {

    TextView doc, hos, con, doc1, hos1, con1, msg,appointment,appointment1;
    String doctordata, Spin;
    Spinner spin;
    Boolean b;
    Boolean item ;
    SQLiteDatabase db;
    private DatePickerDialog.OnDateSetListener mData;
    ArrayList<String> ls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petowner);

        doc = findViewById(R.id.textView27);
        hos = findViewById(R.id.textView28);
        con = findViewById(R.id.textView29);
        doc1 = findViewById(R.id.textView19);
        hos1 = findViewById(R.id.textView20);
        con1 = findViewById(R.id.textView26);
        msg = findViewById(R.id.textView30);
        appointment = findViewById(R.id.textView4);
        appointment1 = findViewById(R.id.textView6);
        spin = findViewById(R.id.spinner);
        db = openOrCreateDatabase("mydatabase", MODE_PRIVATE, null);


        ls.add("select doctor");
        try {
            Cursor ddusername = db.rawQuery("select Username from doctor1", null);

            if (ddusername.moveToFirst()) {
                do {
                    doctordata = ddusername.getString(ddusername.getColumnIndex("Username"));
                    ls.add(doctordata);
                    b = ddusername.moveToNext();

                } while (b);
                ddusername.close();
            }
        } catch (Exception e) {
            Toast.makeText(this, "No Doctor available  ", Toast.LENGTH_LONG).show();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ls);
        spin.setAdapter(adapter);
        //Spin = spin.getSelectedItem().toString();
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                item = selectedItem.equals("select doctor");
                if (!(selectedItem.equals("select doctor"))) {
                    try {
                        Bundle bundle = getIntent().getExtras();
                        String pet = bundle.getString("username");
                        Cursor pcolumn = db.rawQuery("select * from doctor1", null);


                        if (pcolumn.moveToFirst()) {
                            do {
                                String data = pcolumn.getString(pcolumn.getColumnIndex("Username"));

                                if (pet.equals(data)) {

                                    //String doctor = pcolumn.getString(pcolumn.getColumnIndex("email"));
                                    String hospital = pcolumn.getString(pcolumn.getColumnIndex("email"));
                                    String contact = pcolumn.getString(pcolumn.getColumnIndex("hospital"));

                                    String d = "Doctor    :";
                                    String c = "Hospital :";
                                    String h = "Contact  :";

                                    doc1.setText(data);
                                    hos1.setText(hospital);
                                    con1.setText(contact);

                                    doc.setText(d);
                                    hos.setText(h);
                                    con.setText(c);



                                    break;

                                }
                            } while (pcolumn.moveToNext());
                        }


                        pcolumn.close();


                    } catch (Exception e) {
                        Toast.makeText(petowner.this, e.toString(), Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    doc1.setText("");
                    hos1.setText("");
                    con1.setText("");

                    doc.setText("");
                    hos.setText("");
                    con.setText("");

                    appointment.setText("");
                    appointment1.setText("");

                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


   mData = new DatePickerDialog.OnDateSetListener() {
       @Override
       public void onDateSet(DatePicker datePicker, int year, int month, int day) {
           month=month+1;
           String date = day +"/"+month+"/"+year;
           String app = "Appointment On :";
           appointment.setText(app);
           appointment1.setText(date);
       }
   };
    }
public void setdate(View view)
{
    if(!(item))
    {
        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day   = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(petowner.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mData,year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();;
    }
    else
    {
        Toast.makeText(this, "please select doctor", Toast.LENGTH_SHORT).show();
    }
}




}