package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Graph {

    private static class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private final Map<String, Node> nodes;
    private final Map<Node, List<Node>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
        nodes = new HashMap<>();
    }

    public void addNodes(String... labels) {
        for (String label : labels)
            addNode(label);
    }

    public void addNode(String label) {
        var node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void removeNode(String label) {
        final var node = nodes.get(label);
        if (node == null) return;

        nodes.remove(label);

        adjacencyList.remove(node);

        var set = adjacencyList.entrySet();
        set.forEach(entry -> entry.getValue().remove(node));
    }

    public void addEdge(String from, String to) {
        var fromNode = nodes.get(from);
        if (fromNode == null)
            throw new IllegalStateException();

        var toNode = nodes.get(to);
        if (toNode == null)
            throw new IllegalStateException();

        var list = adjacencyList.get(fromNode);
        if (!list.contains(toNode)) list.add(toNode);
    }

    public void removeEdge(String from, String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void traverseDepthFirstRec(String startLabel) {
        var startNode = nodes.get(startLabel);
        if (startNode == null) return;
        traverseDepthFirstRec(startNode, new HashSet<>());
    }

    private void traverseDepthFirstRec(Node node, Set<Node> visitedSet) {
        if (visitedSet.contains(node)) return;
        System.out.print(node + " "); // visit
        visitedSet.add(node);
        var neighbors = adjacencyList.get(node);
        neighbors.forEach(neighbor -> traverseDepthFirstRec(neighbor, visitedSet));
    }

    public void traverseDepthFirst(String startLabel) {
        var startNode = nodes.get(startLabel);
        if (startNode == null) return;

        var visited = new HashSet<Node>();

        var stack = new Stack<Node>();
        stack.push(startNode);
        while (!stack.isEmpty()) {
            var current = stack.pop();
            if (visited.contains(current))
                continue;

            System.out.print(current + " "); // visit
            visited.add(current);

            var neighbors = adjacencyList.get(current);
            neighbors.forEach(stack::push);
        }
    }

    public void traverseBreadthFirst(String startLabel) {
        var startNode = nodes.get(startLabel);
        if (startNode == null) return;

        var visited = new HashSet<Node>();

        var queue = new ArrayDeque<Node>();
        queue.add(startNode);
        while (!queue.isEmpty()) {
            var current = queue.remove();

            if (visited.contains(current))
                continue;

            System.out.print(current + " "); // visit
            visited.add(current);

            var neighbors = adjacencyList.get(current);
            queue.addAll(neighbors);
        }
    }

    public List<String> topologicalSort() {
        Stack<Node> nodesStack = null, stack;

        var values = nodes.values();
        for(var node : values) {
            stack = new Stack<>();
            topologicalSort(node, new HashSet<>(), stack);
            if(nodesStack == null || stack.size() > nodesStack.size())
                nodesStack = stack;
        }

        List<String> sortedList = new ArrayList<>();
        while(!nodesStack.isEmpty())
            sortedList.add(nodesStack.pop().label);

        return sortedList;
    }

    private void topologicalSort(Node node, Set<Node> visited, Stack<Node> stack) {
        if(visited.contains(node))
            return;

        visited.add(node);

        adjacencyList.get(node).forEach(neighbor -> {
            topologicalSort(neighbor, visited, stack);
        });

        stack.push(node);
    }

    // TODO: detecting path is not working
    public boolean hasCycle() {
        var all = new HashSet<>(nodes.values());
        var stack = new Stack<Node>();
        while(!all.isEmpty()) {
            stack.clear();
            if(hasCycle(all.iterator().next(), all, new HashSet<>(), new HashSet<>(), stack)) {
                System.out.println(stack);
                return true;
            }
        }

        return false;
    }

    private boolean hasCycle(Node node, HashSet<Node> all, Set<Node> visiting, Set<Node> visited, Stack<Node> path) {
        all.remove(node);
        visiting.add(node);

        var neighbors = adjacencyList.get(node);
        for(var neighbor : neighbors) {
            if(visited.contains(neighbor)) continue;
            if(visiting.contains(neighbor)) {
                path.push(node);
                path.push(neighbor);
                return true;
            }
            if(hasCycle(neighbor, all, visiting, visited, path)) {
                path.push(neighbor);
                return true;
            }
        }

        visiting.remove(node);
        visited.add(node);

        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        adjacencyList.forEach((key, value) -> {
            builder
                    .append(key)
                    .append(" -> ")
                    .append(value)
                    .append("\n");
        });

        return builder.toString();
    }
}
