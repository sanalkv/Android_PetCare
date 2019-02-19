package sanal.gmail.android.PetCare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class pettable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pettable);
    }




        public void doc(View view)
        {
            try
        {

            Bundle bundle = getIntent().getExtras();
            String pet = bundle.getString("username");

            Intent intent = new Intent(this, petowner.class);
            bundle = new Bundle();
            bundle.putString("username", pet);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();

    }}
    public void vaccine(View view)
    {
        Intent intent = new Intent(pettable.this,Vaccine.class);
        startActivity(intent);
    }
    public void tips(View view)
    {
        Intent intent = new Intent(pettable.this,Tips.class);
        startActivity(intent);
    }


}
