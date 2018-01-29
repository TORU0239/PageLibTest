package my.com.toru.pagelibtest.real.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by toruchoi on 29/01/2018.
 */

public interface GithubService {
    String BASE_URL = "https://api.github.com";
    int PAGE_SIZE = 50;

    @GET("/users")
    Call<List<String>> getUser(@Query("since") long since,
                               @Query("per_page") int perPage);
}