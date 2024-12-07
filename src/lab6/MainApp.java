package lab6;

import java.util.Scanner;

// main driver class for program
public class MainApp
{
    public static void main(String[] args)
    {
        //create input object
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Johnson's Algorithm Test program");
        System.out.println("--------------------------------");
        System.out.println();

        // create graph from user input
        Graph<String> testGraph = InputProcessing.returnUserGraph(keyboard);

        // catch negative weight cycle error and print message if one is detected
        try
        {
            // create new Johnson class object and pass graph through method
            Johnson<String> johnson = new Johnson<>();
            johnson.johnsonAlgo(testGraph); // adjust this if it ends up returning something *****
        } catch (RuntimeException e)
        {
            System.out.println("Critical Error: " + e.getMessage());
        }
    }
}