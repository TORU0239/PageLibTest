package my.com.toru.pagelibtest.repo.remote;

import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class Remote {
    private static final String TAG = Remote.class.getSimpleName();

    private static final String BASE_URL = "https://api.stackexchange.com";
    private static final int PAGE_SIZE = 50;

    public static void retrofit(HashMap<String, String> queryMap){

        OkHttpClient client = new OkHttpClient.Builder()
                                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                                .readTimeout(5000, TimeUnit.MILLISECONDS)
                                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                                .build();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(client)
                            .build();

        Disposable disposable = retrofit.create(StackOverFlowAPI.class)
                .getQuestions(queryMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        s -> Log.w(TAG, "result string:" + s),
                        Throwable::printStackTrace,
                        () -> Log.w(TAG, "onComplete!!"));
    }
}
