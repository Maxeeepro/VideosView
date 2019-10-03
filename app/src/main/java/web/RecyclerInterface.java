package web;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecyclerInterface {
    String JSONURL = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/";
    @GET("videos-enhanced-c.json")
    Call<String> getString();
}
