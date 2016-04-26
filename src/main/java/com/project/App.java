package com.project;

import com.project.models.DiGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        DiGraph<String> graph = new DiGraph<>();
        String graphRoutes = "AB20 AD80 AG90 BF10 CF50 CD10 CH20 DC10 DG20 EG30 EB50 FC10 FD40 GA20";
        graph.createGraph(graphRoutes);
        String shortest = graph.dijkstraShortestPath("A", "G", new HashMap<>(), new HashSet<>(), new LinkedList<>());
        System.out.println(shortest);
        System.out.println();
    }
}