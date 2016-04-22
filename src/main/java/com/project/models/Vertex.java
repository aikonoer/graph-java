package com.project.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianmomongan on 21/04/16.
 */


public class Vertex<T> {

    private List<Neighbor<T>> neighbors = new ArrayList<>();
    private T element;

    Vertex(T element) {
        this.element = element;
    }

    void addNeighbor(Vertex<T> destination, int edge) {
        Neighbor<T> neighbor = new Neighbor<>(destination, edge);
        neighbors.add(neighbor);
    }

    public T getElement() {
        return element;
    }

    public List<Neighbor<T>> getNeighbors() {
        return neighbors;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((element == null) ? 0 : element.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (element == null) {
            if (other.element != null)
                return false;
        } else if (!element.equals(other.element))
            return false;
        return true;
    }
}
