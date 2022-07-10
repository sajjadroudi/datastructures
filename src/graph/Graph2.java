package graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Graph2 {

/*
    private static class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(label, node.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private static final int INITIAL_SIZE = 0;

    private int[][] matrix;
    private int vertexCount;
    private final Map<String, Node> nodes;

    public Graph() {
        matrix = new int[INITIAL_SIZE][INITIAL_SIZE];
        nodes = new HashMap<>();
        vertexCount = 0;
    }

    public void addNode(String label) {
        var node = new Node(label);
        nodes.putIfAbsent(label, node);

        growMatrix();
        vertexCount++;
    }

    private void growMatrix() {
        int[][] newMatrix = new int[vertexCount + 1][vertexCount + 1];
        copy(matrix, newMatrix);

        matrix = newMatrix;
    }

    private void shrinkMatrix(int nodeIndex) {
        int[][] newMatrix = new int[vertexCount - 1][vertexCount - 1];

        for (int row = 0; row < vertexCount; row++) {
            if(row == nodeIndex) continue;

            for (int col = 0; col < vertexCount; col++) {
                if(col == nodeIndex) continue;

                int rowIndex = row, colIndex = col;

                if (row > nodeIndex) rowIndex--;
                if (col > nodeIndex) colIndex--;

                newMatrix[rowIndex][colIndex] = matrix[row][col];
            }
        }

        matrix = newMatrix;
    }

    private void copy(int[][] orig, int[][] dest) {
        for (int row = 0; row < orig.length; row++) {
            for (int col = 0; col < orig[row].length; col++) {
                dest[row][col] = orig[row][col];
            }
        }
    }

    public void removeNode(String label) {
        shrinkMatrix(getIndex(label));
//        hashTable.remove(la)
        vertexCount--;
    }

    public void addEdge(String from, String to) {
        var fromIndex = getIndex(from);
        var toIndex = getIndex(to);
        matrix[fromIndex][toIndex] = 1;
    }

    public void removeEdge(String from, String to) {
        var fromNode = nodes.get(from);
        if(fromNode == null)
            throw new IllegalStateException();

        var toNode = nodes.get(to);
        if(toNode == null)
            throw new IllegalStateException();


    }

    private Node getNode(int nodeIndex) {
        var set = hashTable.entrySet();
        for(var entry : set) {
            if(entry.getValue() == nodeIndex)
                return entry.getKey();
        }
        return null;
    }

    private Node[] getNeighbors(int nodeIndex) {
        List<Node> list = new LinkedList<>();
        for(int col = 0; col < vertexCount; col++) {
            if(matrix[nodeIndex][col] == 1) {
                list.add(getNode(col));
            }
        }
        return list.toArray(new Node[0]);
    }

    private Node[] getNeighbors(Node node) {
        return getNeighbors(getIndex(node.label));
    }

    @Override
    public String toString() {
        var set = hashTable.entrySet();

        StringBuilder builder = new StringBuilder();
        for(var entry : set) {
            Node[] neighbors = getNeighbors(entry.getKey());

            builder
                    .append(entry.getKey())
                    .append(" -> ")
                    .append(Arrays.toString(neighbors))
                    .append("\n");
        }

        return builder.toString();
    }

    private int getIndex(String label) {
        return hashTable.get(new Node(label));
    }
*/

}
