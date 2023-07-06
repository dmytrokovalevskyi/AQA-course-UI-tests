package qa.projects.services;

import models.AdvancedResults;
import models.Results;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImdbService {
    @GET("en/API/SearchMovie/{api_key}/{expression}")
    Call<Results> searchMovie(
            @Path("api_key") String api_key,
            @Path("expression") String expression
    );

    @GET("/API/AdvancedSearch/{api_key}")
    Call<AdvancedResults> advancedSearchTitleAndTitleType(
            @Path("api_key") String api_key,
            @Query("title") String title,
            @Query("title_type") String title_type
    );

    @GET("/API/AdvancedSearch/{api_key}")
    Call<AdvancedResults> advancedSearchGenre(
            @Path("api_key") String api_key,
            @Query("genres") String genres
    );

}
