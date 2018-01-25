package my.com.toru.pagelibtest.repo.page;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import my.com.toru.pagelibtest.model.Question;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class PositionalRepoDataSource extends PositionalDataSource<Question> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams params,
                            @NonNull LoadInitialCallback<Question> callback) {

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params,
                          @NonNull LoadRangeCallback<Question> callback) {
    }
}
