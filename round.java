package tinesandbarbs.tinesandbarbs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import static tinesandbarbs.tinesandbarbs.login.Email;


public class round extends AppCompatActivity {
   SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);
        sharedPreferences=getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(Email,"").length()==0)
        {
            startActivity(new Intent(this,login.class));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.site:
                login.a=2;
                Intent j=new Intent(this,sitepage.class);
                j.putExtra("page","round");
               startActivity(j);
               return(true);
            case R.id.out:
                shared();
                startActivity(new Intent(this,login.class));
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
    public void round1(View view)
    {
        try {
            if (new net(getApplicationContext()).network()) {
                Intent intent1 = new Intent(this, round1.class);
                startActivity(intent1);
            }
        }catch (Exception e)
        {
            start();
        }
    }
    public void round2(View view) {
        try{
        if (new net(getApplicationContext()).network()) {
            Intent intent2 = new Intent(this, round2.class);
            startActivity(intent2);
        }
    }
        catch (Exception e)
        {
            start();
        }
    }
    public void round3(View view)
    {
        try {
            if (new net(getApplicationContext()).network()) {
                Intent intent3 = new Intent(this, round3.class);
                startActivity(intent3);
            }
        }
        catch(Exception e)
        {
            start();
        }

    }
    public void start()
    {
        Intent i=new Intent(this,network.class);
        i.putExtra("from","round");
        startActivity(i);
    }
    public void onBackPressed()
    {
        moveTaskToBack(true);
    }
    public void shared()
    {
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
