public class Paths {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addEdge("Parking Lot", "100 Campus");
        graph.addEdge("100 Campus", "99 Campus");
        graph.addEdge("100 Campus", "153 Campus");
        graph.addEdge("100 Campus", "79 Campus - Main Library");
        graph.addEdge("99 Campus", "98 Campus");
        graph.addEdge("99 Campus", "154 Campus");
        graph.addEdge("154 Campus", "153 Campus");
        graph.addEdge("153 Campus", "79 Campus - Main Library");

        graph.bfs("154 Campus", "98 Campus");



    }
}
