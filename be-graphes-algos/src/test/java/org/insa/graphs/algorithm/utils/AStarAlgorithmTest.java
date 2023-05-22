package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.assertEquals;
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
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;


public class AStarAlgorithmTest {

   @Test
   public void TestCheminInexistant() throws Exception{

      final String mapName = "D:\\Travail\\INSA\\3A\\BE_Graphes\\Maps\\europe\\france\\bretagne.mapgr" ;

      final GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

      final Graph graph = reader.read();

      Node origin = graph.get(383731);

      Node destination = graph.get(548481);

      ShortestPathData data = new ShortestPathData(graph, origin, destination, ArcInspectorFactory.getAllFilters().get(0));

      AStarAlgorithm algo = new AStarAlgorithm(data);

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

      AStarAlgorithm algo = new AStarAlgorithm(data);

      ShortestPathSolution Path = algo.run();

      assertEquals(Status.INFEASIBLE, Path.getStatus());
   }

   @Test
   public void TestAStarShortestAllRoads() throws Exception{
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

         AStarAlgorithm AStar = new AStarAlgorithm(data);

         BellmanFordAlgorithm BellmanFord = new BellmanFordAlgorithm(data);

         ShortestPathSolution PathAStar = AStar.run();

         ShortestPathSolution PathBellmanFord = BellmanFord.run();

         if(PathAStar.getPath() == null && PathBellmanFord.getPath() == null){
            assertEquals(null, PathAStar.getPath());
        } 
        else{
            assertEquals(PathBellmanFord.getPath().getLength(), PathAStar.getPath().getLength(), 1e-3);
        }
      }
   }

   @Test
   public void TestAStarShortestOnlyCars() throws Exception{
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

         AStarAlgorithm AStar = new AStarAlgorithm(data);

         BellmanFordAlgorithm BellmanFord = new BellmanFordAlgorithm(data);

         ShortestPathSolution PathAStar = AStar.run();

         ShortestPathSolution PathBellmanFord = BellmanFord.run();

         if(PathAStar.getPath() == null && PathBellmanFord.getPath() == null){
            assertEquals(null, PathAStar.getPath());
        } 
        else{
            assertEquals(PathBellmanFord.getPath().getLength(), PathAStar.getPath().getLength(), 1e-3);
        }
      }
   }

   @Test
   public void TestAStarFastestAllRoads() throws Exception{
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

         AStarAlgorithm AStar = new AStarAlgorithm(data);

         BellmanFordAlgorithm BellmanFord = new BellmanFordAlgorithm(data);

         ShortestPathSolution PathAStar = AStar.run();

         ShortestPathSolution PathBellmanFord = BellmanFord.run();

         if(PathAStar.getPath() == null && PathBellmanFord.getPath() == null){
            assertEquals(null, PathAStar.getPath());
        } 
        else{
            assertEquals(PathBellmanFord.getPath().getMinimumTravelTime(), PathAStar.getPath().getMinimumTravelTime(), 1e-3);
        }
      }
   }

   @Test
   public void TestAStarFastestOnlyCars() throws Exception{
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

         AStarAlgorithm AStar = new AStarAlgorithm(data);

         BellmanFordAlgorithm BellmanFord = new BellmanFordAlgorithm(data);

         ShortestPathSolution PathAStar = AStar.run();

         ShortestPathSolution PathBellmanFord = BellmanFord.run();

         if(PathAStar.getPath() == null && PathBellmanFord.getPath() == null){
            assertEquals(null, PathAStar.getPath());
        } 
        else{
            assertEquals(PathBellmanFord.getPath().getMinimumTravelTime(), PathAStar.getPath().getMinimumTravelTime(), 1e-3);
        }
      }
   }

   @Test
   public void TestAStarFastestPedestrian() throws Exception{
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

         AStarAlgorithm AStar = new AStarAlgorithm(data);

         BellmanFordAlgorithm BellmanFord = new BellmanFordAlgorithm(data);

         ShortestPathSolution PathAStar = AStar.run();

         ShortestPathSolution PathBellmanFord = BellmanFord.run();

         if(PathAStar.getPath() == null && PathBellmanFord.getPath() == null){
            assertEquals(null, PathAStar.getPath());
        } 
        else{
            assertEquals(PathBellmanFord.getPath().getMinimumTravelTime(), PathAStar.getPath().getMinimumTravelTime(), 1e-3);
        }
      }
   }
}