package my.com.toru.pagelibtest.real.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import my.com.toru.pagelibtest.real.model.GithubUser;

/**
 * Created by toruchoi on 29/01/2018.
 */

public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    public LiveData<PagedList<GithubUser>> userList = new MutableLiveData<>();

    public MainViewModel() {}
}