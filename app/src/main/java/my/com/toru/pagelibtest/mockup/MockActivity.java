package my.com.toru.pagelibtest.mockup;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.DataSource;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import my.com.toru.pagelibtest.R;
import my.com.toru.pagelibtest.mockup.dao.UserDao;
import my.com.toru.pagelibtest.mockup.dao.UserMockData;

public class MockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(
                view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show());

        init();
    }

    private void init(){
        final UserAdapter adapter = new UserAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(MockActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recyclerView = findViewById(R.id.rcv_mock);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        UserMockViewModel viewModel = ViewModelProviders.of(this, new UserMockViewModelFactory(new UserDao() {
            @Override
            public void insertAllUsers(List<UserMockData> users) {}

            @Override
            public void insertUser(UserMockData... user) {}

            @Override
            public void updateUser(UserMockData... user) {}

            @Override
            public void deleteUser(UserMockData... user) {}

            @Override
            public DataSource.Factory<Integer, UserMockData> getAllUsers() {
                return null;
            }

            @Override
            public int getTotalUserCount() {
                return 0;
            }

            @Override
            public DataSource.Factory<Integer, UserMockData> getUsersFromName(String name) {
                return null;
            }
        })).get(UserMockViewModel.class);


        // Whenever getting data set changed, notify here.
        viewModel.usersList.observe(MockActivity.this, adapter::setList);
    }
}