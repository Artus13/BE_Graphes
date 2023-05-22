package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;


public class DijkstraAlgorithmTest {

   @Test
   public void TestLongueurNulle() throws Exception{

      final String mapName = "D:\\Travail\\INSA\\3A\\BE_Graphes\\Maps\\europe\\france\\reunion.mapgr" ;

      final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

      final Graph graph = reader.read();

      Node origin = graph.get(0);

      Node destination = graph.get(0);

      ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));

      DijkstraAlgorithm algo = new DijkstraAlgorithm(data);

      ShortestPathSolution SingleNodePath = algo.run();

      assertEquals( 0.0, SingleNodePath.getPath().getLength(), 0.1);

      assertEquals(0.0, SingleNodePath.getPath().getMinimumTravelTime(), 0.1);
   }
}