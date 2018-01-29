package my.com.toru.pagelibtest.real.repo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.util.Log;

/**
 * Created by toruchoi on 29/01/2018.
 */

public class GithubUserDataSourceFactory implements DataSource.Factory{
    private static final String TAG = GithubUserDataSourceFactory.class.getSimpleName();

    private ItemKeyedUserDataSource itemKeyedUserDataSource;
    private MutableLiveData<ItemKeyedUserDataSource> mutableLiveData = new MutableLiveData<>();

    public GithubUserDataSourceFactory() {
        Log.w(TAG, "Construction");
    }

    @Override
    public DataSource create() {
        Log.w(TAG, "DataSource Create");
        itemKeyedUserDataSource = new ItemKeyedUserDataSource();
        mutableLiveData.postValue(itemKeyedUserDataSource);
        return itemKeyedUserDataSource;
    }

    public MutableLiveData<ItemKeyedUserDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}