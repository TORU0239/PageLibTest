package my.com.toru.pagelibtest.repo.page;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import my.com.toru.pagelibtest.model.Question;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class ItemKeyedDataRepoSource extends ItemKeyedDataSource<Integer, Question> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Question> callback) {}

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Question> callback) {
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Question> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Question item) {
        return null;
    }
}