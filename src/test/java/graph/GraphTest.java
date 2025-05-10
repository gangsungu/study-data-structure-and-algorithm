package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GraphTest {
    @Test
    public void bfs_list() {
        IGraph graph = new AdjacencyListGraph(10);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(2, 3);
        graph.add(2, 5);
        graph.add(1, 3);

        /*
         0 - 1
         |   |
         2 - 3
         |
         5
         */
        List<Integer> bfsResult = GraphAlgorithms.bfs(graph, 0);
        assertEquals(5, bfsResult.size());
        assertEquals(0, bfsResult.get(0));
        assertEquals(1, bfsResult.get(1));
        assertEquals(2, bfsResult.get(2));
        assertEquals(3, bfsResult.get(3));
        assertEquals(5, bfsResult.get(4));
    }

    @Test
    public void dfs_list() {
        IGraph graph = new AdjacencyListGraph(10);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(2, 3);
        graph.add(2, 5);
        graph.add(1, 3);

        /*
         0 - 1
         |   |
         2 - 3
         |
         5
         */
        List<Integer> bfsResult = GraphAlgorithms.dfs(graph, 0);
        assertEquals(5, bfsResult.size());
        assertEquals(0, bfsResult.get(0));
        assertEquals(2, bfsResult.get(1));
        assertEquals(5, bfsResult.get(2));
        assertEquals(3, bfsResult.get(3));
        assertEquals(1, bfsResult.get(4));
    }

    @Test
    public void topological_sort_list() {
        IGraph graph = new AdjacencyListGraph(10);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(2, 5);
        graph.add(1, 3);
        graph.add(3, 5);

        /*
         0 - 1
         |   |
         2   3
         |  /
         5
         */
        List<Integer> sort = GraphAlgorithms.topologicalSort(graph);
        assertEquals(5, sort.size());
        assertEquals(0, sort.get(0));
        assertEquals(2, sort.get(1));
        assertEquals(1, sort.get(2));
        assertEquals(3, sort.get(3));
        assertEquals(5, sort.get(4));
    }

    @Test
    public void topological_sort_indegree_list() {
        IGraph graph = new AdjacencyListGraph(10);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(2, 5);
        graph.add(1, 3);
        graph.add(3, 5);

        /*
         0 - 1
         |   |
         2   3
         |  /
         5
         */
        List<Integer> sort = GraphAlgorithms.topologicalSortIndegree(graph);
        assertEquals(5, sort.size());
        assertEquals(0, sort.get(0));
        assertEquals(1, sort.get(1));
        assertEquals(2, sort.get(2));
        assertEquals(3, sort.get(3));
        assertEquals(5, sort.get(4));
    }

    @Test
    public void shortest_path_list() {
        IGraph graph = new AdjacencyListGraph(10);
        graph.add(0, 1, 5);
        graph.add(0, 2, 3);
        graph.add(2, 5, 10);
        graph.add(1, 3, 2);
        graph.add(3, 5, 2);

        /*
         0 - 1
         |   |
         2   3
         |  /
         5
         */
        int shortestDistance = GraphAlgorithms.dijkstraShortestPath(graph, 0, 5);
        assertEquals(9, shortestDistance);
    }

    @Test
    public void bfs_matrix() {
        IGraph graph = new AdjacencyMatrixGraph(10);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(2, 3);
        graph.add(2, 5);
        graph.add(1, 3);

        /*
         0 - 1
         |   |
         2 - 3
         |
         5
         */
        List<Integer> bfsResult = GraphAlgorithms.bfs(graph, 0);
        assertEquals(5, bfsResult.size());
        assertEquals(0, bfsResult.get(0));
        assertEquals(1, bfsResult.get(1));
        assertEquals(2, bfsResult.get(2));
        assertEquals(3, bfsResult.get(3));
        assertEquals(5, bfsResult.get(4));
    }

    @Test
    public void dfs_matrix() {
        IGraph graph = new AdjacencyMatrixGraph(10);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(2, 3);
        graph.add(2, 5);
        graph.add(1, 3);

        /*
         0 - 1
         |   |
         2 - 3
         |
         5
         */
        List<Integer> bfsResult = GraphAlgorithms.dfs(graph, 0);
        assertEquals(5, bfsResult.size());
        assertEquals(0, bfsResult.get(0));
        assertEquals(2, bfsResult.get(1));
        assertEquals(5, bfsResult.get(2));
        assertEquals(3, bfsResult.get(3));
        assertEquals(1, bfsResult.get(4));
    }

    @Test
    public void topological_sort_matrix() {
        IGraph graph = new AdjacencyMatrixGraph(10);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(2, 5);
        graph.add(1, 3);
        graph.add(3, 5);

        /*
         0 - 1
         |   |
         2   3
         |  /
         5
         */
        List<Integer> sort = GraphAlgorithms.topologicalSort(graph);
        assertEquals(5, sort.size());
        assertEquals(0, sort.get(0));
        assertEquals(2, sort.get(1));
        assertEquals(1, sort.get(2));
        assertEquals(3, sort.get(3));
        assertEquals(5, sort.get(4));
    }

    @Test
    public void topological_sort_indegree_matrix() {
        IGraph graph = new AdjacencyMatrixGraph(10);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(2, 5);
        graph.add(1, 3);
        graph.add(3, 5);

        /*
         0 - 1
         |   |
         2   3
         |  /
         5
         */
        List<Integer> sort = GraphAlgorithms.topologicalSortIndegree(graph);
        assertEquals(5, sort.size());
        assertEquals(0, sort.get(0));
        assertEquals(1, sort.get(1));
        assertEquals(2, sort.get(2));
        assertEquals(3, sort.get(3));
        assertEquals(5, sort.get(4));
    }

    @Test
    public void shortest_path_matrix() {
        IGraph graph = new AdjacencyMatrixGraph(10);
        graph.add(0, 1, 5);
        graph.add(0, 2, 3);
        graph.add(2, 5, 10);
        graph.add(1, 3, 2);
        graph.add(3, 5, 2);

        /*
         0 - 1
         |   |
         2   3
         |  /
         5
         */
        int shortestDistance = GraphAlgorithms.dijkstraShortestPath(graph, 0, 5);
        assertEquals(9, shortestDistance);
    }
}
