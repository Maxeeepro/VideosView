package web;
import java.util.List;

import model.Movies;
import model.Videos;
import retrofit2.Call;
import retrofit2.http.GET;
//object -> array -> object -> array
public interface GetDataService {
    //here should be end point for GET request
    @GET("videos-enhanced-c.json")
    Call<Movies> getAllMovies();
}
