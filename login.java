package tinesandbarbs.tinesandbarbs;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;


public class login extends AppCompatActivity implements AsyncResponse {
    static CharSequence input;
    EditText email, password;
    Button l;
    TextView sign;
    TextView forgot;
    static String e,f;
    static int a=1;
    ProgressDialog dialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final String MyPREFERENCES="User", Email="email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        l=(Button) findViewById(R.id.l);
        sign = (TextView) findViewById(R.id.sign);
        forgot = (TextView) findViewById(R.id.forgot);
        sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }
 @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar5, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
        case R.id.site:
                    Intent j = new Intent(this, sitepage.class);
                    j.putExtra("page", "login");
                    startActivity(j);
                    return (true);
        }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void processFinish(String s) {
        dialog.dismiss();
        if (s.equals("success")) {
            editor.putString(Email,e);
            editor.apply();
            Intent a = new Intent(this, round.class);
            a.putExtra("email",e);
            a.putExtra("pass",f);
            startActivity(a);
        } else {
            Toast.makeText(this, "No account found!!!", Toast.LENGTH_SHORT).show();

        }
    }
    public void l(View v) {
        e = email.getText().toString();
        f = password.getText().toString();
        final String url = "http://10.0.2.2/client/login.php";
        if (isValidEmail(e)) {
            try {
            if (new net(getApplicationContext()).network()) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading........");
            dialog.show();
            dialog.setCancelable(true);
            HashMap<String, String> post = new HashMap<>();
            post.put("mobile", "android");
            post.put("txtEmail", e);
            post.put("txtPassword", f);
            PostResponseAsyncTask task = new PostResponseAsyncTask(this, post, this);
            task.execute(url);
        }
        }catch (Exception e1)
        {
         start();
        }
    }
         else {
            Toast.makeText(login.this, "Invalid Email Address or Password!!!", Toast.LENGTH_SHORT).show();
        }

    }
    public void start()
    {
        Intent i=new Intent(this,network.class);
        i.putExtra("from","login");
        i.putExtra("from1","loginb");
        startActivity(i);
    }


    public static boolean isValidEmail(String email) {
        String expression = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
        input = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    public void sign(View view)
    {
        sign.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.rect));
        Intent i=new Intent(this,sign.class);
        startActivity(i);
    }
    public void forgot(View view)
    {
        forgot.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.rect));
        Intent j=new Intent(this,fyp.class);
        startActivity(j);
    }
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }
}

