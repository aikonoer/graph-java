package com.project;

import com.project.models.DiGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String inputString = "/home/brianmomongan/Documents/input.txt";
        final Path path = Paths.get(inputString);
        List<String> queue = new LinkedList<>();
        DiGraph<String> graph;

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(queue::add);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (!queue.isEmpty()) {
            graph = new DiGraph<>();
            graph.createGraph(queue.get(0));
            final String distance = "DISTANCE";
            final String shortest = "SHORTEST";
            final String possible = "POSSIBLE";
            List<String> results = queue
                    .stream()
                    .map(lines -> {
                        if (lines.startsWith(distance)) {
                            String input = lines.substring(distance.length()).trim();
                            return distance + " " + input + " = " + input + graph.findDistance(input, input.length() - 1);
                        } else if (lines.startsWith(shortest)) {
                            String input = lines.substring(shortest.length()).trim();
                            return shortest + " " + input + " = " + graph.dijkstraShortestPath(input.substring(0, 1), input.substring(1, 2), new HashMap<>(), new HashSet<>(), new LinkedList<>());
                        } else if (lines.startsWith(possible)) {
                            String input = lines.substring(shortest.length()).trim();
                            StringBuilder builder = new StringBuilder();
                            Set<String> result = graph.possibleRoutes(input.substring(0, 1), input.substring(1, 2), Integer.parseInt(input.substring(2)), new HashSet<>());
                            for (String s : result) {
                                builder.append(s).append(" ");
                            }
                            return input + " = " + builder.toString().trim();
                        } else {
                            return null;
                        }
                    }).collect(Collectors.toList());
            System.out.println(results);
        } else {
            System.out.println("Graph routes not found");
        }
    }
}
