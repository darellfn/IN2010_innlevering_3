import java.util.ArrayList;
import java.util.List;

public class Vertex {
    String actorId;
    String actorName;
    List<Edge> edges;

    public Vertex(String actorId, String actorName) {
        this.actorId = actorId;
        this.actorName = actorName;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }
}