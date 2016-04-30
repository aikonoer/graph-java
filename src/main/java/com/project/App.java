package com.project;

import com.project.models.Graph;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Graph algorithm
 */
public class App {
    public static void main(String[] args) {

        final Path path = Paths.get(args[0]);
        final Path parent = path.getParent();
        List<String> queue = new LinkedList<>();

        try (Stream<String> stream = Files.lines(path)) {
            stream.forEach(queue::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> results = processGraph(queue);

        try {
            Files.write(Paths.get(parent + "/output.txt"), results, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<String> processGraph(List<String> queue) {
        Graph<String> graph;
        List<String> results = new ArrayList<>();
        if (!queue.isEmpty()) {
            graph = new Graph<>();
            graph.createGraph(queue.get(0));
            queue.remove(0);
            final String distance = "DISTANCE";
            final String shortest = "SHORTEST";
            final String possible = "POSSIBLE";
            final String noRoutes = "NO POSSIBLE ROUTES";
            results = queue
                    .stream()
                    .map(lines -> {
                        if (lines.startsWith(distance)) {
                            String input = lines.substring(distance.length()).trim();
                            Integer totalDistance = graph.findDistance(input, input.length() - 1);
                            if (totalDistance > 0) return distance + " " + input + " = " + input + totalDistance;
                            else return distance + " " + input + " = " + noRoutes;
                        } else if (lines.startsWith(shortest)) {
                            String input = lines.substring(shortest.length()).trim();
                            String shortestRoutes = graph.dijkstraShortestPath(input.substring(0, 1), input.substring(1, 2), new HashMap<>(), new HashSet<>(), new LinkedList<>());

                            return (shortestRoutes != null) ? shortest + " " + input + " = " + shortestRoutes : shortest + " " + input + " = " + noRoutes;
                        } else if (lines.startsWith(possible)) {
                            String input = lines.substring(shortest.length()).trim();
                            StringBuilder builder = new StringBuilder();
                            Set<String> result = graph.possibleRoutes(input.substring(0, 1), input.substring(1, 2), Integer.parseInt(input.substring(2)), new HashSet<>());
                            for (String s : result) {
                                builder.append(s).append(" ");
                            }
                            return possible + " " + input + " = " + ((builder.length() != 0) ? builder.toString().trim() : noRoutes);
                        } else {
                            return null;
                        }
                    }).collect(Collectors.toList());
        } else {
            results.add("Graph routes not found");
        }
        return results;
    }

}
