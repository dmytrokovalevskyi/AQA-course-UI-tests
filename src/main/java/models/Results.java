package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {
    @SerializedName("results")
    public List<Result> searchResults;
    @SerializedName("errorMessage")
    public String errorMessage;

    @Override
    public String toString() {
        return "Results{" +
                "searchResults=" + searchResults +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    public boolean resultDescriptionContainExpression(int index, String expression) {
        return searchResults.get(index).description.contains(expression);
    }
}
