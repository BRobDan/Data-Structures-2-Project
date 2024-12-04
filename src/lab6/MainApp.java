package lab6;

import java.util.Scanner;




public class MainApp {
    public static void main(String[] args) {
        //create input object
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Johnson's Algorithm Test program");

        Graph<String> testGraph = InputProcessing.returnUserGraph(keyboard);


        System.out.println("\nGraph Structure");

        testGraph.getNodes().stream()
                .forEach(node -> {
                    System.out.println("Node " + node.getName() + " -> ");

                    testGraph.getAdjNodes(node).entrySet().stream()
                            .map(entry -> "(" + entry.getKey().getName() + ", weight=" + entry.getValue() + ")")
                            .forEach(adjNodeInfo -> System.out.print(adjNodeInfo + " "));

                    System.out.println();
                });



        }
//        System.out.println(testGraph.getNumVertices());
//        System.out.println(testGraph.getNumEdges());

    }