package com.project.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by brianmomongan on 21/04/16.
 */
public class DiGraph<T extends String> {

    private Map<T, Vertex<T>> vertices = new HashMap<>();

    public Map<T, Vertex<T>> getVertices() {
        return vertices;
    }

    public void createGraph(T source, T destination, int edge) {
        if (!vertices.containsKey(source)) vertices.put(source, new Vertex<>(source));
        if (!vertices.containsKey(destination)) vertices.put(destination, new Vertex<>(destination));
        vertices.get(source).addNeighbor(vertices.get(destination), edge);
    }

    public Integer findEdge(String from, String to) {
        Vertex<T> fromVertex = vertices.get(from);
        Vertex<T> toVertex = vertices.get(to);
        if (fromVertex != null && toVertex != null) {
            Optional<Neighbor<T>> neighbor = vertices
                    .get(from)
                    .getNeighbors()
                    .stream()
                    .filter(v -> v.getVertex() == toVertex).findFirst();
            return neighbor.isPresent() ? neighbor.get().getEdge() : -1;
        } else return -1;
    }

    public Integer findDistance(T route, int length) {
        if (route == null) return -1;
        if (length < 1) return 0;
        else {
            final int edge = findEdge(route.charAt(length - 1) + "", route.charAt(length) + "");
            if (edge == -1) return -1;
            else return edge + findDistance(route, length - 1);
        }
    }
}
