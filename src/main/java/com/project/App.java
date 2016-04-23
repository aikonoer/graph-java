package com.project;

import java.util.Arrays;
import java.util.List;

import com.project.models.DiGraph;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        DiGraph<String> graph = new DiGraph<>();
        graph.createGraph("AB5 AD2 AE4 BC3 BD6 BE8 CE8 DE6");
        String distance = "ABDEX ".trim();
        System.out.println(graph.findDistance(distance, distance.length() - 1));
        graph
                .getVertices()
                .values()
                .forEach(v -> v.getNeighbors().forEach(n -> System.out.println(v.getElement() + " " + n.getVertex().getElement() + " " + n.getEdge())));
    }
}
