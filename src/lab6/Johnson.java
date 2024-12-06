package lab6;

import java.util.LinkedList;


// i think i'm going to have this class simply for the johnson method
// that takes a graph reference as a parameter and just prints out the solution
public class Johnson<T>
{


    public void johnsonAlgo(Graph<T> testGraph)
    {
        // create Bellman ford object and pass the graph through its method
        BellmanFord<T> bellman = new BellmanFord<>();
        bellman.bellmanFord(testGraph);
        // the graph has now been re-weighted to have no negative edge weights



        // formula to run djikstr'a algorithm and print out all the shortest paths and distances
        for (Node<T> node : testGraph.getNodes())
        {
            System.out.println(node.getName() + "TEST");
            for (Node<T> node1 : testGraph.getNodes()) // added this to reset all distances to 'infinite'
            {
                node1.setDistance(Integer.MAX_VALUE);
            }
            // create Djikstra object to run the algorithm
            Dijkstra<T> dijkstra = new Dijkstra<>();
            testGraph.getNodes().forEach(entry -> entry.setShortestPath(new LinkedList<>())); // got stuck on this forever
            dijkstra.calculateShortestPath(node);
            dijkstra.printPaths(testGraph.getNodes().stream().toList());
        }
    }
}
