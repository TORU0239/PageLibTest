package my.com.toru.pagelibtest.repo.remote;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by toruchoi on 25/01/2018.
 */

public interface StackOverFlowAPI {
    @GET("/users/{user}/repos")
    Observable<String> getRepos(@Path("user") String user,
                                @Query("page") int page,
                                @Query("per_page") int perPage);


    @GET("/2.2/questions")
    Observable<String> getQuestions(@QueryMap(encoded = true) HashMap<String, String> queryMap);

}