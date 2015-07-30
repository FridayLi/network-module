package com.datayes.volleymodule;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.datayes.network.ResponseListener;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginManager manager = new LoginManager(this);

//        manager.login("quandong.li@datayes.com", "123456", new Response.Listener<AuthResult>() {
//            @Override
//            public void onResponse(AuthResult response) {
//                Toast.makeText(MainActivity.this,"login token = " + response.getAccessToken()
//                        ,Toast.LENGTH_SHORT).show();
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this,"login error = " + error.getMessage()
//                        ,Toast.LENGTH_SHORT).show();
//            }
//        });

        manager.login("quandong.li@datayes.com", "123456", new ResponseListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult data) {
                Toast.makeText(MainActivity.this,"login token = " + data.getAccessToken()
                        ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception exception, int errorCode) {
                Toast.makeText(MainActivity.this,"login error = " + exception.getMessage()
                        ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
