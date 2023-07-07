import models.AdvancedResults;
import models.Results;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;

import static org.testng.Assert.*;

public class FirstTest {
    public static String api_key = "k_kw0qy43q";
    ImdbClient ImdbClient = new ImdbClient();


    @Test(description = "Check first result description")
    public void firstTest() throws IOException {
        Results resultsBody = ImdbClient.searchService.searchMovie(api_key, "inception 2010").execute().body();
        assertTrue(resultsBody.resultDescriptionContainExpression(0, "2010"), "First description does not contain test description part");
    }
    @Test(description = "Check error message and size of results")
    public void secondTest() throws IOException {
        Results resultsBody = ImdbClient.searchService.searchMovie(api_key, "").execute().body();
        assertTrue(resultsBody.errorMessage.contains("Invalid request."), "error message does not contain 'Invalid request.'");
        assertEquals(resultsBody.searchResults.size(), 0);
    }

    @Test(description = "First result title contain only name of movie and description does not contain year")
    public void thirdTest() throws IOException {


        Results resultsBody = ImdbClient.searchService.searchMovie(api_key, "batman 1994").execute().body();
        assertTrue(resultsBody.searchResults.get(0).title.contains("Batman"), "Title does not contain 'batman'");
        assertFalse(resultsBody.searchResults.get(0).title.contains("1994"), "Title contains '1994'");
        assertFalse(resultsBody.searchResults.get(0).description.contains("1994"), "Description contains '1994'");
        // тут тест падає через те шо з імдб приходить інший результат :(
    }

    @Test(description = "Check filtration with title_type parameter")
    public void fourthTest() throws IOException {
        AdvancedResults firstResponse = ImdbClient.searchService.advancedSearchTitleAndTitleType(api_key, "batman", "tv_series,tv_movie").execute().body();
        AdvancedResults secondResponse = ImdbClient.searchService.advancedSearchTitleAndTitleType(api_key, "batman", "tv_movie").execute().body();
        assertTrue(firstResponse.advancedSearchResults.size() > secondResponse.advancedSearchResults.size(), "Number of first request results is NOT larger, that from second.");
    }

    @Test(description = "check genres filtering")
    public void fifthTest() throws IOException {
        String firstGenre = "comedy";
        String secondGengre = "thriller";
        String coma = ",";
        AdvancedResults responseBodyWithGenres = ImdbClient.searchService.advancedSearchGenre(api_key, firstGenre.concat(coma.concat(secondGengre))).execute().body();
        for (int i = 0; i < responseBodyWithGenres.advancedSearchResults.size(); i++) {
            if (!responseBodyWithGenres.advancedSearchResults.get(i).genres.contains(firstGenre) || !responseBodyWithGenres.advancedSearchResults.get(i).genres.contains(secondGengre)) {
                System.out.println(responseBodyWithGenres.advancedSearchResults.get(i).title + " has different genres: " + responseBodyWithGenres.advancedSearchResults.get(i).genres);
            }
        }
    }
}
