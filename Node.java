class Node implements Comparable<Node> {
    String id;
    double distance;

    public Node(String id, double distance) {
        this.id = id;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.distance, other.distance);
    }
}