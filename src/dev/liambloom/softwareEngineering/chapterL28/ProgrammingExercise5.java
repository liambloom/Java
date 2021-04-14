package dev.liambloom.softwareEngineering.chapterL28;


/**
 (Find paths) Add a new method in AbstractGraph to find a path between two
 vertices with the following header:
 public List<Integer> getPath(int u, int v);
 The method returns a List<Integer> that contains all the vertices in a path
 from u to v in this order. Using the BFS approach, you can obtain a shortest
 path from u to v. If there isn’t a path from u to v, the method returns null.
 */

import java.util.*;

public class ProgrammingExercise5
{
    // STEP 1: use the data below to create + fill edgeList.
    public static void main(String[] args)
    {
        String data[] = {
                "12\n"       // pg 1016 Flight City Graph
                        + "0 1 3 5\n"
                        + "1 0 2 3\n"
                        + "2 1 3 4 10\n"
                        + "3 0 1 2 4 5\n"
                        + "4 2 3 5 7 8 10\n"
                        + "5 0 3 4 6 7\n"
                        + "6 5 7\n"
                        + "7 4 5 6 8\n"
                        + "8 4 7 10 11\n"
                        + "9 8 11\n"
                        + "10 2 4 8 11\n"
                        + "11 8 9 10",
                "6\n"	    // pg 1054 28.1 (a)
                        + "0 1 2\n"
                        + "1 0 3\n"
                        + "2 0 3 4\n"
                        + "3 1 2 4 5\n"
                        + "4 2 3 5\n"
                        + "5 3 4",
                "6\n"       // pg 1054 28.1 (b)
                        + "0 1 2 3\n"
                        + "1 0 3\n"
                        + "2 0 3 \n"
                        + "3 0 1 2\n"
                        + "4 5\n"
                        + "5 4"};

        // We will get the paths (0,5) in Graph 1, and (0,4) in Graph 2.
        int uArray[] = {8,0,0};
        int vArray[] = {0,5,4};
        for (int i=0; i<data.length; i++)
        {
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
            Graph<Integer> graph = new UnweightedGraph<>(edgeList, numVerticesInList);
            AbstractGraph<Integer>.Tree bfsTree = graph.bfs(vArray[i]);

            // Step 3: Get the path from u to v, null if none exists.
            List<Integer> path = bfsTree.getPath(uArray[i]);

            // Step 4: Set 'path' properly
            if (path.get(path.size() - 1) != vArray[i])
                path = null;

            // Step 4: Print Results
            System.out.println(path);

        }// for

    } // main

}  // Liang_Chapter28_5



