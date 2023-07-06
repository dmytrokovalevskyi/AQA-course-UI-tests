package models;

public class Result {
    public String id;
    public String resultType;
    public String title;
    public String description;

    @Override
    public String toString() {
        return "Result{" +
                "id='" + id + '\'' +
                ", resultType='" + resultType + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
