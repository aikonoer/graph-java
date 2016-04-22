package com.project.models;

/**
 * Created by brianmomongan on 21/04/16.
 */
public class Neighbor<T> {

    private Vertex<T> vertex;
    private int edge;

    public Neighbor(Vertex<T> vertex, int edge) {
        this.vertex = vertex;
        this.edge = edge;
    }

    public Vertex<T> getVertex() {
        return vertex;
    }

    public int getEdge() {
        return edge;
    }
}
