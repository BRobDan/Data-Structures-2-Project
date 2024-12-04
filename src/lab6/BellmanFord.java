package lab6;

import java.util.HashMap;
import java.util.Map;

public class BellmanFord<T>
{
    public HashMap<Node<T>, Integer> bellmanFord(Graph<T> testGraph)
    {
        // add new node to graph, and set distance to 0
        Node<T> bfNode = new Node<>(null);
        bfNode.setDistance(0);
        testGraph.addVertex(bfNode);

        // update number of vertices
        testGraph.setNumVertices(testGraph.getNumVertices() + 1);

        // connect the new node to all other nodes with a weight of 0
        for (Node<T> node : testGraph.getNodes()) {
            if (!node.equals(bfNode)) {
                testGraph.addEdge(bfNode, node, 0);
            }
        }

        // update the distance value at each node using the formula d[u] + w[u,v] < d[v], V - 1 times
        for (int i = 0; i < testGraph.getNumVertices(); i++)
        {
            for (Node<T> node : testGraph.getNodes())
            {
                node.getAdjacentNodes().entrySet().stream()
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
        boolean negativeWeight = false;

        for (Node<T> node : testGraph.getNodes())
        {
            for (Map.Entry<Node<T>, Integer> entry: testGraph.getAdjNodes(node).entrySet())
            {
                int dU = node.getDistance();
                int wUV = entry.getValue();
                int dV = entry.getKey().getDistance();

                if (dV > wUV + dU)
                {
                    negativeWeight = true;
                    break;
                }
            }
            if (negativeWeight)
                break;
        }

        if (negativeWeight)
            throw new IllegalStateException("Graph has a negative weight cycle!");

        // set the graph back to the original data
        // sets numVertices back to normal & removes the extra node
        testGraph.setNumVertices(testGraph.getNumVertices() - 1);
        testGraph.removeVertex(bfNode);

        // add distances to a new hashmap to return
        HashMap<Node<T>, Integer> newDistanceMap = new HashMap<>();
        testGraph.getNodes().stream().forEach(n -> newDistanceMap.put(n, n.getDistance()));

        return newDistanceMap;
    }
}
