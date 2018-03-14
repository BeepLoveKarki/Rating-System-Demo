package tinesandbarbs.tinesandbarbs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class notournament extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notournament);
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
                login.a=6;
                Intent j=new Intent(this,sitepage.class);
                j.putExtra("page","notournament");
                startActivity(j);
                return(true);
            case R.id.out:
                shared();
                startActivity(new Intent(this,login.class));
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
    public void button2(View view)
    {

    }
    public void onBackPressed()
    {

        startActivity(new Intent(this,round.class));
    }
    public void shared() {
        SharedPreferences sharedPreferences = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
