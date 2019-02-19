package sanal.gmail.android.PetCare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class doctorassignment extends AppCompatActivity {
    SQLiteDatabase db;
    EditText message1;
    String ph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorassignment);
        db = openOrCreateDatabase("mydatabase", MODE_PRIVATE, null);



        Bundle bundle = getIntent().getExtras();
        String pet = bundle.getString("selected");

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);

        TextView t = (TextView) findViewById(R.id.textView7);
        TextView t1 = (TextView) findViewById(R.id.textView5);
        TextView t3 = (TextView) findViewById(R.id.textView11);
        TextView t4 = (TextView) findViewById(R.id.textView12);
        TextView t5 = (TextView) findViewById(R.id.textView13);
        TextView t6 = (TextView) findViewById(R.id.textView14);
        TextView t7 = (TextView) findViewById(R.id.textView15);
        TextView t8 = (TextView) findViewById(R.id.textView16);
        TextView t9 = (TextView) findViewById(R.id.textView17);
        message1 = findViewById(R.id.editText10);


        t.setText(pet);
        Cursor pcolumn = db.rawQuery("select * from petowner2", null);

        if (pcolumn.moveToFirst()) {
            do {
                String data = pcolumn.getString(pcolumn.getColumnIndex("fullname"));

                if (pet.equals(data)) {

                    String email = pcolumn.getString(pcolumn.getColumnIndex("email"));
                     ph = pcolumn.getString(pcolumn.getColumnIndex("phonenumber"));
                    String address = pcolumn.getString(pcolumn.getColumnIndex("address"));
                    String petname = pcolumn.getString(pcolumn.getColumnIndex("petname"));
                    String breed = pcolumn.getString(pcolumn.getColumnIndex("breed"));
                    String color = pcolumn.getString(pcolumn.getColumnIndex("colour"));
                    String age = pcolumn.getString(pcolumn.getColumnIndex("age"));
                    String gender = pcolumn.getString(pcolumn.getColumnIndex("gender1"));

                    t1.setText(email);
                    t3.setText(ph);
                    t4.setText(address);
                    t5.setText(petname);
                    t6.setText(breed);
                    t7.setText(color);
                    t8.setText(age);
                    t9.setText(gender);

                    break;

                }
            } while (pcolumn.moveToNext());
        }
        pcolumn.close();


    }


        private class MyHttpRequestTask extends AsyncTask<String,Integer,String>{

            @Override
            protected String doInBackground(String... params) {
                String my_url = params[0];
                String my_data = params[1];
                try {
                    URL url = new URL(my_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    // setting the  Request Method Type
                    httpURLConnection.setRequestMethod("POST");
                    // adding the headers for request
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");
                    try{
                        //to tell the connection object that we will be wrting some data on the server and then will fetch the output result
                        httpURLConnection.setDoOutput(true);
                        // this is used for just in case we don't know about the data size associated with our request
                        httpURLConnection.setChunkedStreamingMode(0);

                        // to write tha data in our request
                        OutputStream outputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                        outputStreamWriter.write(my_data);
                        outputStreamWriter.flush();
                        outputStreamWriter.close();

                        // to log the response code of your request
                        Toast.makeText(doctorassignment.this, httpURLConnection.getResponseCode(), Toast.LENGTH_SHORT).show();
                        // to log the response message from your server after you have tried the request.


                    }catch (Exception e){
                       // Toast.makeText(doctorassignment.this, e.toString(), Toast.LENGTH_SHORT).show();

                    }finally {
                        // this is done so that there are no open connections left when this task is going to complete

                        httpURLConnection.disconnect();
                    }


                }catch (Exception e){
                    //Toast.makeText(doctorassignment.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

                return null;
            }
        }


        public void sendmessage(View view)
        {   String message=message1.getText().toString();
            String number="8907916725";
            String my_url = "http://pay4sms.in/sendsms/?token=b6c9342f0554fc4f4a52853c5d210982&credit=3&sender=kARMAS&message="+message+"&number="+number; // Replace this with your own url
            String my_data = "";// Replace this with your data
            new MyHttpRequestTask().execute(my_url,my_data);

            MyHttpRequestTask lmib = new MyHttpRequestTask();

            if(lmib.getStatus() == AsyncTask.Status.PENDING){
                // My AsyncTask has not started yet
                Toast.makeText(this, "not send", Toast.LENGTH_SHORT).show();
            }

            if(lmib.getStatus() == AsyncTask.Status.RUNNING){
                // My AsyncTask is currently doing work in doInBackground()
                Toast.makeText(this, "sms sending", Toast.LENGTH_SHORT).show();
            }

            if(lmib.getStatus() == AsyncTask.Status.FINISHED){
                // My AsyncTask is done and onPostExecute was called
                Toast.makeText(this, "sms is sent", Toast.LENGTH_SHORT).show();
            }
                }

        /*String message = message1.getText().toString();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            //Toast.makeText(this, "sms permission not granted", Toast.LENGTH_LONG).show();
        }
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("+918907916725", null, message, null, null);
            //Toast.makeText(this, "Message sent", Toast.LENGTH_LONG).show();
            new SendPostRequest().execute("");


        }



        catch(Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }*/

    }







