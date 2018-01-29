package my.com.toru.pagelibtest.real.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import my.com.toru.pagelibtest.real.api.GithubAPI;
import my.com.toru.pagelibtest.real.model.GithubUser;
import my.com.toru.pagelibtest.real.repo.GithubUserDataSourceFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by toruchoi on 29/01/2018.
 */

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    public LiveData<PagedList<GithubUser>> userList = new MutableLiveData<>();

    public MainViewModel() {
        Executor executor = Executors.newSingleThreadExecutor();
        GithubUserDataSourceFactory dataSourceFactory = new GithubUserDataSourceFactory();
        PagedList.Config pagedListConfig = new PagedList.Config.Builder()
                                            .setEnablePlaceholders(false)
                                            .setInitialLoadSizeHint(10)
                                            .setPageSize(20)
                                            .build();

        userList = new LivePagedListBuilder<Long, GithubUser>(dataSourceFactory, pagedListConfig)
                .setBackgroundThreadExecutor(executor)
                .build();
    }

    private void request(){
        Call<List<GithubUser>> apiCall = GithubAPI.retrofit().getUser(2000, 10);
            apiCall.enqueue(new Callback<List<GithubUser>>() {
                @Override
                public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                    if(response.isSuccessful()) {
                        Log.w(TAG, "size:: " + response.body().size());
                        if (userList.getValue() != null) {
                            userList.getValue().addAll(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                    t.printStackTrace();
                }
        });
    }
}