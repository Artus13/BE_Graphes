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
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;


public class DijkstraAlgorithmTest {

   @Test
   public void TestCheminInexistant() throws Exception{

      final String mapName = "D:\\Travail\\INSA\\3A\\BE_Graphes\\Maps\\europe\\france\\bretagne.mapgr" ;

      final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

      final Graph graph = reader.read();

      Node origin = graph.get(383731);

      Node destination = graph.get(548481);

      ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));

      DijkstraAlgorithm algo = new DijkstraAlgorithm(data);

      ShortestPathSolution Path = algo.run();

      assertEquals(Status.INFEASIBLE, Path.getStatus());
   }

   @Test
   public void TestLongueurNulle() throws Exception{

      final String mapName = "D:\\Travail\\INSA\\3A\\BE_Graphes\\Maps\\europe\\france\\toulouse.mapgr" ;

      final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

      final Graph graph = reader.read();

      Node origin = graph.get(0);

      Node destination = graph.get(0);

      ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));

      DijkstraAlgorithm algo = new DijkstraAlgorithm(data);

      ShortestPathSolution Path = algo.run();

      assertEquals(Status.INFEASIBLE, Path.getStatus());
   }

   @Test
   public void TestDijkstraShortestAllRoads() throws Exception{
      final String mapName = "D:\\Travail\\INSA\\3A\\BE_Graphes\\Maps\\europe\\france\\toulouse.mapgr" ;

      final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

      final Graph graph = reader.read();

      int max = graph.size();

      int min = 0;

      for(int i=0; i<10; i++){
         int idOrigin = (int)Math.floor(Math.random() * (max - min));

         int idDestination = (int)Math.floor(Math.random() * (max - min)) ;

         while (idOrigin == idDestination){
            idDestination = (int)Math.floor(Math.random() * (max - min)) ;
         }

         Node origin = graph.get(idOrigin);

         Node destination = graph.get(idDestination);

         ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));

         DijkstraAlgorithm Dijkstra = new DijkstraAlgorithm(data);

         BellmanFordAlgorithm BellmanFord = new BellmanFordAlgorithm(data);

         ShortestPathSolution PathDijkstra = Dijkstra.run();

         ShortestPathSolution PathBellmanFord = BellmanFord.run();

         if(PathDijkstra.getPath() == null && PathBellmanFord.getPath() == null){
            assertEquals(null, PathDijkstra.getPath());
        } 
        else{
            assertEquals(PathBellmanFord.getPath().getLength(), PathDijkstra.getPath().getLength(), 1e-3);
        }
      }
   }

   @Test
   public void TestDijkstraShortestOnlyCars() throws Exception{
      final String mapName = "D:\\Travail\\INSA\\3A\\BE_Graphes\\Maps\\europe\\france\\toulouse.mapgr" ;

      final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

      final Graph graph = reader.read();

      int max = graph.size();

      int min = 0;

      for(int i=0; i<10; i++){
         int idOrigin = (int)Math.floor(Math.random() * (max - min));

         int idDestination = (int)Math.floor(Math.random() * (max - min)) ;

         while (idOrigin == idDestination){
            idDestination = (int)Math.floor(Math.random() * (max - min)) ;
         }

         Node origin = graph.get(idOrigin);

         Node destination = graph.get(idDestination);

         ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(1));

         DijkstraAlgorithm Dijkstra = new DijkstraAlgorithm(data);

         BellmanFordAlgorithm BellmanFord = new BellmanFordAlgorithm(data);

         ShortestPathSolution PathDijkstra = Dijkstra.run();

         ShortestPathSolution PathBellmanFord = BellmanFord.run();

         if(PathDijkstra.getPath() == null && PathBellmanFord.getPath() == null){
            assertEquals(null, PathDijkstra.getPath());
        } 
        else{
            assertEquals(PathBellmanFord.getPath().getLength(), PathDijkstra.getPath().getLength(), 1e-3);
        }
      }
   }

   @Test
   public void TestDijkstraFastestAllRoads() throws Exception{
      final String mapName = "D:\\Travail\\INSA\\3A\\BE_Graphes\\Maps\\europe\\france\\toulouse.mapgr" ;

      final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

      final Graph graph = reader.read();

      int max = graph.size();

      int min = 0;

      for(int i=0; i<10; i++){
         int idOrigin = (int)Math.floor(Math.random() * (max - min));

         int idDestination = (int)Math.floor(Math.random() * (max - min)) ;

         while (idOrigin == idDestination){
            idDestination = (int)Math.floor(Math.random() * (max - min)) ;
         }

         Node origin = graph.get(idOrigin);

         Node destination = graph.get(idDestination);

         ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(2));

         DijkstraAlgorithm Dijkstra = new DijkstraAlgorithm(data);

         BellmanFordAlgorithm BellmanFord = new BellmanFordAlgorithm(data);

         ShortestPathSolution PathDijkstra = Dijkstra.run();

         ShortestPathSolution PathBellmanFord = BellmanFord.run();

         if(PathDijkstra.getPath() == null && PathBellmanFord.getPath() == null){
            assertEquals(null, PathDijkstra.getPath());
        } 
        else{
            assertEquals(PathBellmanFord.getPath().getMinimumTravelTime(), PathDijkstra.getPath().getMinimumTravelTime(), 1e-3);
        }
      }
   }

   @Test
   public void TestDijkstraFastestOnlyCars() throws Exception{
      final String mapName = "D:\\Travail\\INSA\\3A\\BE_Graphes\\Maps\\europe\\france\\toulouse.mapgr" ;

      final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

      final Graph graph = reader.read();

      int max = graph.size();

      int min = 0;

      for(int i=0; i<10; i++){
         int idOrigin = (int)Math.floor(Math.random() * (max - min));

         int idDestination = (int)Math.floor(Math.random() * (max - min)) ;

         while (idOrigin == idDestination){
            idDestination = (int)Math.floor(Math.random() * (max - min)) ;
         }

         Node origin = graph.get(idOrigin);

         Node destination = graph.get(idDestination);

         ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(3));

         DijkstraAlgorithm Dijkstra = new DijkstraAlgorithm(data);

         BellmanFordAlgorithm BellmanFord = new BellmanFordAlgorithm(data);

         ShortestPathSolution PathDijkstra = Dijkstra.run();

         ShortestPathSolution PathBellmanFord = BellmanFord.run();

         if(PathDijkstra.getPath() == null && PathBellmanFord.getPath() == null){
            assertEquals(null, PathDijkstra.getPath());
        } 
        else{
            assertEquals(PathBellmanFord.getPath().getMinimumTravelTime(), PathDijkstra.getPath().getMinimumTravelTime(), 1e-3);
        }
      }
   }

   @Test
   public void TestDijkstraFastestPedestrian() throws Exception{
      final String mapName = "D:\\Travail\\INSA\\3A\\BE_Graphes\\Maps\\europe\\france\\toulouse.mapgr" ;

      final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

      final Graph graph = reader.read();

      int max = graph.size();

      int min = 0;

      for(int i=0; i<10; i++){
         int idOrigin = (int)Math.floor(Math.random() * (max - min));

         int idDestination = (int)Math.floor(Math.random() * (max - min)) ;

         while (idOrigin == idDestination){
            idDestination = (int)Math.floor(Math.random() * (max - min)) ;
         }

         Node origin = graph.get(idOrigin);

         Node destination = graph.get(idDestination);

         ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(4));

         DijkstraAlgorithm Dijkstra = new DijkstraAlgorithm(data);

         BellmanFordAlgorithm BellmanFord = new BellmanFordAlgorithm(data);

         ShortestPathSolution PathDijkstra = Dijkstra.run();

         ShortestPathSolution PathBellmanFord = BellmanFord.run();

         if(PathDijkstra.getPath() == null && PathBellmanFord.getPath() == null){
            assertEquals(null, PathDijkstra.getPath());
        } 
        else{
            assertEquals(PathBellmanFord.getPath().getMinimumTravelTime(), PathDijkstra.getPath().getMinimumTravelTime(), 1e-3);
        }
      }
   }
}
