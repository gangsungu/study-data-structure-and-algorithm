package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyMatrixGraph implements IGraph {
    private Integer[][] matrix;
    private Set<Integer> vertexes;
    private Map<Integer, Integer> indegrees;

    public AdjacencyMatrixGraph(int numOfVertex) {
        this.vertexes = new HashSet<>();
        this.indegrees = new HashMap<>();
        this.matrix = new Integer[numOfVertex][];
        for (int i = 0; i < numOfVertex; i++) {
            this.matrix[i] = new Integer[numOfVertex];
        }
    }

    @Override
    public void add(int from, int to, int distance) {
        vertexes.add(from);
        vertexes.add(to);
        int count = indegrees.getOrDefault(to, 0);
        indegrees.put(to, count + 1);
        matrix[from][to] = distance;
    }

    @Override
    public void add(int from, int to) {
        vertexes.add(from);
        vertexes.add(to);
        int count = indegrees.getOrDefault(to, 0);
        indegrees.put(to, count + 1);
        matrix[from][to] = 1;
    }

    @Override
    public int getDistance(int from, int to) {
        return this.matrix[from][to];
    }

    @Override
    public Map<Integer, Integer> getIndegrees() {
        return this.indegrees;
    }

    @Override
    public Set<Integer> getVertexes() {
        return this.vertexes;
    }

    @Override
    public List<Integer> getNodes(int vertex) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < matrix[vertex].length; i++) {
            if (matrix[vertex][i] != null) {
                ret.add(i);
            }
        }
        return ret;
    }
}
