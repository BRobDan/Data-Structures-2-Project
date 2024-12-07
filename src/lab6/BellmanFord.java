package lab6;

import java.util.Map;

// this class runs the bellman-ford algorithm for the johnson algorithm
public class BellmanFord<T>
{
    public void bellmanFord(Graph<T> testGraph)
    {
        // add a new node to graph, and set distance to 0
        Node<T> bfNode = new Node<>(null);
        testGraph.addVertex(bfNode);
        bfNode.setDistance(0);

        // update number of vertices
        testGraph.setNumVertices(testGraph.getNumVertices() + 1);

        // connect the new node to all other nodes with a weight of 0
        for (Node<T> node : testGraph.getNodes()) {
            if (!node.equals(bfNode)) {
                testGraph.addEdge(bfNode, node, 0);
            }
        }

        // update the distance value at each node using the formula d[u] + w[u,v] < d[v], V - 1 times
        for (int i = 0; i < testGraph.getNumVertices() - 1; i++)
        {
            for (Node<T> node : testGraph.getNodes())
            {
                testGraph.getAdjNodes(node).entrySet().stream()
                        .forEach(entry -> {
                            int dU = node.getDistance();
                            int wUV = entry.getValue();
                            int dV = entry.getKey().getDistance();

                            if (dU + wUV < dV)
                                entry.getKey().setDistance(dU + wUV);
                        });
            }
        }

        // check for negative weight cycles
        for (Node<T> node : testGraph.getNodes())
        {
            for (Map.Entry<Node<T>, Integer> entry : testGraph.getAdjNodes(node).entrySet())
            {
                int dU = node.getDistance();
                int wUV = entry.getValue();
                int dV = entry.getKey().getDistance();

                if (dV > wUV + dU)
                {
                    throw new IllegalStateException("Graph has a negative weight cycle!");
                }
            }
        }

        // set the graph back to the original data
        // sets numVertices back to normal & removes the extra node
        testGraph.setNumVertices(testGraph.getNumVertices() - 1);
        testGraph.removeVertex(bfNode);

        // re-weight the edges using the new distances
        for (Node<T> node : testGraph.getNodes())
        {
            for (Map.Entry<Node<T>, Integer> entry : testGraph.getAdjNodes(node).entrySet())
            {
                int dU = node.getDistance();
                int wUV = entry.getValue();
                int dV = entry.getKey().getDistance();

                entry.setValue(dU + wUV - dV); // uses formula -> w(u,v)' = d[u] + w(u,v) - d[v]
            }
        }
    }
}
