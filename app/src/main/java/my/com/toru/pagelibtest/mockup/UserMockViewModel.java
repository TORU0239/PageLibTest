package my.com.toru.pagelibtest.mockup;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import my.com.toru.pagelibtest.mockup.dao.UserDao;
import my.com.toru.pagelibtest.mockup.dao.UserMockData;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class UserMockViewModel extends ViewModel {
    private static final String TAG = UserMockViewModel.class.getSimpleName();

    public LiveData<PagedList<UserMockData>> usersList = new MutableLiveData<>();

    public UserMockViewModel(UserDao dao){
        Log.w(TAG, "Constructed!!");
        // temporarily blocked!!
        usersList = new LivePagedListBuilder<>(dao.getAllUsers(), 10).build();
    }

    // TODO: calling REST API, updating Database.
}