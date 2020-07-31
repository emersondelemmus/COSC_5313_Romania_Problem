/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
Authors: Adrien Fabian and Emerson de Lemmus

code identification by comment location:

comment above code: Emerson
comment on same line as code: Adrien

*/
package main;
import java.util.*;

public class Romania_Problem
{
    int cityArray[][];
    static int min = 1; //sets minimum possible city number to 1
    static int max = 19; //sets maximum possible city number to 19
    LinkedList<Integer> neighbours[];
    static int parentArray[];
    int steps = 0;
    static int numCity = 20;
    
    boolean Destination_Bucharest = false;

    public Romania_Problem(int numCity)
    {
        //this.numCity = numCity;
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

    public void BFS(int start, int numCity) //takes the starting city, rando in this case, as well as the total number of cities
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
                    
                    /*
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
                    */
                    
                    resetParentArray( parentArray );
                    return;
                }
            }
        }
    }
    
    // Accepts random starting city denoted as (rando)
    public void DFS( int rando )
    {
        // create boolean array to keep track of visited nodes
        boolean visited[] = new boolean [numCity];
        
        // Stack keeps track of cities visited
        // Route keeps track of the path taken from Source -> Destination
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> route = new Stack<Integer>();
        assignParent( rando, rando);
    
        System.out.print("To get to Bucharest from node " + rando + " you must pass through node(s): ");
        
        // For loop starts at Source city and runs through the boolean array holding
        // other cities until it reaches destination.
        for (int startIndex = rando; startIndex < numCity; startIndex++)
        {
            if (visited[startIndex] == false) 
            {
                // push City onto the stack and mark it as visited
                stack.push(startIndex);
                visited[startIndex] = true;
                
                // until stack is empty, keep running
                while (stack.isEmpty() == false)
                {
                    // increase the path cost counter and pop the stack to 
                    // node index
                    stepCounter();
                    int nodeIndex = stack.pop();
                    
                    //print out the node index (city)
                    System.out.print(nodeIndex + " ");
                    
                    //create nodeList and bring in neighbors for each City
                    LinkedList<Integer> nodeList = neighbours[nodeIndex];
                
                    // for the neighborlist of a city run until the end
                    for (int i = 0; i < nodeList.size(); i++) 
                    {
                        // grabs each neighbor of a city
                        int dest = nodeList.get(i);
                        
                        // if the next neighbor of a city hasn't been visited, push it on the stack
                        // push it to the route
                        if (visited[dest] == false) 
                        {
                            stack.push(dest);
                            route.push(dest);
                            visited[dest] = true;

                            // if destination == bucharest (0) Exit and print out the path cost
                            if (dest == 0 )
                            {
                                System.out.print(dest + " ");
                                System.out.println();
                                System.out.println("You have reached Bucharest after visiting " + (steps + 1) + " nodes/cities");
                                
                                System.out.println();
                                
                                //reset steps
                                stepReset();              
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    
/*    public void IDS ( int rando ) 
    {
        // Iterates depth from 0 -> max value held by an int data type (essentially infinity for our problem)
        for (int limit = 0; limit < Integer.MAX_VALUE; limit++)
        {
            boolean destination_Bucharest = DLS(rando, limit);
            if (destination_Bucharest == true)
            {
                return;
            }
        }
    }
    
    public boolean DLS (int rando, int limit) 
    {
        //int depth_track = 0;

        boolean visited[] = new boolean [numCity];
        Stack<Integer> stack = new Stack<Integer>();
     
        //stack.push(rando);
        int limiter = limit;
        int depth = 0;

        if(rando == 0)
        {
            return true;
        }
        
        if (visited[rando] == false) 
        {
            stack.push(rando);
            visited[rando] = true;
            
            while (!stack.isEmpty())
            {
                if(depth <= limiter)
                {
                    int nodeIndex = stack.pop();
                    System.out.println(nodeIndex + " at depth " + depth);  //comment out for all depths
                    //Grab neighbor list for nodeIndex
                    LinkedList<Integer> nodeList = neighbours[nodeIndex];
                    for (int i = 0; i < nodeList.size(); i++) 
                    {
                        int dest = nodeList.get(i);
                        if (visited[dest] == false) 
                        {
                            //stack.push(dest);
                            //visited[dest] = true;
                            if (dest == 0 )
                            {
                                //System.out.print(dest + " ");
                                //System.out.println();
                                System.out.println("You have reached Bucharest at depth: " + depth);    
                                //System.out.println("City " + rando + " is at depth: " + depth);                                   
                                Destination_Bucharest = true;
                                return Destination_Bucharest;
                            }
                            else
                            {
                                stack.push(dest);
                                visited[dest] = true;
                                //System.out.println(nodeIndex + " at depth " + depth); //comment out for limited depths
                                depth++;
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Not found at limit " + limit);
                    return false;
                }
            }
        }       
        return Destination_Bucharest;
    }
*/
    public void backtrackSteps(int[] parentArray, int start) //backtracks parentArray to print out the path taken
    {
        Stack path = new Stack(); //create a stack
        int i = 0; //sets the starting location
        int cityCount = 0;

        while(parentArray[i] != i) //pushes the path onto the stack
        {
            path.push(i);
            i = parentArray[i]; //changes the current parent/child pair to the previous one in the path
            cityCount++;
        }

        System.out.println("To get to Bucharest from node " + start + " you must pass through " + cityCount + " node(s): ");
        while(!path.isEmpty()) //continues until the stack has been emptied
        {
            Object j = path.pop(); //removes an item from the stack
            System.out.print(j + " "); //prints the item
        }
    }
    
    public void resetParentArray( int parentArray[]) //sets all values of parentArray to -1
    {
        for (int i = 0; i < parentArray.length; i++)
        {
            parentArray[i] = -1;
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
   
    public void printArray(int numCity) //prints the neighbour array, then prints out every node's neighbours
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
        ArrayList<Node> Rand_Loc = new ArrayList();
        
        //List of Cities represented as integers
        // 0 = Bucharest
        Node Bucharest = new Node ("Bucharest");
        
        // 1 = Arad
        Node Arad = new Node ("Arad");
        Rand_Loc.add(Arad);
        
        // 2 = Craiova
        Node Craiova = new Node ("Craiova");
        Rand_Loc.add(Craiova);
        
        // 3 = Drobeta
        Node Drobeta = new Node ("Drobeta");
        Rand_Loc.add(Drobeta);
        
        // 4 = Eforie
        Node Eforie = new Node ("Eforie");
        Rand_Loc.add(Eforie);
        
        // 5 = Fagaras
        Node Fagaras = new Node ("Fagaras");
        Rand_Loc.add(Fagaras);
        
        // 6 = Giurgiu
        Node Giurgiu = new Node ("Giurgiu");
        Rand_Loc.add(Giurgiu);
        
        // 7 = Hirsova
        Node Hirsova = new Node ("Hirsova");
        Rand_Loc.add(Hirsova);
        
        // 8 = Iasi
        Node Iasi = new Node ("Iasi");
        Rand_Loc.add(Iasi);
        
        // 9 = Lugoj
        Node Lugoj = new Node ("Lugoj");
        Rand_Loc.add(Lugoj);
        
        // 10 = Mehadia
        Node Mehadia = new Node ("Mehadia");
        Rand_Loc.add(Mehadia);
        
        // 11 = Neamt
        Node Neamt = new Node ("Neamt");
        Rand_Loc.add(Neamt);
        
        // 12 = Oradea
        Node Oradea = new Node ("Oradea");
        Rand_Loc.add(Oradea);
        
        // 13 = Pitesti
        Node Pitesti = new Node ("Pitesti");
        Rand_Loc.add(Pitesti);
        
        // 14 = Rimnicu Vilcea
        Node RimnicuVilcea = new Node ("RimnicuVilcea");
        Rand_Loc.add(RimnicuVilcea);
        
        // 15 = Sibiu
        Node Sibiu = new Node ("Sibiu");
        Rand_Loc.add(Sibiu);
        
        // 16 = Timisoara
        Node Timisoara = new Node ("Timisoara");
        Rand_Loc.add(Timisoara);
        
        // 17 = Urziceni
        Node Urziceni = new Node ("Urziceni");
        Rand_Loc.add(Urziceni);
        
        // 18 = Vaslui
        Node Vaslui = new Node ("Vaslui");
        Rand_Loc.add(Vaslui);
        
        // 19 = Zerind
        Node Zerind = new Node ("Zerind");
        Rand_Loc.add(Zerind);

        // gets passed to Graph(int vertices)

        // Create Graph Object
        Romania_Problem graph = new Romania_Problem(numCity);

        // NODES ARE ADDED IN ALPHABETICAL ORDER
        
        // Bucharest
        Bucharest.addNeighbour(Fagaras);
        Bucharest.addNeighbour(Giurgiu);
        Bucharest.addNeighbour(Pitesti);
        Bucharest.addNeighbour(Urziceni);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(0, 13);
        graph.addEdge(0, 17);

        // Arad
        Arad.addNeighbour(Sibiu);
        Arad.addNeighbour(Timisoara);
        Arad.addNeighbour(Zerind);
        graph.addEdge(1, 15);
        graph.addEdge(1, 16);
        graph.addEdge(1, 19);

        // Craiova
        Craiova.addNeighbour(Drobeta);
        Craiova.addNeighbour(Pitesti);
        Craiova.addNeighbour(RimnicuVilcea);
        graph.addEdge(2, 3);
        graph.addEdge(2, 13);
        graph.addEdge(2, 14);

        // Drobeta
        Drobeta.addNeighbour(Craiova);
        Drobeta.addNeighbour(Mehadia);
        graph.addEdge(3, 2);
        graph.addEdge(3, 10);

        // Eforie
        Eforie.addNeighbour(Hirsova);
        graph.addEdge(4, 7);

        // Fagaras
        Fagaras.addNeighbour(Bucharest);
        Fagaras.addNeighbour(Sibiu);
        graph.addEdge(5, 0);
        graph.addEdge(5, 15);

        // Giurgiu
        Giurgiu.addNeighbour(Bucharest);
        graph.addEdge(6, 0);

        // Hirsova
        Hirsova.addNeighbour(Eforie);
        Hirsova.addNeighbour(Urziceni);
        graph.addEdge(7, 4);
        graph.addEdge(7, 17);

        // Iasi
        Iasi.addNeighbour(Neamt);
        Iasi.addNeighbour(Vaslui);
        graph.addEdge(8, 11);
        graph.addEdge(8, 18);

        // Lugoj
        Lugoj.addNeighbour(Mehadia);
        Lugoj.addNeighbour(Timisoara);
        graph.addEdge(9, 10);
        graph.addEdge(9, 16);

        // Mehadia
        Mehadia.addNeighbour(Drobeta);
        Mehadia.addNeighbour(Lugoj);
        graph.addEdge(10, 3);
        graph.addEdge(10, 9);

        // Neamt
        Neamt.addNeighbour(Iasi);
        graph.addEdge(11, 8);

        // Oradea
        Oradea.addNeighbour(Sibiu);
        Oradea.addNeighbour(Zerind);
        graph.addEdge(12, 15);
        graph.addEdge(12, 19);

        // Pitesti
        Pitesti.addNeighbour(Bucharest);
        Pitesti.addNeighbour(Craiova);
        Pitesti.addNeighbour(RimnicuVilcea);
        graph.addEdge(13, 0);
        graph.addEdge(13, 2);
        graph.addEdge(13, 14);

        // Rimnicu Vilcea
        RimnicuVilcea.addNeighbour(Craiova);
        RimnicuVilcea.addNeighbour(Pitesti);
        RimnicuVilcea.addNeighbour(Sibiu);
        graph.addEdge(14, 2);
        graph.addEdge(14, 13);
        graph.addEdge(14, 15);

        // Sibiu
        Sibiu.addNeighbour(Arad);
        Sibiu.addNeighbour(Fagaras);
        Sibiu.addNeighbour(Oradea);
        Sibiu.addNeighbour(RimnicuVilcea);
        graph.addEdge(15, 1);
        graph.addEdge(15, 5);
        graph.addEdge(15, 12);
        graph.addEdge(15, 14);

        // Timisoara
        Timisoara.addNeighbour(Arad);
        Timisoara.addNeighbour(Lugoj);
        graph.addEdge(16, 1);
        graph.addEdge(16, 9);

        // Urziceni
        Urziceni.addNeighbour(Bucharest);
        Urziceni.addNeighbour(Hirsova);
        Urziceni.addNeighbour(Vaslui);
        graph.addEdge(17, 0);
        graph.addEdge(17, 7);
        graph.addEdge(17, 18);

        // Vaslui
        Vaslui.addNeighbour(Iasi);
        Vaslui.addNeighbour(Urziceni);
        graph.addEdge(18, 8);
        graph.addEdge(18, 17);

        // Zerind
        Zerind.addNeighbour(Arad);
        Zerind.addNeighbour(Oradea);
        graph.addEdge(19, 1);
        graph.addEdge(19, 12);

        // print graph
        graph.printArray(numCity);

        Random rand = new Random(); //random starting location
        int rando = rand.nextInt((max - min) + 1) + min;

        System.out.println("BFS starting at node " + rando + ": ");
        graph.BFS(rando, numCity); //does the BFS, sends our randomised starting location and the number of vertices (important for the visited array size)
        System.out.println();

        System.out.println();
        System.out.println("DFS starting at node " + rando + ": ");
        // calls Deep First search with rando being starting location
        graph.DFS(rando);
        System.out.println();

        System.out.print("IDS starting at node " + rando + ": ");
        IterativeDeepeningSearch IDS = new IterativeDeepeningSearch(Bucharest);
        IDS.runIDS(Rand_Loc.get(rando - 1));
    }
}