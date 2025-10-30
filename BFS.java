import java.util.*;

public class BFS {

    public void BFSFull(HashMap<String, Vertex> G, String actor1, String actor2) {
        Set<String> visited = new HashSet<>();
        BFSVisit(G, actor1, visited, actor1, actor2);
    }

    private void BFSVisit(HashMap<String, Vertex> G, String s, Set<String> visited, String actor1, String actor2) {
        Queue<String> queue = new LinkedList<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, Edge> edge = new HashMap<>();
        
        queue.offer(s);
        visited.add(s);
        
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            String q = queue.poll();
            Vertex vertex = G.get(q);
            
            for (Edge v : vertex.edges) {
                if (!visited.contains(v.actorId)) {
                    visited.add(v.actorId);
                    queue.offer(v.actorId);
                    parents.put(v.actorId, q);
                    edge.put(v.actorId, v);
                    
                    if (v.actorId.equals(actor2)) {
                        found = true;
                        break;
                    }
                }
            }
        }

        if (!parents.containsKey(actor2)) {
            return;
        }

        List<String> path = new ArrayList<>();
        String current = actor2;
        while (current != null) {
            path.add(current);
            current = parents.get(current);
        }
        Collections.reverse(path);

        System.out.println(G.get(actor1).actorName);
        for (int i = 0; i < path.size() - 1; i++) {
            Edge e = edge.get(path.get(i+1));
            String actorName = e.name;
            String movieName = e.movieName;
            double rating = e.rating;
            System.out.println("===[ " + movieName + " (" + rating + ") ] ===> " + actorName);
        }
        System.out.println();
    }
}