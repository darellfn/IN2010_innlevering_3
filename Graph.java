import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Graph {
    HashMap<String, Double> validMovies;
    HashMap<String, String> movieNames;
    HashMap<String, List<String>> movieActors;
    HashMap<String, Vertex> graph;

    public Graph() {
        this.validMovies = new HashMap<>();
        this.movieActors = new HashMap<>();
        this.movieNames = new HashMap<>();
        this.graph = new HashMap<>();
    }
    
    public HashMap<String, Vertex> buildGraph(String movieFile, String actorFile) {
        buildMovies(movieFile);
        buildActors(actorFile);

        for (String movieId : movieActors.keySet()) {
            List<String> actors = movieActors.get(movieId);
            double rating = validMovies.get(movieId);
            String movieName = movieNames.get(movieId);

            for (int i = 0; i < actors.size(); i++) {
                String actorA = actors.get(i);
                Vertex vA = graph.get(actorA);

                for (int j = i + 1; j < actors.size(); j++) {
                    String actorB = actors.get(j);
                    Vertex vB = graph.get(actorB);

                    vA.addEdge(new Edge(vB.actorName, actorB, movieId, movieName, rating));
                    vB.addEdge(new Edge(vA.actorName, actorA, movieId, movieName, rating));
                }
            }
        }

        return graph;
    }

    public void buildActors(String actorFile) {
        try {
            Scanner actorScanner = new Scanner(new File(actorFile));

            while (actorScanner.hasNextLine()) {
                String[] parts = actorScanner.nextLine().split("\t");
                
                if (parts.length < 2) continue;
                String actorId = parts[0];
                String actorName = parts[1];
                graph.putIfAbsent(actorId, new Vertex(actorId, actorName));

                for (int i = 2; i < parts.length; i++) {
                    String movieId = parts[i];
                    if (!validMovies.containsKey(movieId)) continue; 
                    movieActors.putIfAbsent(movieId, new ArrayList<>());
                    movieActors.get(movieId).add(actorId);
                }
            }

            actorScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error - File not found");
        }
    }

    public void buildMovies(String movieFile) {
        try {
            Scanner movieScanner = new Scanner(new File(movieFile));

            while (movieScanner.hasNextLine()) {
                String[] parts = movieScanner.nextLine().split("\t");
                if (parts.length < 3) continue;
                
                String movieId = parts[0];
                String movieName = parts[1];
                double rating = Double.parseDouble(parts[2]);
                
                validMovies.put(movieId, rating);
                movieNames.put(movieId, movieName);
            }

            movieScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error - File not found");
        }
    }

    public void countNodesAndEdges() {
        System.out.println("Nodes: " + graph.size());
        int totalEdges = 0;
        for (Vertex v : graph.values()) {
            totalEdges += v.edges.size();
        }
        int edgeCount = totalEdges / 2; 
        System.out.println("Edges: " + edgeCount);
    }

    public void printNodesAndEdges() {
        System.out.println("\nSample edges:");
        graph.entrySet().stream().limit(5).forEach(e -> {
            System.out.println(e.getKey() + " -> " + e.getValue());
        });
    }
}