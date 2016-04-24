package com.project;

import com.project.models.DiGraph;
import com.project.models.Vertex;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        DiGraph<String> graph = new DiGraph<>();
        graph.createGraph("AB5 AD2 AE4 BC3 BD6 BE8 CE8 DE6");
        String distance = "ABDEX ".trim();
        System.out.println(graph.findDistance(distance, distance.length() - 1));
        Set<Vertex<String>> graphbfs = graph.bfs("E", new HashSet<>(), new LinkedList<>());
        Set<Vertex<String>> graphdfs = graph.dfs("E", new HashSet<>());
        System.out.println();
    }
}