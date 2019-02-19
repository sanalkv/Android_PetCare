package sanal.gmail.android.PetCare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    button=(Button) findViewById(R.id.button3);


        //text3 = (TextView) findViewById(R.id.textView3);
        //text4 = (TextView) findViewById(R.id.textView4);
    }
    public void movetodoctor(View view)
    {
        Intent intent = new Intent(this,doctorlogin.class);
        startActivity(intent);
    }
    public void petowner(View view)
    {
        Intent intent = new Intent(this,petownerlogin.class);
        startActivity(intent);
    }




}








