/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author user
 */
public class Graphs {

    static boolean[] visited;
    static int[] weight;
    static int[] parent;

    public static void intializeArrays(int numberOfNodes) {
        visited = new boolean[numberOfNodes];
        weight = new int[numberOfNodes];
        parent = new int[numberOfNodes];
    }

    public static int findMinWeight(boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false && weight[i] < min) {
                min = weight[i];
                index = i;
            }
        }
        return index;
    }

    public static void buildMST(int[][] graph, int numberOfNodes) {
        int minIndex;

        for (int i = 0; i < numberOfNodes; i++) {
            minIndex = findMinWeight(visited);

            visited[minIndex] = true;

            for (int j = 0; j < numberOfNodes; j++) {
                if (graph[minIndex][j] != 0 && visited[j] == false && (graph[minIndex][j] + weight[minIndex]) < weight[j]) {
                    
                        weight[j] = weight[minIndex] + graph[minIndex][j];
                        parent[j] = minIndex;
                    
                }
            }
        }
        
    }

    public static void Dijkstra(int[][] graph, int numberOfNodes, int src) {
        int minIndex;
        int startingPoint;
        intializeWeightDijstra(weight, numberOfNodes, src);
        for (int i = 0; i < numberOfNodes; i++) {
            minIndex = findMinWeight(visited);
            startingPoint = minIndex;

            visited[minIndex] = true;

            for (int j = 0; j < numberOfNodes; j++) {
                if (graph[minIndex][j] != 0 && (graph[minIndex][j] + weight[minIndex]) < weight[j]) {

                    weight[j] = weight[minIndex] + graph[minIndex][j];
                    parent[j] = minIndex;
                }
            }

        }

        if (isPossible(weight)) {
            printDijkstra(numberOfNodes, src);
        } else {
            System.out.println("sorry it is not possible !");
        }
    }

    public static boolean isPossible(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 9999999 || arr[i] == 0) {
            } else {
                flag = true;
            }
        }
        return flag;
    }

    public static void printGraph(int numberOfNodes) {
        System.out.print("Total weight :");
        int totalWeight = 0;

        
        for (int i = 0; i < numberOfNodes; i++) {
            totalWeight += weight[i] - weight[parent[i]];

        }
        System.out.print(totalWeight);
        System.out.println("\nEdges:");
        for (int i = 1; i < numberOfNodes; i++) {
            System.out.println(parent[i] + "," + i);
        }
    }

    public static void printDijkstra(int numberOfNodes, int src) {
        
        Stack<String> stck = new Stack<>();
        int i;
        for(int c=0;c<numberOfNodes;c++){
         i = c;
         if(c==src)
         {
             continue;
         }
         if(parent[c]==Integer.MAX_VALUE){
             continue;
         }
        System.out.println("Shortest path from " + src + " to " + i);
        while (parent[i] != -1) {

            stck.push(parent[i] + "," + i);
            i = parent[i];
        }
        while (!(stck.empty())) {

            System.out.print(stck.pop());
            if (!(stck.empty())) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\n");
        }
    }

    public static void printArray(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + "  ");
        }
    }

    public static void intializeWeight(int[] weight, int n) {
        weight[0] = 0;
        for (int i = 1; i < n; i++) {
            weight[i] = Integer.MAX_VALUE;
        }
    }

    public static void intializeWeightDijstra(int[] weight, int n, int src) {
        for (int i = 0; i < n; i++) {
            weight[i] = 9999999;
            parent[i]=Integer.MAX_VALUE;
        }
        weight[src] = 0;
        parent[src] = -1;

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int op;

        System.out.println("choose operation: \n 1- build MST \n 2- find Shortest Path");
        op = input.nextInt();
        if (op == 1 || op == 2) {
            System.out.println("Please enter number of nodes : ");
            int numberOfNodes = input.nextInt();

            intializeArrays(numberOfNodes);
            intializeWeight(weight, numberOfNodes);

            int[][] graph = new int[numberOfNodes][numberOfNodes];

            
            System.out.println("Please enter graph input : ");
            for (int i = 0; i < numberOfNodes; i++) {
                for (int j = 0; j < numberOfNodes; j++) {
                    graph[i][j] = input.nextInt();
                }
            }

            if (op == 1) {

                System.out.println("");
                buildMST(graph, numberOfNodes);
                printGraph(numberOfNodes);
            }
            if (op == 2) {

                System.out.println("Please enter source : ");
                int src = input.nextInt();
                
                System.out.println("");
                Dijkstra(graph, numberOfNodes, src);
            }
            
        }else
        {
            System.out.println("Wrong choice !");
        }

    }
}
