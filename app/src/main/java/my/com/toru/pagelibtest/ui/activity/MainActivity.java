package my.com.toru.pagelibtest.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

import my.com.toru.pagelibtest.BuildConfig;
import my.com.toru.pagelibtest.R;
import my.com.toru.pagelibtest.mockup.MockActivity;
import my.com.toru.pagelibtest.repo.remote.Remote;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(BuildConfig.DEBUG){
            startActivity(new Intent(MainActivity.this, MockActivity.class));
        }
        else{
            HashMap<String, String> queryMap = new HashMap<>();
            queryMap.put("order", "desc");
            queryMap.put("site", "stackoverflow");
            queryMap.put("tagged", "android");
            queryMap.put("filter", "withbody");
            Remote.retrofit(queryMap);
        }
    }
}