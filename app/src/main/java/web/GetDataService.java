package web;


import io.reactivex.Observable;
import model.Movies;
import retrofit2.Call;
import retrofit2.http.GET;
//object -> array -> object -> array
public interface GetDataService {
    //here should be end point for GET request
    @GET("videos-enhanced-c.json")
    Observable<Movies> getAllMovies();
}
