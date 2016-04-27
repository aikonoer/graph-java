package com.project.models;

import java.util.*;

/**
 * Created by brianmomongan on 21/04/16.
 */
public class DiGraph<T extends String> {

    final private Map<T, Vertex<T>> vertices = new HashMap<>();

    public Map<T, Vertex<T>> getVertices() {
        return vertices;
    }

    private void insertRoutes(T source, T destination, int edge) {
        if (!vertices.containsKey(source)) vertices.put(source, new Vertex<>(source));
        if (!vertices.containsKey(destination)) vertices.put(destination, new Vertex<>(destination));
        vertices.get(source).addNeighbor(vertices.get(destination), edge);
    }

    public void createGraph(String graph) {
        List<String> routes = Arrays.asList(graph.split(" "));
        routes.forEach(route -> {
            T src = (T) T.valueOf(route.charAt(0));
            T dst = (T) T.valueOf(route.charAt(1));
            Integer edge = Integer.parseInt(route.substring(2));
            insertRoutes(src, dst, edge);
        });
    }

    private Integer findEdge(T from, T to) {
        Vertex<T> fromVertex = vertices.get(from);
        Vertex<T> toVertex = vertices.get(to);
        if (fromVertex != null && toVertex != null) return fromVertex.getEdge(toVertex);
        else return -1;
    }

    public Integer findDistance(T route, int length) {
        if (route == null) return -1;
        if (length < 1) return 0;
        else {

            T src = (T) T.valueOf(route.charAt(length - 1));
            T dst = (T) T.valueOf(route.charAt(length));
            final int edge = findEdge(src, dst);
            if (edge == -1) return -1;
            else return edge + findDistance(route, length - 1);
        }
    }

    private Set<Vertex<T>> dfsLoop(Vertex<T> firstVertex, Set<Vertex<T>> found) {
        if (found.contains(firstVertex)) return found;
        else {
            found.add(firstVertex);
            firstVertex
                    .getNeighbors()
                    .stream()
                    .map(Neighbor::getVertex)
                    .sorted((x, y) -> x.getElement().compareTo(y.getElement()))
                    .forEach(vertex -> dfsLoop(vertex, found));
            return found;
        }
    }

    public Set<Vertex<T>> depthFirstSearch(T element, Set<Vertex<T>> set) {
        if (vertices.containsKey(element)) return dfsLoop(vertices.get(element), set);
        else return set;
    }

    private Set<Vertex<T>> bfsLoop(Vertex<T> current, Set<Vertex<T>> found, Queue<Vertex<T>> queue) {
        if (queue.isEmpty()) return found;
        else {
            queue.remove();
            current
                    .getNeighbors()
                    .stream()
                    .map(Neighbor::getVertex)
                    .sorted((x, y) -> x.getElement().compareTo(y.getElement()))
                    .forEach(vertex -> {
                        if (found.add(vertex)) queue.add(vertex);
                    });
            return bfsLoop(queue.peek(), found, queue);
        }
    }

    public Set<Vertex<T>> breadthFirstSearch(T element, Set<Vertex<T>> set, Queue<Vertex<T>> queue) {
        if (vertices.containsKey(element)) {
            Vertex<T> vertex = vertices.get(element);
            queue.add(vertex);
            set.add(vertex);
            return bfsLoop(queue.peek(), set, queue);
        } else return set;
    }

    private Map<Vertex<T>, Neighbor<T>> dspLoop(Vertex<T> current, Vertex<T> toVertex, Map<Vertex<T>, Neighbor<T>> found, Set<Vertex<T>> uniqueQueue, Queue<Vertex<T>> queue) {
        if (queue.isEmpty()) return found;
        else if (found.containsKey(toVertex) && found.get(current).getEdge() > found.get(toVertex).getEdge()) {
            uniqueQueue.remove(queue.peek());
            queue.remove();
            return dspLoop(queue.peek(), toVertex, found, uniqueQueue, queue);
        } else {
            uniqueQueue.remove(queue.peek());
            queue.remove();
            current
                    .getNeighbors()
                    .stream()
                    .forEach(tNeighbor -> {
                        if (!found.containsKey(tNeighbor.getVertex())) {
                            found.put(tNeighbor.getVertex(), new Neighbor<>(current, found.get(current).getEdge() + tNeighbor.getEdge()));
                            uniqueQueue.add(tNeighbor.getVertex());
                            queue.add(tNeighbor.getVertex());
                        } else {
                            if (found.get(current).getEdge() + tNeighbor.getEdge() < found.get(tNeighbor.getVertex()).getEdge()) {
                                found.get(tNeighbor.getVertex()).setEdge(found.get(current).getEdge() + tNeighbor.getEdge());
                                found.get(tNeighbor.getVertex()).setVertex(current);
                                if (uniqueQueue.add(tNeighbor.getVertex())) queue.add(tNeighbor.getVertex());
                            }
                        }

                    });

            return dspLoop(queue.peek(), toVertex, found, uniqueQueue, queue);
        }
    }

    private String dsPaths(Vertex<T> from, Vertex<T> to, Map<Vertex<T>, Neighbor<T>> map) {
        if (from == to) return from.getElement();
        else {
            return dsPaths(map.get(from).getVertex(), to, map) + from.getElement();
        }
    }

    public String dijkstraShortestPath(T from, T to, Map<Vertex<T>, Neighbor<T>> found, Set<Vertex<T>> uniqueQueue, Queue<Vertex<T>> queue) {
        Vertex<T> fromVertex = vertices.get(from);
        Vertex<T> toVertex = vertices.get(to);

        found.put(fromVertex, new Neighbor<>(fromVertex, 0));
        queue.add(fromVertex);
        Map<Vertex<T>, Neighbor<T>> map = dspLoop(fromVertex, vertices.get(to), found, uniqueQueue, queue);

        return map.containsKey(toVertex) ? dsPaths(toVertex, fromVertex, map) + map.get(toVertex).getEdge() : "Not found";
    }

    private Set<String> prLoop(Vertex<T> currentVertex, Integer currentDistance, String currentRoute, Vertex<T> toVertex, Integer max, Set<String> found) {
        if (currentDistance > max) return found;
        else {
            if (currentVertex == toVertex) found.add(currentRoute + currentDistance);
            currentVertex
                    .getNeighbors()
                    .stream()
                    .forEach(tNeighbor -> prLoop(tNeighbor.getVertex()
                            , currentDistance + tNeighbor.getEdge()
                            , currentRoute + tNeighbor.getVertex().getElement()
                            , toVertex, max, found));
            return found;
        }
    }

    public Set<String> possibleRoutes(T from, T to, Integer max, Set<String> list) {
        Vertex<T> fromVertex = vertices.get(from);
        Vertex<T> toVertex = vertices.get(to);
        return prLoop(fromVertex, 0, new String(from), toVertex, max, list);
    }
}






