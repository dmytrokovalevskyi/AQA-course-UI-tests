import models.AdvancedResults;
import models.Results;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.IOException;

public class FirstTest {
    public static String api_key = "k_kw0qy43q";

    @Test(description = "Check first result description")
    public void firstTest() throws IOException {
        ImdbClient ImdbClient = new ImdbClient();
        Assertion assertion = new Assertion();
        Results resultsBody = ImdbClient.searchService.searchMovie(api_key, "inception 2010").execute().body();
        assertion.assertTrue(resultsBody.resultDescriptionContainExpression(0, "2010"), "First description does not contain test description part");
    }
    @Test(description = "Check error message and size of results")
    public void secondTest() throws IOException {
        ImdbClient ImdbClient = new ImdbClient();
        Assertion assertion = new Assertion();
        Results resultsBody = ImdbClient.searchService.searchMovie(api_key, "").execute().body();
        assertion.assertTrue(resultsBody.errorMessage.contains("Invalid request."), "error message does not contain 'Invalid request.'");
        assertion.assertEquals(resultsBody.searchResults.size(), 0);
    }

    @Test(description = "First result title contain only name of movie and description does not contain year")
    public void thirdTest() throws IOException {
        ImdbClient ImdbClient = new ImdbClient();
        Assertion assertion = new Assertion();
        Results resultsBody = ImdbClient.searchService.searchMovie(api_key, "batman 1994").execute().body();
        assertion.assertTrue(resultsBody.searchResults.get(0).title.contains("Batman"), "Title does not contain 'batman'");
        assertion.assertFalse(resultsBody.searchResults.get(0).title.contains("1994"), "Title contains '1994'");
        assertion.assertFalse(resultsBody.searchResults.get(0).description.contains("1994"), "Description contains '1994'");
        // тут тест падає через те шо з імдб приходить інший результат :(
    }

    @Test(description = "Check filtration with title_type parameter")
    public void fourthTest() throws IOException {
        ImdbClient ImdbClient = new ImdbClient();
        Assertion assertion = new Assertion(); // тут викупаю шо не треба в кожному тесті створювати нові обєкти класу але хз як
        AdvancedResults firstResponce = ImdbClient.searchService.advancedSearchTitleAndTitleType(api_key, "batman", "tv_series,tv_movie").execute().body();
        AdvancedResults secondResponce = ImdbClient.searchService.advancedSearchTitleAndTitleType(api_key, "batman", "tv_movie").execute().body();
        assertion.assertTrue(firstResponce.advancedSearchResults.size() > secondResponce.advancedSearchResults.size(), "Number of first request results is NOT larger, that from second.");
    }

    @Test(description = "check genres filtering")
    public void fifthTest() throws IOException {
        String firstGenre = "comedy";
        String secondGengre = "thriller";
        String coma = ",";
        ImdbClient ImdbClient = new ImdbClient();
        AdvancedResults responceBodyWithGenres = ImdbClient.searchService.advancedSearchGenre(api_key, firstGenre.concat(coma.concat(secondGengre))).execute().body();
        for (int i = 0; i < responceBodyWithGenres.advancedSearchResults.size(); i++) {
            if (!responceBodyWithGenres.advancedSearchResults.get(i).genres.contains(firstGenre) || !responceBodyWithGenres.advancedSearchResults.get(i).genres.contains(secondGengre)) {
                System.out.println(responceBodyWithGenres.advancedSearchResults.get(i).title + " has different genres: " + responceBodyWithGenres.advancedSearchResults.get(i).genres);
            }
        }
    }


}
