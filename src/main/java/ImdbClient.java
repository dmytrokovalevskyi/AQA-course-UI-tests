import qa.projects.services.ImdbService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImdbClient {

    public ImdbService searchService;

    public ImdbClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://imdb-api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        searchService = retrofit.create(ImdbService.class);
    }
}
