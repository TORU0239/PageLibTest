package my.com.toru.pagelibtest.real.repo;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

/**
 * Created by toruchoi on 29/01/2018.
 */

public class GithubUserDataSourceFactory implements DataSource.Factory{
    private static final String TAG = GithubUserDataSourceFactory.class.getSimpleName();

    ItemKeyedUserDataSource itemKeyedUserDataSource;
    MutableLiveData<ItemKeyedUserDataSource> mutableLiveData = new MutableLiveData<>();

    @Override
    public DataSource create() {
        itemKeyedUserDataSource = new ItemKeyedUserDataSource();
        mutableLiveData.postValue(itemKeyedUserDataSource);
        return itemKeyedUserDataSource;
    }

    public MutableLiveData<ItemKeyedUserDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}