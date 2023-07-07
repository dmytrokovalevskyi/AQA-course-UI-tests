package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdvancedResult {
    public String id;
    public String title;
    public String genres;
    @SerializedName("genreList")
    public List<GenreList> genreList;

    @Override
    public String toString() {
        return "AdvancedResult{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genres='" + genres + '\'' +
                ", genreList=" + genreList +
                '}';
    }
}
