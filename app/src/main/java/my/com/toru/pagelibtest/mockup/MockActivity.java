package my.com.toru.pagelibtest.mockup;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import my.com.toru.pagelibtest.R;
import my.com.toru.pagelibtest.mockup.dao.UserDB;

public class MockActivity extends AppCompatActivity {
    private static final String TAG = MockActivity.class.getSimpleName();

    private UserMockViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initDatabase();
        init();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view ->
                viewModel.onClickForFab(view)
        );
    }

    private void init(){
        final UserAdapter adapter = new UserAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(MockActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        final RecyclerView recyclerView = findViewById(R.id.rcv_mock);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(MockActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);

        viewModel = ViewModelProviders.of(this, new UserMockViewModelFactory(userDB.getUserDao())).get(UserMockViewModel.class);

        // Whenever getting data set changed, notify here.
        viewModel.usersList.observe(MockActivity.this, userMockData -> {
            Log.w(TAG, "updated?");
            adapter.setList(userMockData);
            Log.w(TAG, "updated!, item:: " + adapter.getItemCount());
            recyclerView.smoothScrollToPosition(0);
        });
        recyclerView.setAdapter(adapter);
    }

    private UserDB userDB;
    private void initDatabase(){
        userDB = UserDB.get(MockActivity.this);
        try {
            Log.w(TAG, "user DB Size:: " + userDB.getUserDao().getTotalUserCount());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}