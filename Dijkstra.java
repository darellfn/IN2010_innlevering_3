import java.util.*;

public class Dijkstra {
    
    public void findChillestWay(HashMap<String, Vertex> G, String s, String e) {
        HashMap<String, Double> dist = new HashMap<>();
        for (String id : G.keySet()) {
            dist.put(id, Double.POSITIVE_INFINITY);
        }
        Set<String> visited = new HashSet<>();
        HashMap<String, String> parent = new HashMap<>();
        HashMap<String, Edge> edges = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        dist.put(s, 0.0);
        queue.offer(new Node(s, 0.0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            String u = node.id;
            if (visited.contains(u)) continue;
            visited.add(u);

            if (u.equals(e)) break;

            Vertex vertex = G.get(u);

            for (Edge edge : vertex.edges) {
                String v = edge.actorId;
                double w = 10.0 - edge.rating;
                double c = dist.get(u) + w;
                
                if (c < dist.get(v)) {
                    dist.put(v, c);
                    parent.put(v, u);
                    edges.put(v, edge);
                    queue.offer(new Node(v, c));
                }
            }
        }

        if (!parent.containsKey(e)) {
            return;
        }

        List<String> path = new ArrayList<>();
        String current = e;
        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }
        Collections.reverse(path);

        double totalWeight = 0.0;

        System.out.println(G.get(s).actorName);

        for (int i = 0; i < path.size() - 1; i++) {
            Edge edge = edges.get(path.get(i+1));
            System.out.println("===[ " + edge.movieName + " (" + edge.rating + ") ] ===> " + edge.name);
            totalWeight += (10 - edge.rating);
        }

        System.out.println("Total weight " + totalWeight + "\n");
    }
}