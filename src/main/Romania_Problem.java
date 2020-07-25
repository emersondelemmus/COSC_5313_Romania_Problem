/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;



/*
Authors: Adrien Fabian and Emerson de Lemmus

code identification by comment location:

comment above code: Emerson
comment on same line as code: Adrien

*/

import java.util.*;

public class Romania_Problem
{
    
    int cityArray[][];
    static int min = 1; //sets minimum possible city number to 1
    static int max = 19; //sets maximum possible city number to 19
    LinkedList<Integer> neighbours[];
    static int parentArray[];
    int steps = 0;
    int numCity;
    
    
    
    boolean Destination_Bucharest = false;

    
    
    
    public Romania_Problem(int numCity)
    {
        this.numCity = numCity;
        cityArray = new int[numCity][numCity]; //creates a 2D array of size numCity (20)
        neighbours = new LinkedList[numCity]; //creates a linked list with 20 nodes
        parentArray = new int[numCity]; //creates a 2D array to store each node and its parent node
        
        for(int i = 0; i < numCity; ++i) //creates 20 linked lists to contain each city's neighbours
        {
            neighbours[i] = new LinkedList(); //creates 20 linked lists to contain each city's neighbours
            parentArray[i] = -1; //fills the array with -1 values (0 would be used, but needs to be used elsewhere)
        }
    }
    
    public void addEdge(int from, int to)
    {
        cityArray[from][to] = 1; //for the matrix, adds a 1 indicating destination from a source
        cityArray[to][from] = 1; //for the matrix, adds a 1 on its opposite

        neighbours[from].add(to); //adds a node's neighbours, important for BFS traversal, can probably be used for DFS too
    }

    public void BFS(int start, int numCity) //takes the starting city, rando in this case
    {
        boolean visited[] = new boolean[numCity]; //creates a boolean array to determine if the city was visited before

        LinkedList<Integer> queue = new LinkedList<Integer>(); //creates a linked list for the queue
        visited[start] = true; //obviously we visit the starting location, its where we're at
        queue.add(start); //need to get at the starting location somehow
        assignParent(start, start);
        int next = start; //this way we stop using start the entire time, which led to some confusion for me

        while(queue.size() != 0) //loop start
        {
            next = queue.poll(); //gets the next city
            System.out.print(next + " "); //prints it
            stepCounter();

            Iterator<Integer> i = neighbours[next].listIterator(); //iterates over the linked list
            while(i.hasNext()) //does things as long as there are still unvisited neighbours
            {
                int current = i.next(); //takes the next item in neighbours and makes it usable
                if(!visited[current]) //checks to see if a node/city was visited
                {
                    visited[current] = true; //sets the visited boolean to true
                    queue.add(current); //considers the node/city as visited

                    if(current != -1) //checks to see if the current node already has a parent, ensures nodes with a parent don't have the existing parent overwritten
                    {
                        assignParent(next, current); //calls the method to assign a parent-child relationship
                    }
                }

                if(next == 0) //check to see if we are in Bucharest
                {
                    System.out.println();
                    System.out.println("You have reached Bucharest after visiting " + steps + " nodes/cities"); //prints the number of nodes/cities visited to get to Bucharest
                    System.out.println("The item(s) in the queue is/are: " + queue); //prints the queue, to show what the next nodes/cities were next

                    backtrackSteps(parentArray, start); //traces and prints the path
                    System.out.println();
                    stepReset();
                    
                    
                    System.out.print("Child Node\t");
                    for(int k = 0; k < parentArray.length; k++) //prints each node
                    {
                        System.out.print(k + "\t");
                    }
                    
                    System.out.print("\nParent Node\t");
                    
                    for(int j = 0; j < parentArray.length; j++) //prints each node's parent
                    {
                        System.out.print(parentArray[j] + "\t");
                    }

                    return;
                }
            }
        }
    }
    
