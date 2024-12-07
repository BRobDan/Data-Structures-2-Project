package lab6;

import java.util.Scanner;

// this class processes user input and returns a graph for use in the program
public class InputProcessing
{
    public static Graph<String> returnUserGraph(Scanner keyboard)
    {
        // create a new graph
        Graph<String> testGraph = new Graph<>();

        System.out.print("Enter the number of vertices and edges in the format <V E> without <>:");

        // get user input for number of vertices and edges
        int numVertices = keyboard.nextInt();
        int numEdges = keyboard.nextInt();

        // sets the two related variables in the graph
        testGraph.setNumVertices(numVertices);
        testGraph.setNumEdges(numEdges);

        // create the vertices
        for (int i = 1; i <= testGraph.getNumVertices(); i++)
        {
            System.out.print("Enter name for Vertex #" + i + ": ");
            Node<String> node = new Node<>(keyboard.next());
            testGraph.addVertex(node);
        }

        // loop that takes the user input for each edge and puts it in the graph
        for (int i = 1; i <= testGraph.getNumEdges(); i++)
        {
            System.out.print("Enter the data for edge #" + i +
                    " in the format of Start End Weight <S E W>: ");

            // save user input
            String start = keyboard.next();
            String end = keyboard.next();
            int weight = keyboard.nextInt();

            // filter through the graph and find the start and end nodes
            Node<String> startNode = testGraph.getNodes().stream()
                    .filter(node -> node.getName().equals(start))
                    .findFirst()
                    .orElse(null);

            Node<String> endNode = testGraph.getNodes().stream()
                    .filter(node -> node.getName().equals(end))
                    .findFirst()
                    .orElse(null);

            // add the edge (graph is directed b/c johnson's won't work on undirected graph with
            // negative edge weights)
            testGraph.addEdge(startNode, endNode, weight);
        }

        return testGraph;
    }
}
