package my.com.toru.pagelibtest.real.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static my.com.toru.pagelibtest.real.api.GithubService.BASE_URL;

/**
 * Created by toruchoi on 25/01/2018.
 */

public class GithubAPI {
    private static final String TAG = GithubAPI.class.getSimpleName();

    public static GithubService retrofit(){
        OkHttpClient client = new OkHttpClient.Builder()
                                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                                .readTimeout(5000, TimeUnit.MILLISECONDS)
                                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                                .build();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();

        return retrofit.create(GithubService.class);
    }
}