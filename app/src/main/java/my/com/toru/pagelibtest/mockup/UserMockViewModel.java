package my.com.toru.pagelibtest.mockup;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import my.com.toru.pagelibtest.mockup.dao.UserDao;
import my.com.toru.pagelibtest.mockup.dao.UserMockData;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class UserMockViewModel extends ViewModel {
    private static final String TAG = UserMockViewModel.class.getSimpleName();

    public LiveData<PagedList<UserMockData>> usersList;

    public UserMockViewModel(UserDao dao){
        Log.w(TAG, "Constructed!!");
        if(dao.getTotalUserCount() <= 1){
            dao.insertAllUsers(dummyDatas());
        }
        usersList = new LivePagedListBuilder<>(dao.getAllUsers(), 10).build();
    }

    // TODO: calling REST API, updating Database.


    private List<UserMockData> dummyDatas(){
        Log.w(TAG, "dummyDatas");
        LinkedList<UserMockData> dataList = new LinkedList<>();

        UserMockData data = new UserMockData();
        data.name = "Toru";
        data.age = 30;
        data.address = "KLCC";
        dataList.add(data);

        data = new UserMockData();
        data.name = "Sean";
        data.age = 40;
        data.address = "Desa Park";
        dataList.add(data);

        data = new UserMockData();
        data.name = "Kar Heng";
        data.age = 34;
        data.address = "Glennmarie";
        dataList.add(data);

        data = new UserMockData();
        data.name = "Su Lynn";
        data.age = 30;
        data.address = "Subang";
        dataList.add(data);

        data = new UserMockData();
        data.name = "Natalia";
        data.age = 33;
        data.address = "Dang Wangi";
        dataList.add(data);

        data = new UserMockData();
        data.name = "Austeja";
        data.age = 30;
        data.address = "Bangsar";
        dataList.add(data);

        return dataList;
    }
}