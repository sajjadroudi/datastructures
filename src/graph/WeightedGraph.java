package graph;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class WeightedGraph {

    private static class Node {
        private String label;
        // It could be also implemented using a Map
        private List<Edge> edges;

        public Node(String label) {
            this.label = label;
            edges = new ArrayList<>();
        }

        public void addEdge(Node to, int weight) {
            edges.add(new Edge(this, to, weight));
        }

        public List<Edge> getEdges() {
            return edges;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private static class Edge {
        private Node from;
        private Node to;
        private int weight;

        public Edge(Node from, Node to) {
            this(from, to, 0);
        }

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to;
        }
    }

    private Map<String, Node> nodes;

    public WeightedGraph() {
        nodes = new HashMap<>();
    }

    public void addNodes(String... labels) {
        for (String label : labels)
            addNode(label);
    }

    public void addNode(String label) {
        nodes.putIfAbsent(label, new Node(label));
    }

    public void addEdge(String from, String to, int weight) {
        var fromNode = nodes.get(from);
        if (fromNode == null)
            throw new IllegalStateException();

        var toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalStateException();

        if (fromNode.edges.stream().noneMatch(edge -> edge.to == toNode)) {
            fromNode.addEdge(toNode, weight);
            toNode.addEdge(fromNode, weight);
        }
    }

    private static class NodeEntry {
        private Node node;
        private int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    public Path getShortestPath(String start, String end) {
        var startNode = nodes.get(start);
        var endNode = nodes.get(end);

        if(startNode == null || endNode == null)
            throw new IllegalArgumentException();

        var distances = new HashMap<Node, Integer>();
        var prevNodes = new HashMap<Node, Node>();

        // Initialization
        nodes.forEach((label, node) -> {
            distances.put(node, Integer.MAX_VALUE);
        });
        distances.replace(startNode, 0);

        var visited = new HashSet<Node>();

        var queue = new PriorityQueue<NodeEntry>(
                Comparator.comparingInt(ne -> ne.priority)
        );

        queue.add(new NodeEntry(startNode, 0));
        while(!queue.isEmpty()) {
            var current = queue.remove().node;
            visited.add(current);

            for(var edge : current.getEdges()) {
                Node neighbor = edge.to;
                if(visited.contains(neighbor)) continue;

                int knownDistance = distances.get(neighbor);

                int newDistance = distances.get(current) + edge.weight;

                if (newDistance < knownDistance) {
                    distances.replace(neighbor, newDistance);
                    prevNodes.put(neighbor, current);
                    queue.add(new NodeEntry(neighbor, newDistance));
                }
            }
        }

        var stack = new Stack<String>();
        var current = endNode;
        while(current != null) {
            stack.push(current.label);
            current = prevNodes.get(current);
        }

        var path = new Path();
        while(!stack.isEmpty())
            path.add(stack.pop());

        return path;
    }

    public boolean hasCycle() {
        var visited = new HashSet<Node>();

        for(var node : nodes.values()) {
            if(visited.contains(node))
                continue;

            if(hasCycle(node, null, visited))
                return true;
        }

        return false;
    }

    private boolean hasCycle(Node node, Node parent, Set<Node> visited) {
        visited.add(node);

        var edges = node.getEdges();
        for(var edge : edges) {
            Node neighbor = edge.to;
            if(neighbor != parent && visited.contains(neighbor))
                return true;
        }

        for(var edge : edges) {
            Node neighbor = edge.to;
            if(neighbor != parent
                    && !visited.contains(neighbor)
                    && hasCycle(neighbor, node, visited))
                return true;
        }

        return false;
    }

    public WeightedGraph getMinimumSpanningTree() {
        var tree = new WeightedGraph();

        var availableEdges = new PriorityQueue<Edge>(
                Comparator.comparingInt(e -> e.weight)
        );

        Node start = nodes.values().iterator().next();
        tree.addNode(start.label);

        getMinimumSpanningTree(start, availableEdges, new HashSet<>(), tree);

        return tree;
    }

    private void getMinimumSpanningTree(Node node, Queue<Edge> availableEdges, Set<Node> visited, WeightedGraph tree) {
        if(tree.nodes.size() == nodes.size())
            return;

        visited.add(node);

        for(var edge : node.getEdges()) {
            if(visited.contains(edge.to)) continue;
            availableEdges.add(edge);
        }

        Edge selectedEdge = availableEdges.remove();
        while(visited.contains(selectedEdge.to)) {
            selectedEdge = availableEdges.remove();
        }

        tree.addNode(selectedEdge.to.label);
        tree.addEdge(selectedEdge.from.label, selectedEdge.to.label, selectedEdge.weight);
        getMinimumSpanningTree(selectedEdge.to, availableEdges, visited, tree);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        nodes.forEach((label, node) -> {
            builder
                    .append(node)
                    .append(" -> ")
                    .append(node.getEdges())
                    .append("\n");
        });

        return builder.toString();
    }

    public int size() {
        return nodes.size();
    }

    public boolean contains(String label) {
        return nodes.containsKey(label);
    }

}
