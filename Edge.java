
public class Edge {
    String name;
    String actorId;
    String movieId;
    String movieName;
    double rating;

    public Edge(String name, String actorId, String movieId, String movieName, double rating) {
        this.name = name;
        this.actorId = actorId;
        this.movieId = movieId;
        this.movieName = movieName;
        this.rating = rating;
    }
}