package my.com.toru.pagelibtest.real.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import my.com.toru.pagelibtest.BuildConfig;
import my.com.toru.pagelibtest.R;
import my.com.toru.pagelibtest.mockup.MockActivity;
import my.com.toru.pagelibtest.real.api.GithubAPI;
import my.com.toru.pagelibtest.real.model.GithubUser;
import my.com.toru.pagelibtest.real.ui.viewmodel.MainViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!BuildConfig.DEBUG){
            startActivity(new Intent(MainActivity.this, MockActivity.class));
            finish();
        }
        else{
            init();
        }
    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.rcv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);

        final GithubUserAdapter adapter = new GithubUserAdapter();
        MainViewModel viewModel = ViewModelProviders.of(MainActivity.this).get(MainViewModel.class);
        viewModel.userList.observe(MainActivity.this, githubUsers -> {
            if(githubUsers != null){
                Log.w(TAG, "github users size: " + githubUsers.size());
                adapter.setList(githubUsers);
            }
            else{
                Log.w(TAG, "github users null");
            }
        });
        recyclerView.setAdapter(adapter);
    }
}