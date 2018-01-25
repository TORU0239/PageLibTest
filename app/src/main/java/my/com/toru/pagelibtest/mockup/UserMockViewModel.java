package my.com.toru.pagelibtest.mockup;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import my.com.toru.pagelibtest.mockup.dao.UserDao;
import my.com.toru.pagelibtest.mockup.dao.UserMockData;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class UserMockViewModel extends ViewModel {
    private static final String TAG = UserMockViewModel.class.getSimpleName();

    public final LiveData<PagedList<UserMockData>> usersList;

    public UserMockViewModel(UserDao dao){
        usersList = new LivePagedListBuilder<>(dao.getAllUsers(), 10).build();
    }

    // TODO: calling REST API, updating Database.
}