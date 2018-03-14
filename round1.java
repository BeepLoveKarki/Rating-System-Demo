package tinesandbarbs.tinesandbarbs;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class round1 extends AppCompatActivity {
    private TextView text0, text1, text2, text3, text4, text5, text6, text7, text8,text9,text10,text11,text12,text13,text14;
    int j, k,a[],round1[],phase[],phasestoplay[];
    static int i = 0,n,w=0,y;
    float x1, x2,y1,y2;
    private ImageView img;
    private ProgressBar spin;
    String[] Name,b;
    StringBuilder sb;
    BufferedReader reader;
    Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round1);
        text0 = (TextView) findViewById(R.id.textView14);
        text1 = (TextView) findViewById(R.id.textView7);
        text2 = (TextView) findViewById(R.id.textView13);
        text3 = (TextView) findViewById(R.id.textView8);
        text4 = (TextView) findViewById(R.id.textView);
        text5 = (TextView) findViewById(R.id.textView2);
        text6 = (TextView) findViewById(R.id.textView3);
        text7 = (TextView) findViewById(R.id.textView4);
        text8 = (TextView) findViewById(R.id.textView5);
        text9 = (TextView) findViewById(R.id.textView6);
        text10 = (TextView) findViewById(R.id.textView9);
        text11 = (TextView) findViewById(R.id.textView10);
        text12 = (TextView) findViewById(R.id.textView11);
        text13 = (TextView) findViewById(R.id.textView12);
        text14 = (TextView) findViewById(R.id.textView15);
        img = (ImageView) findViewById(R.id.u1);
        spin = (ProgressBar) findViewById(R.id.pb);
        netw();
        this.mHandler = new Handler();
        m_Runnable.run();
    }
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            netw();
            round1.this.mHandler.postDelayed(m_Runnable, 1000);
        }
    };
    private void netw()
    {
        try {
            if (new net(getApplicationContext()).network()) {
                new doit().execute("http://10.0.2.2/client/name.php");
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
        i.putExtra("from","round1");
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.status:
            //add the function to perform here
            return(true);
        case R.id.site:
            mHandler.removeCallbacks(m_Runnable);
            login.a=3;
            Intent j=new Intent(this,sitepage.class);
            j.putExtra("page","round1");
            startActivity(j);
            return(true);
        case R.id.refresh:
            netw();
            return(true);
        case R.id.out:
            mHandler.removeCallbacks(m_Runnable);
            shared();
            startActivity(new Intent(this,login.class));
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }
    private class doit extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {

            if (i >= 0 || i <= n - 4) {
                if(w==0) {
                    yes();
                    super.onPreExecute();
                    w=1;
                }
                else {
                    super.onPreExecute();
                }
            }
        }

        @Override
        protected Void doInBackground(final String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(60000);
                connection.setConnectTimeout(60000);
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                InputStream stream = new BufferedInputStream(connection.getInputStream());
                String data = convertstreamtostring(stream);
                readandparseJSON(data);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void s) {
            if(n==0)
            {
                over();
            }
            else {
                a = new int[n];
                for (j = i; j < n; j++) {
                    a[j] = j + 1;
                }
                if(phase[0]!=0 && phasestoplay[0]!=0 && phase[0]==phasestoplay[0]) {
                    size(1);
                    text0.setText(Integer.toString(a[i]));
                    text1.setText(Integer.toString(a[i + 1]));
                    text2.setText(Integer.toString(a[i + 2]));
                    text3.setText(Integer.toString(a[i + 3]));
                    text7.setText(b[i]);
                    text8.setText(b[i + 1]);
                    text9.setText(b[i + 2]);
                    text10.setText(b[i + 3]);
                    text11.setText(Name[i]);
                    text13.setText(Name[i + 1]);
                    text12.setText(Name[i + 2]);
                    text14.setText(Name[i + 3]);
                }
                else {
                    y=n/4;
                    size(y);
                    text0.setText(Integer.toString(a[i]));
                    text1.setText(Integer.toString(a[y + i]));
                    text2.setText(Integer.toString(a[2*y + i]));
                    text3.setText(Integer.toString(a[3*y + i]));
                    text7.setText(b[i]);
                    text8.setText(b[y + i]);
                    text9.setText(b[2*y + i]);
                    text10.setText(b[3*y + i]);
                    text11.setText(Name[i]);
                    text13.setText(Name[y + i]);
                    text12.setText(Name[2*y + i]);
                    text14.setText(Name[3*y + i]);
                }
                no();
                go();
            }
            try {
                sb.setLength(0);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void size(int m)
    {
        if(i>=8)
        {
            RelativeLayout.LayoutParams  linearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT );
            text13.setLayoutParams(linearLayoutParams);
            text13.setPadding(322,88,26,15);
            RelativeLayout.LayoutParams  linearLayoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT );
            text14.setLayoutParams(linearLayoutParams1);
            text14.setPadding(319,157,26,15);
        }
        if(Name[i+m].length()>=12&& i<8)
        {
            RelativeLayout.LayoutParams  linearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT );
            text13.setLayoutParams(linearLayoutParams);
            text13.setPadding(307,88,26,15);
        }
        if(Name[i].length()>=12 && i<8)
        {
            RelativeLayout.LayoutParams  linearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT );
            text13.setLayoutParams(linearLayoutParams);
            text13.setPadding(307,77,26,15);
        }
        if(Name[i+m].length()>=12&& i>=8)
        {
            RelativeLayout.LayoutParams  linearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT );
            text13.setLayoutParams(linearLayoutParams);
            text13.setPadding(319,88,26,15);
        }
        if(Name[i].length()>=12 && i>=8)
        {
            RelativeLayout.LayoutParams  linearLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT );
            text13.setLayoutParams(linearLayoutParams);
            text13.setPadding(319,77,26,15);
        }
    }
    public void go() {
        if (round1[i]==0 && round1[i+1]==0 && round1[i+2]==0 && round1[i+3]==0) {
            round1.this.setTitle("Tournament Not Started!!!");
        }
        if (round1[i]==1 && round1[i+1]==1 && round1[i+2]==1 && round1[i+3]==1) {
            round1.this.setTitle("Tournament Going On!!!");
        }
        if (round1[i]==2 && round1[i+1]==2 && round1[i+2]==2 && round1[i+3]==2) {
            round1.this.setTitle("Tournament Over!!!");
        }
    }

    private void readandparseJSON(String in)
    {
        try{
            JSONArray name=new JSONArray(in);
            n=name.length();
            if(n%4!=0) {
                for (k = 1; k <= 4; k++) {
                    if ((n - k) % 4 == 0) {
                        n = n - k;
                        break;
                    }
                }
            }
            Name=new String[n];
            round1=new int[n];
            phase=new int[n];
            phasestoplay=new int[n];
            b=new String[n];
            for(k=0;k<n;k++) {
                JSONObject c = name.getJSONObject(k);
                b[k]= Integer.toString(k+1)+":";
                Name[k] =c.getString("name");
                round1[k]=c.getInt("Round1");
                phase[k]=c.getInt("Phase");
                phasestoplay[k]=c.getInt("Phases to play");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String convertstreamtostring(InputStream is)
    {
        reader = new BufferedReader(new InputStreamReader(is));
        sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    public void over()
    {
        mHandler.removeCallbacks(m_Runnable);
        Intent i=new Intent(this,notournament.class);
        startActivity(i);
    }
    public boolean onTouchEvent(MotionEvent touchevent) {
        int min_distance = 100;

        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }

            case MotionEvent.ACTION_UP: {

                x2 = touchevent.getX();
                y2 = touchevent.getY();
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;

                if (Math.abs(deltaX) > Math.abs(deltaY)) {//left to right
                    if ((Math.abs(deltaX) > min_distance)) {
                        if (phase[0] == phasestoplay[0]) {
                            if (deltaX < 0) {
                                if (i <= n - 4) {
                                    i += 4;
                                    netw();
                                }
                                if (i > n - 4) {
                                    i = n - 4;
                                    netw();
                                    Toast.makeText(this, "Swipe Right For Previous Tournament Block", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                if (i >= 0) {
                                    i -= 4;
                                    netw();
                                }

                                if (i < 0) {
                                    i = 0;
                                    netw();
                                    Toast.makeText(this, "Swipe Left For Next Tournament Blocks", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            }
                        }
                        else{
                            if (deltaX < 0) {
                                if (i <= y-1) {
                                    i ++;
                                    netw();
                                }
                                if (i > y-1) {
                                    i = y-1;
                                    netw();
                                    Toast.makeText(this, "Swipe Right For Previous Tournament Block", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                if (i >= 0) {
                                    i --;
                                    netw();
                                }

                                if (i < 0) {
                                    i = 0;
                                    netw();
                                    Toast.makeText(this, "Swipe Left For Next Tournament Blocks", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            }

                        }
                    }
                }
            }
        }
        return false;
    }
    public void yes()
    {
        text0.setVisibility(View.INVISIBLE);
        text1.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);
        text5.setVisibility(View.INVISIBLE);
        text6.setVisibility(View.INVISIBLE);
        text7.setVisibility(View.INVISIBLE);
        text8.setVisibility(View.INVISIBLE);
        text9.setVisibility(View.INVISIBLE);
        text10.setVisibility(View.INVISIBLE);
        text11.setVisibility(View.INVISIBLE);
        text12.setVisibility(View.INVISIBLE);
        text13.setVisibility(View.INVISIBLE);
        text14.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
        spin.setVisibility(View.VISIBLE);
    }

    public void no()
    {
        text0.setVisibility(View.VISIBLE);
        text1.setVisibility(View.VISIBLE);
        text2.setVisibility(View.VISIBLE);
        text3.setVisibility(View.VISIBLE);
        text4.setVisibility(View.VISIBLE);
        text5.setVisibility(View.VISIBLE);
        text6.setVisibility(View.VISIBLE);
        text7.setVisibility(View.VISIBLE);
        text9.setVisibility(View.VISIBLE);
        text11.setVisibility(View.VISIBLE);
        text12.setVisibility(View.VISIBLE);
        text8.setVisibility(View.VISIBLE);
        text10.setVisibility(View.VISIBLE);
        text13.setVisibility(View.VISIBLE);
        text14.setVisibility(View.VISIBLE);
        img.setVisibility(View.VISIBLE);
        spin.setVisibility(View.INVISIBLE);
    }
    public void shared()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public void onBackPressed()
    {
        mHandler.removeCallbacks(m_Runnable);
        finish();
    }
}



