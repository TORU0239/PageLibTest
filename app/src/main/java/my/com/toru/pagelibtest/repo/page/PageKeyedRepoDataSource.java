package my.com.toru.pagelibtest.repo.page;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import my.com.toru.pagelibtest.model.Question;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class PageKeyedRepoDataSource extends PageKeyedDataSource<Integer, Question> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, Question> callback) {
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, Question> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Integer, Question> callback) {

    }
}
