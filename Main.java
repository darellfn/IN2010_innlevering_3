import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        
        Graph graph = new Graph();
        HashMap<String, Vertex> G = graph.buildGraph("movies.tsv", "actors.tsv");
        HashMap<String, String> actorsAndCoActors = new HashMap<>();
        actorsAndCoActors.put("nm2255973", "nm0000460");
        actorsAndCoActors.put("nm0424060", "nm8076281");
        actorsAndCoActors.put("nm4689420", "nm0000365");
        actorsAndCoActors.put("nm0000288", "nm2143282");
        actorsAndCoActors.put("nm0637259", "nm0931324");
                
        System.out.println("----------- Oppgave 1 -----------\n");
        
        System.out.println("Representation: Films as edges");
        graph.countNodesAndEdges();
        
        System.out.println("\n----------- Oppgave 2 -----------\n");

        DFS dfs = new DFS();
        dfs.DFSFull(G);

        System.out.println("\n----------- Oppgave 3 -----------\n");

        BFS bfs = new BFS();

        for (String actor : actorsAndCoActors.keySet()) {
            bfs.BFSFull(G, actor, actorsAndCoActors.get(actor));
        }

        System.out.println("\n----------- Oppgave 4 -----------\n");

        Dijkstra d = new Dijkstra();

        for (String actor : actorsAndCoActors.keySet()) {
            d.findChillestWay(G, actor, actorsAndCoActors.get(actor));
        }
    }
}