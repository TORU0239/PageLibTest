package my.com.toru.pagelibtest.real.repo;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import my.com.toru.pagelibtest.real.api.GithubAPI;
import my.com.toru.pagelibtest.real.api.GithubService;
import my.com.toru.pagelibtest.real.model.GithubUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by toruchoi on 29/01/2018.
 */

public class ItemKeyedUserDataSource extends ItemKeyedDataSource<Long, GithubUser> {

    private static final String TAG = ItemKeyedUserDataSource.class.getSimpleName();

    private GithubService githubService;
    private LoadInitialParams<Long> initialParams;
    private LoadParams<Long> afterParams;

    public ItemKeyedUserDataSource() {
        githubService = GithubAPI.retrofit();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<GithubUser> callback) {
        List<GithubUser> githubUsers = new ArrayList<>();
        initialParams = params;

        githubService.getUser(1, params.requestedLoadSize).enqueue(new Callback<List<GithubUser>>() {
            @Override
            public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                if(response.isSuccessful() && response.code() == 200){
                    githubUsers.addAll(response.body());
                    callback.onResult(githubUsers);
                    initialParams = null;
                }
                else{
                    Log.w(TAG, "Load initial : What Happened? " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<GithubUser> callback) {
        List<GithubUser> user = new ArrayList<>();
        afterParams = params;

        githubService.getUser(params.key, params.requestedLoadSize).enqueue(new Callback<List<GithubUser>>() {
            @Override
            public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                if(response.isSuccessful()){
                    user.addAll(response.body());
                    callback.onResult(user);
                    afterParams = null;
                }
                else{
                    Log.w(TAG, "Load After :: What Happened? " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<GithubUser> callback) {}

    @NonNull
    @Override
    public Long getKey(@NonNull GithubUser item) {
        return item.userId;
    }
}