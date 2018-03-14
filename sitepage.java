package tinesandbarbs.tinesandbarbs;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static tinesandbarbs.tinesandbarbs.login.Email;


public class sitepage extends AppCompatActivity {
    WebView site;
    ProgressDialog pb;
    SharedPreferences sharedPreferences;
    String url="http://tinesandbarbs.com/login/index.php?page=start&keep_mobile=1",b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitepage);
        site = (WebView) findViewById(R.id.site);
        load();
    }
    public void load()
    {
        try {
            if(new net(getApplicationContext()).network()) {
                cookie();
                site.getSettings().getJavaScriptEnabled();
                site.getSettings().setLoadsImagesAutomatically(true);
                site.setWebViewClient(new browser());
                site.loadUrl(url);
            }
        }catch(Exception e)
        {
            start();
        }
    }
    public void cookie()
    {
        CookieManager cookieManager=CookieManager.getInstance();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            cookieManager.acceptCookie();
            cookieManager.setAcceptThirdPartyCookies(site,true);
        }
        else
        {
            cookieManager.acceptCookie();
            cookieManager.setAcceptCookie(true);
        }
    }
    private class browser extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    sitepage.this.pb = new ProgressDialog(sitepage.this);
                    sitepage.this.pb.setMessage("Loading");
                    sitepage.this.pb.setIndeterminate(true);
                    sitepage.this.pb.show();
                    sitepage.this.pb.setCancelable(true);
                    sitepage.this.pb.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            site.stopLoading();
                        }
                    });
        }
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    sitepage.this.pb.dismiss();
                }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        sharedPreferences=getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(Email,"").length()==0)
        {
            getMenuInflater().inflate(R.menu.toolbar2,menu);
        }
        else
        {
            getMenuInflater().inflate(R.menu.toolbar3,menu);
        }

        return true;
    }
     @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         if(sharedPreferences.getString(Email,"").length()==0)
         {
             switch(item.getItemId()) {
                 case R.id.apk:
                     startActivity(new Intent(this, login.class));
                     return (true);
             }
         }
         else
         {
         switch(item.getItemId()) {
             case R.id.apk:
                 see();
                 return (true);
             case R.id.out:
                 shared();
                 startActivity(new Intent(this, login.class));
                 return (true);
             case R.id.refresh:
                 load();
         }
    }
        return(super.onOptionsItemSelected(item));
    }
    public void see()
    {
        Bundle extras=getIntent().getExtras();
        b=extras.getString("page");
        assert b!=null;
        switch (b)
        {
            case "round":
                startActivity(new Intent(this,round.class));
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
            case "login":
                startActivity(new Intent(this,login.class));
                break;
            case "l":
                startActivity(new Intent(this,l.class));
                break;
        }
    }
    public void onBackPressed()
    {
                if (site.canGoBack()) {
                    cookie();
                    site.setWebViewClient(new browser());
                    site.goBack();

                } else{
                    switch(login.a)
                    {
                        case 1:
                            startActivity(new Intent(this,login.class));
                            break;
                        case 2:
                            startActivity(new Intent(this,round.class));
                            break;
                        case 3:
                            startActivity(new Intent(this,round1.class));
                            break;
                        case 4:
                            startActivity(new Intent(this,round2.class));
                            break;
                        case 5:
                            startActivity(new Intent(this,round3.class));
                            break;
                        case 6:
                            startActivity(new Intent(this,notournament.class));
                            break;
                        case 7:
                            startActivity(new Intent(this,l.class));
                            break;
                    }

                }
        }
    public void shared() {
        SharedPreferences sharedPreferences = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void start()
    {
        Intent i=new Intent(this,network.class);
        i.putExtra("from","sitepage");
        i.putExtra("from1","sitepage");
        startActivity(i);
    }
}

