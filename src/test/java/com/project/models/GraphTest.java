package com.project.models;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by brianmomongan on 29/04/16.
 */
public class GraphTest extends TestCase {

    Graph<String> graph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph<>();
        String graphRoutes = "AB20 AD80 AG90 BF10 CF50 CD10 CH20 DC10 DG20 EG30 EB50 FC10 FD40 GA20";
        graph.createGraph(graphRoutes);
    }

    @Test
    public void testFindDistance() throws Exception {
        assertEquals(90, Math.toIntExact(graph.findDistance("ABFCDCDG", 7)));
    }

    @Test
    public void testDijkstraShortestPath() throws Exception {
        String expected = "ABFCDG70";
        assertEquals(expected, graph.dijkstraShortestPath("A", "G", new HashMap<>(), new HashSet<>(), new LinkedList<>()));
    }

    @Test
    public void testPossibleRoutes() throws Exception {
        final Set<String> possibleRoutes = graph.possibleRoutes("A", "G", 100, new HashSet<>());
        final Set<String> expected = new HashSet<>();
        expected.add("ABFCDCDG90");
        expected.add("ABFDG90");
        expected.add("ABFCDG70");
        expected.add("AG90");
        expected.add("ADG100");
        assertEquals(expected, possibleRoutes);
    }

}