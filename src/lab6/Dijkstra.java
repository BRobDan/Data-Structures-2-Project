/*
 * MIT License
 *
 * Copyright (c) 2024 Geekific (https://www.youtube.com/c/Geekific)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice, Geekific's channel link and this permission notice
 * shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

// i modified this class to take a graph as input
// i also modified the printPaths() method

package lab6;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra<T>
{

    public void calculateShortestPath(Graph<T> graph, Node<T> source)
    {

        source.setDistance(0);
        Set<Node<T>> settledNodes = new HashSet<>(); // creates a set of settled nodes
        Queue<Node<T>> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledNodes.isEmpty())
        {
            Node<T> currentNode = unsettledNodes.poll();

            graph.getAdjNodes(currentNode)
                    .entrySet().stream()
                    .filter(entry -> !settledNodes.contains(entry.getKey()))
                    .forEach(entry ->
                    {
                        evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
                        unsettledNodes.add(entry.getKey());
                    });
            settledNodes.add(currentNode);
        }
    }

    private void evaluateDistanceAndPath(Node<T> adjacentNode, Integer edgeWeight, Node<T> sourceNode)
    {
        Integer newDistance = sourceNode.getDistance() + edgeWeight;
        if (newDistance < adjacentNode.getDistance())
        {
            adjacentNode.setDistance(newDistance);
            adjacentNode.setShortestPath(Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList());
        }
    }

    // I modified the below method to print out the shortest paths and actual distances.
    // It uses the edge weights for each adjacent node from the Node class that was unchanged during the
    // BellmanFord algorithm
    public void printPaths(List<Node<T>> nodes)
    {
        for (Node<T> node : nodes)
        {
            // create an array list of the shortest path and add the current node to the beginning
            List<Node<T>> shortestPath = new ArrayList<>(node.getShortestPath());
            shortestPath.add(node);

            // calculate the total distance along the shortest patch and save to variable
            int unweightedDist = 0;
            for (int i = 0; i < shortestPath.size() - 1; i++)
            {
                Node<T> currentNode = shortestPath.get(i);
                Node<T> nextNode = shortestPath.get(i + 1);
                unweightedDist += currentNode.getAdjacentNodes().get(nextNode);
            }

            // creates a String of the shortest path connected with "->"
            String path = shortestPath.stream()
                    .map(Node::getName)
                    .map(Objects::toString)
                    .collect(Collectors.joining(" -> "));

            // checks to see if the distance to the node is still infinite
            String trueDist;
            if (node.getDistance() == Integer.MAX_VALUE)
            {
                trueDist = "infinite";
            } else
            {
                trueDist = String.valueOf(unweightedDist);
            }
            // prints the shortest path string and the true distance string
            System.out.println("%s : %s".formatted(path, trueDist));
        }
    }
}
