package tinesandbarbs.tinesandbarbs;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class sign extends AppCompatActivity {
    WebView site;
    ProgressDialog pb;
    String url="http://tinesandbarbs.com/login/index.php?page=join&redirect=http%3A%2F%2Ftinesandbarbs.com%2Flogin%2Findex.php%3Fpage%3Dstart%26keep_session%3d562582627&keep_session=562582627";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitepage);
        site = (WebView) findViewById(R.id.site);
        try {
            if(new net(getApplicationContext()).network()) {
                cookie();
                site.getSettings().getJavaScriptEnabled();
                site.getSettings().setLoadsImagesAutomatically(true);
                site.getSettings().setDatabaseEnabled(true);
                site.setWebChromeClient(new WebChromeClient());
                site.setWebViewClient(new browser());
                site.loadUrl(url);
            }
        }catch(Exception e)
        {
            start();
        }
    }
    private class browser extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
               super.onPageStarted(view, url, favicon);
                sign.this.pb = new ProgressDialog(sign.this);
                sign.this.pb.setMessage("Loading");
                sign.this.pb.setIndeterminate(true);
                sign.this.pb.show();
                sign.this.pb.setCancelable(true);
                sign.this.pb.setOnCancelListener(new DialogInterface.OnCancelListener() {
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
                sign.this.pb.dismiss();
                if (view.getUrl().equals("http%\"http://tinesandbarbs.com/login/index.php?page=join&type=step3&redirect=3A%2F%2Ftinesandbarbs.com%2Flogin%2Findex.php%3Fpage%3Dstart%26keep_mobile%3D1&keep_mobile=1"))
                {
                    alert();
                }
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar2,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.apk:
                startActivity(new Intent(this,login.class));
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }

    public void onBackPressed()
    {
        if (site.canGoBack()) {
                  cookie();
                  site.setWebViewClient(new browser());
                    site.goBack();
                } else {
                    startActivity(new Intent(this,login.class));
                }
    }

    public void start()
    {
        Intent i=new Intent(this,network.class);
        i.putExtra("from","sign");
        i.putExtra("from1","sign");
        startActivity(i);
    }
    public void alert()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getApplicationContext());
        builder1.setMessage("Congratulations!!!You joined the most advanced form of board game. Do You wish to continue with site or go back to the app???");
        builder1.setCancelable(false);
        builder1.setPositiveButton("Continue with site", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton("Go Back To app", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        sign.this.finish();
                    }
                });
        AlertDialog alert = builder1.create();
        alert.show();
    }
}

