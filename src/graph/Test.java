package graph;

public class Test {

    public static void main(String[] args) {
        testMinSpanningTree();
    }

    private static void testMinSpanningTree() {
        var graph = new WeightedGraph();
        graph.addNodes("A", "B", "C", "D");
        graph.addEdge("A", "C", 1);
        graph.addEdge("A", "B", 3);
        graph.addEdge("C", "B", 2);
        graph.addEdge("C", "D", 5);
        graph.addEdge("B", "D", 4);
        System.out.println(graph.getMinimumSpanningTree());
    }

    private static void testHasCycle() {
        var graph = new WeightedGraph();
        graph.addNodes("A", "B", "C");
        graph.addEdge("A", "B", 0);
        graph.addEdge("A", "C", 0);
        graph.addEdge("B", "C", 0);
        System.out.println(graph.hasCycle());
    }

    private static void testShortestPath() {
        var graph = new WeightedGraph();
        graph.addNodes("A", "B", "C", "D", "E");
        graph.addEdge("A", "C", 4);
        graph.addEdge("A", "B",3 );
        graph.addEdge("A", "D", 2);
        graph.addEdge("B", "D", 6);
        graph.addEdge("B", "E", 1);
        graph.addEdge("D", "C", 1);
        graph.addEdge("D", "E", 5);
        System.out.println(graph.getShortestPath("C", "A"));
    }

    private static void testWeightedGraph() {
        var graph = new WeightedGraph();
        graph.addNodes("A", "B", "C");
        graph.addEdge("A", "B", 3);
        graph.addEdge("A", "C", 2);
        System.out.println(graph);
    }

    private static void testDirectedGraph() {
        Graph graph = new Graph();
        graph.addNodes("A", "B", "C", "D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "D");
        graph.addEdge("D", "C");
        graph.addEdge("A", "C");
        System.out.println(graph);

        graph.traverseDepthFirst("A");

        Graph graph2 = new Graph();
        graph2.addNodes("X", "A", "B", "P");
        graph2.addEdge("X", "B");
        graph2.addEdge("X", "A");
        graph2.addEdge("A", "P");
        graph2.addEdge("B", "P");
        System.out.println(graph2.topologicalSort());

        Graph graph3 = new Graph();
        graph3.addNodes("A", "B", "C", "D");
        graph3.addEdge("A", "B");
        graph3.addEdge("D", "A");
        graph3.addEdge("C", "A");
        graph3.addEdge("B", "C");
        System.out.println(graph3.hasCycle());
    }
}
