package lab6;

// i have never used lombok in my programs before, so I was testing it out with my Graph class
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


// This graph class uses an adjacency list. It creates an undirected, weighted graph

@Getter
@Setter
@RequiredArgsConstructor
public class Graph<T>
{
    // creates an empty HashMap that holds the adjacency list
    private Map<Node<T>, Map<Node<T>, Integer>> adjList = new HashMap<>();
    // variables for the number of vertices and edges for use in MainApp
    private int numVertices;
    private int numEdges;

    // add a vertex to the graph
    public void addVertex(Node<T> node)
    {
        adjList.put(node, new HashMap<>());
    }

    // remove a vertex from the graph (this is just for the extra Bellman-Ford vertex)
    public void removeVertex(Node<T> node)
    {
        adjList.remove(node);
    }

    // add an edge to the graph
    public void addEdge(Node<T> start, Node<T> finish, Integer weight)
    {
        //adds the new edge to the HashMap that is connected to the start node with it's weight
        adjList.get(start).put(finish, weight);
        start.addAdjacentNode(finish, weight); // also add it to the adjacent node list
    }

    // return the adjacent nodes for a node
    public Map<Node<T>, Integer> getAdjNodes(Node<T> node)
    {
        return adjList.get(node);
    }

    // return all nodes (keys) in the graph
    public Set<Node<T>> getNodes()
    {
        return adjList.keySet();
    }
}
