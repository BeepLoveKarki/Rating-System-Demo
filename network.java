package tinesandbarbs.tinesandbarbs;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class network extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar4,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.refresh:
                try {
                    if(new net(getApplicationContext()).network()) {
                        this.finish();
                        fin();
                    }
                }catch (Exception e)
                {
                    this.finish();
                  startActivity(getIntent());
                }
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed()
    {
        finish();
        fin1();
    }
    public void fin()
    {
        Bundle extras=getIntent().getExtras();
        String a=extras.getString("from");
        assert a != null;
        switch (a)
        {
            case "sign":
                startActivity(new Intent(this, sign.class));
                break;
            case "sitepage":
                startActivity(new Intent(this, sitepage.class));
                break;
            case "round":
                startActivity(new Intent(this,round.class));
                break;
            case "login":
                startActivity(new Intent(this,login.class));
                break;
            case "round3":
                startActivity(new Intent(this,round3.class));
                break;
            case "round2":
                startActivity(new Intent(this,round2.class));
                break;
            case "round1":
                startActivity(new Intent(this,round1.class));
                break;
            case "l":
                startActivity(new Intent(this,l.class));
                break;
        }
    }
    public void fin1()
    {
        Bundle extras=getIntent().getExtras();
        String b=extras.getString("from1");
        assert b != null;
        switch (b) {
            case "sign":
                startActivity(new Intent(this, login.class));
                break;
            case "sitepage":
                    startActivity(new Intent(this, round.class));
                    break;
            case"loginb":
                startActivity(new Intent(this,login.class));
                break;
            case "l":
                startActivity(new Intent(this,l.class));
                break;


        }
    }


}
