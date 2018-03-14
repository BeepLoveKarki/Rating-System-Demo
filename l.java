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
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class l extends AppCompatActivity {
    StringBuilder sb;
    static int a=0;
    int n,k,i=1;
    private ListView list;
    private ProgressBar pb;
    String Name[],rank[],rate[],point[],escore[],pscore[],gscore[];
    ArrayList<HashMap<String,String>>user;
    HashMap<String,String> data;
    BufferedReader reader;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l);
        list = (ListView) findViewById(R.id.list);
        pb = (ProgressBar) findViewById(R.id.pb);
        this.setTitle("Players' Chart");
        netw();
        this.mHandler = new Handler();
        m_Runnable.run();
    }
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            netw();
            l.this.mHandler.postDelayed(m_Runnable,1000);
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
        mHandler.removeCallbacks(m_Runnable);
        Intent i=new Intent(this,network.class);
        i.putExtra("from","l");
        i.putExtra("from1","l");
        startActivity(i);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar6,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.refresh:
                netw();
                return true;
            case R.id.site:
                mHandler.removeCallbacks(m_Runnable);
                login.a=7;
                Intent j=new Intent(this,sitepage.class);
                j.putExtra("page","l");
                startActivity(j);
                return true;
            case R.id.out:
                mHandler.removeCallbacks(m_Runnable);
                shared();
                startActivity(new Intent(this,login.class));
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }
    private class doit extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            if(a==0) {
                yes();
                super.onPreExecute();
                a = 1;
            }
            else
            {
                super.onPreExecute();
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
        protected void onPostExecute(Void s){
            super.onPostExecute(s);
            user = new ArrayList<>();
            for(k=0;k<n;k++) {
                data = new HashMap<>();
                data.put("sn",rank[k]);
                data.put("name",Name[k]);
                data.put("rate",rate[k]);
                data.put("point",point[k]);
                data.put("pscore",pscore[k]);
                data.put("gscore",gscore[k]);
                user.add(data);
            }
            try {
                ListAdapter adapter = new SimpleAdapter(l.this, user, R.layout.row, new String[]{"sn", "name", "rate","point","pscore","gscore"}, new int[]{R.id.sn, R.id.name, R.id.rating, R.id.point,R.id.pscore,R.id.gscore});
                list.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
            no();
            }
        }

    public void readandparseJSON(String in)
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
            rate=new String[n];
            point=new String[n];
            gscore=new String[n];
            pscore=new String[n];
            rank= new String[n];
            for(k=0;k<n;k++) {
                JSONObject c = name.getJSONObject(k);
                rank[k]=Integer.toString(k+1);
                Name[k] =c.getString("name");
                rate[k]=Double.toString(c.getDouble("Rating"));
                pscore[k]=Double.toString(c.getDouble("Pscore"));
                gscore[k]=Double.toString(c.getDouble("Gscore"));
                point[k]=Double.toString(c.getDouble("Point"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private String convertstreamtostring(InputStream is) {
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
    public void yes()
    {
        list.setVisibility(View.INVISIBLE);
        pb.setVisibility(View.VISIBLE);
    }
    public void no()
    {
        list.setVisibility(View.VISIBLE);
        pb.setVisibility(View.INVISIBLE);
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

