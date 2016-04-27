package com.project;

import com.project.models.DiGraph;

import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        DiGraph<String> graph = new DiGraph<>();
        String graphRoutes = "AB20 AD80 AG90 BF10 CF50 CD10 CH20 DC10 DG20 EG30 EB50 FC10 FD40 GA20";
        graph.createGraph(graphRoutes);
        Integer distance = graph.findDistance("ABFCDCDG", 7);
        String shortest = graph.dijkstraShortestPath("A", "G", new HashMap<>(), new HashSet<>(), new LinkedList<>());
        Set<String> possibleRoutes = graph.possibleRoutes("A", "G", 100, new HashSet<>());
        System.out.println("Distance: " + distance);
        System.out.println("Shortest: " + shortest);
        System.out.println("Possible: " + possibleRoutes);
        System.out.println();
    }
}