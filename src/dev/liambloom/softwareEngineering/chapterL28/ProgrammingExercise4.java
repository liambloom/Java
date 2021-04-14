package dev.liambloom.softwareEngineering.chapterL28;

/** Liang pg 1055 Chapter 28 #4
 (Find connected components) Create a new class named MyGraph as a subclass
 of UnweightedGraph that contains a method for finding all connected components
 in a graph with the following header:
 public List<List<Integer>> getConnectedComponents();
 The method returns a List<List<Integer>>. Each element in the list is
 another list that contains all the vertices in a connected component. For example,
 for the graph in Figure 28.21b, getConnectedComponents() returns
 [[0, 1, 2, 3], [4, 5]].
 */

import java.util.*;

public class ProgrammingExercise4
{
    public static void main()
    {
        // Data
        String data[] = {
                "6\n"		// First Graph
                        + "0 1 2\n"
                        + "1 0 3\n"
                        + "2 0 3 4\n"
                        + "3 1 2 4 5\n"
                        + "4 2 3 5\n"
                        + "5 3 4",
                "6\n"	            // Second Graph
                        + "0 1 2 3\n"
                        + "1 0 3\n"
                        + "2 0 3 \n"
                        + "3 0 1 2\n"
                        + "4 5\n"
                        + "5 4"};

        // Build the 'edges' list
        System.out.println(" Build the 'edges' list: ");
        for (int i=0; i<data.length; i++)
        {
            System.out.println(" \n ============================================ \n data["+i+"]: ");
            List<AbstractGraph.Edge> edges = new ArrayList<>();
            Scanner scan = new Scanner(data[i]);
            int numVerticesInList = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] splitLine = line.split(" ");
                int u = Integer.parseInt(splitLine[0]);
                for (int j = 1; j < splitLine.length; j++) {
                    int v = Integer.parseInt(splitLine[j]);
                    edges.add(new AbstractGraph.Edge(u, v));
                }
                System.out.println("edges: ");
                System.out.println(edges);
            }// while

            System.out.print("\n\n <<<<<<<<<<<<<<<<<<<<< data[" + i + "] >>>>>>>>>>>>>>>>>>>>>>>>>");
            Graph<Integer> graph = new UnweightedGraph<>(edges,numVerticesInList);
            System.out.println("\n graph.printEdges(): ");
            graph.printEdges();

            // Create a list of lists of connected Edges.
            ArrayList<List<Integer>> connectedComponents = new ArrayList<List<Integer>>();
            // 1. Create a set of Integer vertices
            Set<Integer> vertexSet = new HashSet<>(graph.getVertices());

            // 2. Go thru the entire set picking out vertices in the same tree
            int index=1;
            while (!vertexSet.isEmpty())
            {
                // 3. Pick an edge.
                Integer vertex = vertexSet.iterator().next();

                // 4. Create a tree via this startVertex
                AbstractGraph<Integer>.Tree tree = graph.dfs(vertex);

                // 5. Get list of Vertices.
                List<Integer> connectedVerteces = tree.getSearchOrder();

                // 6. Put verticesList into connectedComponents
                connectedComponents.add(connectedVerteces);

                // 7. Take verticesList out of edgeSet
                vertexSet.removeAll(connectedVerteces);

            } // while

        } // for
        System.out.println("\n\n ------------------- All Done!! -------------------");
    } // main

}  // Liang_Chapter28_4

