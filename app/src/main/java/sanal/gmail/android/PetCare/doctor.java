package sanal.gmail.android.PetCare;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class doctor extends AppCompatActivity {

    ListView l;
    SQLiteDatabase db;
    ArrayList<String> ls = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        try {

            db = openOrCreateDatabase("mydatabase", MODE_PRIVATE, null);

            Cursor pcolumn = db.rawQuery(" select * from petowner2 ", null);


            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("username");

            if (pcolumn.moveToFirst()) {
                do {
                    String data = pcolumn.getString(pcolumn.getColumnIndex("doctor"));

                    if (username.equals(data)) {
                        String poname = pcolumn.getString(pcolumn.getColumnIndex("fullname"));
                        ls.add(poname);

                    }
                } while (pcolumn.moveToNext());
            } else {
                Toast.makeText(this, "No pet owners registered", Toast.LENGTH_LONG).show();
            }

            final ListAdapter adapter = new ArrayAdapter<>(doctor.this, android.R.layout.simple_list_item_1, ls);
            l = (ListView) findViewById(R.id.dynamic);
            l.setAdapter(adapter);
            pcolumn.close();

            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), doctorassignment.class);

                    //String s = String.valueOf(i);
                    String q = String.valueOf(adapter.getItem(i));
                    Bundle bundle = new Bundle();
                    bundle.putString("selected",q );
                    intent.putExtras(bundle);
                    startActivity(intent);
                    // startActivity(intent);

                }

            });
        }
        catch(Exception e)
        {
            Toast.makeText(this, "no patients available", Toast.LENGTH_LONG).show();
        }


}


    }





