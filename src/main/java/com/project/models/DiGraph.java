package com.project.models;

import java.util.*;

/**
 * Created by brianmomongan on 21/04/16.
 */
public class DiGraph<T extends String> {

    private Map<T, Vertex<T>> vertices = new HashMap<>();

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
}
