import java.util.*;

public class DFS {

    public void DFSFull(HashMap<String, Vertex> G) {
        Set<String> visited = new HashSet<>();
        Map<Integer, Integer> sizeCount = new HashMap<>();

        for (String v : G.keySet()) {
            if (!visited.contains(v)) {
                int size = DFSVisit(G, v, visited);
                sizeCount.put(size, sizeCount.getOrDefault(size, 0) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : sizeCount.entrySet()) {
            System.out.println("There are " + entry.getValue() + " components of size " + entry.getKey());
        }
    }

    private int DFSVisit(HashMap<String, Vertex> G, String s, Set<String> visited) {
        Stack<String> stack = new Stack<>();
        stack.add(s);
        int count = 0;

        while (!stack.isEmpty()) {
            String u = stack.pop();
            if (!visited.contains(u)) {
                visited.add(u);
                count++;
                Vertex vertex = G.get(u);
                if (vertex != null) {
                    for (Edge v : vertex.edges) {
                        stack.push(v.actorId);
                    }
                }
            }
        }

        return count;
    }
}