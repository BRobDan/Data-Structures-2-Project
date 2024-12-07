package lab6;

import java.util.LinkedList;


// this class runs the Johnson Algorithm and prints the results
public class Johnson<T>
{
    public void johnsonAlgo(Graph<T> testGraph)
    {
        // catch and rethrown negative weight cycle exception
        try
        {
            // create Bellman ford object and pass the graph through its method
            BellmanFord<T> bellman = new BellmanFord<>();
            bellman.bellmanFord(testGraph);
            // the graph has now been re-weighted to have no negative edge weights
        } catch (IllegalStateException e)
        {
            throw new RuntimeException(e.getMessage(), e);
        }


        // formula to run djikstr'a algorithm and print out all the shortest paths and distances
        for (Node<T> node : testGraph.getNodes())
        {
            System.out.println("Node " + node.getName() + ":");
            for (Node<T> node1 : testGraph.getNodes()) // added this to reset all distances to 'infinite'
            {
                node1.setDistance(Integer.MAX_VALUE);
            }
            // create Djikstra object to run the algorithm
            Dijkstra<T> dijkstra = new Dijkstra<>();

            // reset shortest path for each node and run djikstra's
            testGraph.getNodes().forEach(entry -> entry.setShortestPath(new LinkedList<>()));
            dijkstra.calculateShortestPath(testGraph, node);
            dijkstra.printPaths(testGraph.getNodes().stream().toList());
        }
    }
}
