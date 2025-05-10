package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import queue.IQueue;
import queue.MyLinkedQueue;
import stack.IStack;
import stack.MyStack;

public class GraphAlgorithms {

    public static List<Integer> bfs(IGraph graph, int from) {
        // 최종 아웃풋 결과를 출력하기 위한 list
        List<Integer> result = new ArrayList<>();
        // 방문했던 vertex, node 를 재방문 하지 않기 위해 set 에 방문했던 노드 저장
        Set<Integer> visited = new HashSet<>();
        // bfs 를 위한 queue
        IQueue<Integer> queue = new MyLinkedQueue<>();
        visited.add(from);
        queue.offer(from);  // from 부터 시작
        while (!queue.isEmpty()) {  // 큐가 빌 때까지
            Integer next = queue.poll();
            result.add(next);   // 큐에서 노드를 꺼내오면서 방문
            for (Integer n : graph.getNodes(next)) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.offer(n);
                }
            }
        }
        return result;
    }

    public static List<Integer> dfs(IGraph graph, int from) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        IStack<Integer> stack = new MyStack<>();
        visited.add(from);
        stack.push(from);
        while (stack.size() > 0) {
            Integer next = stack.pop();
            result.add(next);
            for (Integer n : graph.getNodes(next)) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    stack.push(n);
                }
            }
        }
        return result;
    }

    // queue 를 이용한 진입차수 방식
    public static List<Integer> topologicalSortIndegree(IGraph graph) {
        Map<Integer, Integer> indegreeCounter = graph.getIndegrees();
        IQueue<Integer> queue = new MyLinkedQueue<>();
        List<Integer> ret = new LinkedList<>();
        for (int v : graph.getVertexes()) {
            int count = indegreeCounter.getOrDefault(v, 0);
            if (count == 0) {
                queue.offer(v);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            ret.add(node);
            for (int nn : graph.getNodes(node)) {
                if (indegreeCounter.containsKey(nn)) {
                    int count = indegreeCounter.get(nn);
                    if (count - 1 == 0) {
                        queue.offer(nn);
                    }
                    indegreeCounter.put(nn, count - 1);
                }
            }
        }
        return ret;
    }

    // stack 구현
    public static List<Integer> topologicalSort(IGraph graph) {
        List<Integer> result = new ArrayList<>();
        IStack<Integer> stack = new MyStack<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> vertexes = graph.getVertexes();
        for (Integer vertex : vertexes) {
            if (!visited.contains(vertex)) {
                topologicalSort(graph, vertex, visited, stack);
            }
        }

        while (stack.size() > 0) {
            result.add(stack.pop());
        }
        return result;
    }

    public static int dijkstraShortestPath(IGraph graph, int src, int dst) {
        int size = 0;
        for (int n : graph.getVertexes()) {
            if (size < n) {
                size = n;
            }
        }
        int[] dist = new int[size + 1];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[src] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair top = pq.poll();
            if (dist[top.vertex] < top.distance) {
                continue;
            }
            for (int node : graph.getNodes(top.vertex)) {
                if (dist[node] > dist[top.vertex] + graph.getDistance(top.vertex, node)) {
                    dist[node] = dist[top.vertex] + graph.getDistance(top.vertex, node);
                    pq.add(new Pair(node, dist[node]));
                }
            }
        }
        return dist[dst];
    }

    private static void topologicalSort(IGraph graph, int vertex, Set<Integer> visited, IStack<Integer> stack) {
        visited.add(vertex);
        List<Integer> nodes = graph.getNodes(vertex);
        for (Integer n : nodes) {
            if (!visited.contains(n)) {
                topologicalSort(graph, n, visited, stack);
            }
        }

        stack.push(vertex);
    }

    private static class Pair implements Comparable<Pair> {
        int vertex;
        int distance;

        public Pair(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Pair o) {
            return distance - o.distance;
        }
    }
}
