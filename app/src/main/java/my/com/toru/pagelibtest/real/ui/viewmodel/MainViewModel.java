package my.com.toru.pagelibtest.real.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import my.com.toru.pagelibtest.real.model.GithubUser;
import my.com.toru.pagelibtest.real.repo.GithubUserDataSourceFactory;

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
}