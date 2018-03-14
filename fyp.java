package tinesandbarbs.tinesandbarbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class fyp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fyp);
    }

    public void onBackPressed()
    {
        startActivity(new Intent(this,login.class));
    }
}
