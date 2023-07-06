package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdvancedResults {
    @SerializedName("results")
    public List<AdvancedResult> advancedSearchResults;

    @Override
    public String toString() {
        return "AdvancedResults{" +
                "advancedSearchResults=" + advancedSearchResults +
                '}';
    }
}