    public void DFS( int rando )
    {
        boolean visited[] = new boolean [numCity];
        Stack<Integer> stack = new Stack<Integer>();
        
    
        for (int startIndex = rando; startIndex < numCity; startIndex++)
        {
            if (visited[startIndex] == false) 
            {
                stack.push(startIndex);
                visited[startIndex] = true;
                
                while (stack.isEmpty() == false)
                {
                    int nodeIndex = stack.pop();
                    System.out.print(nodeIndex + " ");
                    LinkedList<Integer> nodeList = neighbours[nodeIndex];
                
                    for (int i = 0; i < nodeList.size(); i++) 
                    {
                        int dest = nodeList.get(i);
                        if (visited[dest] == false) 
                        {
                            stack.push(dest);
                            visited[dest] = true;
                            if (dest == 0 )
                            {
                                System.out.print(dest + " ");
                                System.out.println();
                                System.out.println("You have reached Bucharest");                                       
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    
    
    
    //after much trial and error this is nearly identical to DFS bc it RUNS at least
    //TEST CITY = 8 (Lugoj)
    
    
    
    
    
    
    
    
    
    public void IDS ( int rando ) 
    {
        // Iterates depth from 0 -> max value held by an int data type (essentially infinity for our problem)
        for (int depth = 0; depth < Integer.MAX_VALUE; depth ++)
        {
            boolean destination_Bucharest = DLS(rando, depth);
            if (destination_Bucharest == true)
            {
                return;
            }
            
        }
        
    }
    
    public boolean DLS ( int rando, int depth ) 
    {
        //int depth_track = 0;

        boolean visited[] = new boolean [numCity];
        Stack<Integer> stack = new Stack<Integer>();
        
        System.out.println("City " + rando + " is at depth: " + depth);
     
        //stack.push(rando);
        
        if (visited[rando] == false) 
        {
            stack.push(rando);
            visited[rando] = true;
                while ( !stack.isEmpty() )
                {
                    int nodeIndex = stack.pop();
                    System.out.print(nodeIndex + " ");
                    //Grab neighbor list for nodeIndex
                    LinkedList<Integer> nodeList = neighbours[nodeIndex];
                    for (int i = 0; i < nodeList.size(); i++) 
                    {
                        int dest = nodeList.get(i);
                        if (visited[dest] == false) 
                        {
                            stack.push(dest);
                            visited[dest] = true;
                            if (dest == 0 )
                            {
                                System.out.print(dest + " ");
                                System.out.println();
                                System.out.println("You have reached Bucharest");                                       
                                Destination_Bucharest = true;
                            }
                        }
                    }
                }
            }        
        return Destination_Bucharest;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void backtrackSteps(int[] parentArray, int start) //backtracks parentArray to print out the path taken
    {
        Stack path = new Stack(); //create a stack
        int i = 0; //sets the starting location
        while(parentArray[i] != i) //pushes the path onto the stack
        {
            path.push(i);
            i = parentArray[i]; //changes the current parent/child pair to the previous one in the path
        }
        System.out.println();
        System.out.print("To get to Bucharest from node " + start + " you must pass through node(s): ");
        while(!path.isEmpty()) //continues until the stack has been emptied
        {
            Object j = path.pop(); //removes an item from the stack
            System.out.print(j + " "); //prints the item
        }
    }

    public void assignParent(int parent, int child) //assigns each node a parent
    {
        parentArray[child] = parent; //the parent value is assigned to the parentArray's index value. The index value is the child node
    }

    public void stepCounter() //increments the step count
    {
        steps++;
    }

    public void stepReset() //resets the step count to 0
    {
        steps = 0;
    }
    
    


    public void printArray(int numCity)
    {
        System.out.println("Adjacency Matrix:");

        for(int i = 0; i < numCity; i++) //prints the 2D array
        {
            for(int j = 0; j < numCity; j++)
            {
                System.out.print(cityArray[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for(int i = 0; i < numCity; i++) //prints each node's neighbours
        {
            System.out.print("Node " + i + " is linked to:\t");
            for(int j = 0; j < numCity; j++)
            {
                if(cityArray[i][j] == 1)
                {
                    System.out.print( j + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        //List of Cities represented as integers
        // 0 = Bucharest
        // 1 = Arad
        // 2 = Craiova
        // 3 = Drobeta
        // 4 = Eforie
        // 5 = Fagaras
        // 6 = Giurgiu
        // 7 = Hirsova
        // 8 = Iasi
        // 9 = Lugoj
        // 10 = Mehadia
        // 11 = Neamt
        // 12 = Oradea
        // 13 = Pitesti
        // 14 = Rimnicu Vilcea
        // 15 = Sibiu
        // 16 = Timisoara
        // 17 = Urziceni
        // 18 = Vaslui
        // 19 = Zerind

        // This represents number of cities (vertices) we have.
        // gets passed to Graph(int vertices)
        int numCity = 20;

        // Create Graph Object
        Romania_Problem graph = new Romania_Problem(numCity);

        //switched Bucharest and Arad around, makes it easier for the start randomiser to do its thing -Adrien
        // Bucharest
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(0, 13);
        graph.addEdge(0, 17);

        // Arad
        graph.addEdge(1, 15);
        graph.addEdge(1, 16);
        graph.addEdge(1, 19);

        // Craiova
        graph.addEdge(2, 3);
        graph.addEdge(2, 13);
        graph.addEdge(2, 14);

        // Drobeta
        graph.addEdge(3, 2);
        graph.addEdge(3, 10);

        // Eforie
        graph.addEdge(4, 7);

        // Fagaras
        graph.addEdge(5, 0);
        graph.addEdge(5, 15);

        // Giurgiu
        graph.addEdge(6, 0);

        // Hirsova
        graph.addEdge(7, 4);
        graph.addEdge(7, 17);

        // Iasi
        graph.addEdge(8, 11);
        graph.addEdge(8, 18);

        // Lugoj
        graph.addEdge(9, 10);
        graph.addEdge(9, 16);

        // Mehadia
        graph.addEdge(10, 3);
        graph.addEdge(10, 9);

        // Neamt
        graph.addEdge(11, 8);

        // Oradea
        graph.addEdge(12, 15);
        graph.addEdge(12, 19);

        // Pitesti
        graph.addEdge(13, 0);
        graph.addEdge(13, 2);
        graph.addEdge(13, 14);

        // Rimnicu Vilcea
        graph.addEdge(14, 2);
        graph.addEdge(14, 13);
        graph.addEdge(14, 15);

        // Sibiu
        graph.addEdge(15, 1);
        graph.addEdge(15, 5);
        graph.addEdge(15, 12);
        graph.addEdge(15, 14);

        // Timisoara
        graph.addEdge(16, 1);
        graph.addEdge(16, 9);

        // Urziceni
        graph.addEdge(17, 0);
        graph.addEdge(17, 7);
        graph.addEdge(17, 18);

        // Vaslui
        graph.addEdge(18, 8);
        graph.addEdge(18, 17);

        // Zerind
        graph.addEdge(19, 1);
        graph.addEdge(19, 12);

        // print graph
        graph.printArray(numCity);

        Random rand = new Random(); //random starting location
        int rando = rand.nextInt((max - min) + 1) + min;

        System.out.println("BFS starting at node " + 9 + ": ");
        graph.BFS(9, numCity); //does the BFS, sends our randomised starting location and the number of vertices (important for the visited array size)
        System.out.println();
        
        
        
        
        System.out.println();
        System.out.println("DFS starting at node " + 9 + ": ");
        // calls Deep First search with rando being starting location
        graph.DFS(9);
        System.out.println();
        
        
        
        
        
        
        
        System.out.println();
        System.out.println("IDS starting at node " + 9 + ": ");
        graph.IDS(9);
        
    }
}