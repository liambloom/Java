package dev.liambloom.softwareEngineering.chapterL28;

/**
 (Test whether a graph is connected) Write a program that reads a graph from
 an array, ‘data’, and determines whether the graph is connected. The first line
 contains a number that indicates the number of vertices (n). The vertices are
 labeled as 0, 1, . . . , n-1. Each subsequent line, with the format u v1 v2 ...,
 describes edges (u, v1), (u, v2), and so on. Figure 28.21 gives the examples of
 two files for their corresponding graphs.
 */
import java.util.*;

public class ProgrammingExercise1 {
    public static void main(String[] args) {
        // STEP 1: use the data below to fill edgeList.
        String data[ ] = {
                "6\n"      // First Data Set -  Example 28.21 (a)
                        + "0 1 2\n"
                        + "1 0 3\n"
                        + "2 0 3 4\n"
                        + "3 1 2 4 5\n"
                        + "4 2 3 5\n"
                        + "5 3 4",
                "6\n"      // Second Data Set -  Example 28.21 (b)
                        + "0 1 2 3\n"
                        + "1 0 3\n"
                        + "2 0 3 \n"
                        + "3 0 1 2\n"
                        + "4 5\n"
                        + "5 4"};

        for (int i=0; i<data.length; i++) {
            List<AbstractGraph.Edge> edgeList = new ArrayList<>();
            Scanner scan = new Scanner(data[i]);
            int numVerticesInList = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] splitLine = line.split(" ");
                int u = Integer.parseInt(splitLine[0]);
                for (int j = 1; j < splitLine.length; j++) {
                    int v = Integer.parseInt(splitLine[j]);
                    edgeList.add(new AbstractGraph.Edge(u, v));
                }
            }// while


            // STEP 2: Create the graph, tree, etc
            UnweightedGraph<Integer> graph = new UnweightedGraph<>(edgeList, numVerticesInList);
            graph.printEdges();
            AbstractGraph<Integer>.Tree dfsTree = graph.dfs(0);

            // STEP 3: Print Results
            System.out.println("The graph is " + (dfsTree.getNumberOfVerticesFound() == numVerticesInList ? "" : "not ") + "connected");

            System.out.println(System.lineSeparator().repeat(4));
        }// for

    } // main

}  // Liang_Chapter28_1
