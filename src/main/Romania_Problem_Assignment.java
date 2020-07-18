

// Emerson de Lemmus, 
package main;

import java.util.*;

public class Romania_Problem_Assignment
{
    // Edge Class
    static class Edge
    {
        // Edge attributes -> City Start Loc
        //                 -> City Destination
        //                 -> Weight of route (distance)
        int source;
        int destination;
        int weight;
        
        // Edge Java Constructor
        public Edge(int source, int destination, int weight)
        {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    
    // Graph Class
    static class Graph 
    {
        // holds the number of vertices (city nodes)
        int vertices; 
        // Linked list contains the Adjacency list of EACH city
        LinkedList<Edge> adjacencyLists[];
        
        // Graph Constructor
        Graph(int numOfCities)
        {
            this.vertices = numOfCities;
            
            //Creates a linked list object adjacencyList
            adjacencyLists = new LinkedList[vertices];
            
            //initialize adjacencyLists for all the vertices
            for (int i = 0; i < vertices; i++) 
            {
                // creates new list object for each city (20)
                adjacencyLists[i] = new LinkedList<>();
                
            }
        }
    
        public void addEdge(int source, int destination, int weight) {
            // creates object city_edge each time method is called
            Edge city_edge = new Edge(source, destination, weight);
            
            // makes adds city_edge(source, dest, weight) to each adjacency list [0-19]
            adjacencyLists[source].add(city_edge);
            
        }
        
        
        public void printGraph(){
            
            for (int i = 0; i < vertices ; i++) {
                
                // list is a linked list of type Edge(source,dest,weight)
                LinkedList<Edge> list = adjacencyLists[i];
                
                for (int j = 0; j < list.size(); j++) 
                {
                    System.out.println("City:  " + i + " connects to City: " +
                            list.get(j).destination + " with weight " +  list.get(j).weight);
                }
            }
        }
    }
    
    public static void main(String[] args) 
    {
        //List of Cities represented as integers
        // 0 = Arad 
        // 1 = Bucharest 
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
        int numOfCities = 20;
        
        // Create Graph Object
        Graph graph = new Graph(numOfCities);
           
            
        // Bucharest
        graph.addEdge(0, 5, 211);
        graph.addEdge(0, 6, 90);
        graph.addEdge(0, 13, 101);
        graph.addEdge(0, 17, 85);
        
        // Arad
        graph.addEdge(1, 15, 140);
        graph.addEdge(1, 16, 118);
        graph.addEdge(1, 19, 75);
            
        // Craiova
        graph.addEdge(2, 3, 120);
        graph.addEdge(2, 13, 138);
        graph.addEdge(2, 14, 146);
        
        // Drobeta
        graph.addEdge(3, 2, 120);
        graph.addEdge(3, 10, 75);
        
        // Eforie
        graph.addEdge(4, 7, 86);
        
        // Fagaras
        graph.addEdge(5, 1, 211);
        graph.addEdge(5, 15, 99); 
        
        // Giurgiu
        graph.addEdge(6, 1, 90);
        
        // Hirsova
        graph.addEdge(7, 4, 86);
        graph.addEdge(7, 17, 98);
        
        // Iasi
        graph.addEdge(8, 11, 87);
        graph.addEdge(8, 18, 92);
        
        // Lugoj
        graph.addEdge(9, 10, 70);
        graph.addEdge(9, 16, 111);
        
        // Mehadia
        graph.addEdge(10, 3, 75);
        graph.addEdge(10, 9, 70);
        
        // Neamt
        graph.addEdge(11, 8, 87);
      
        // Oradea
        graph.addEdge(12, 15, 151);
        graph.addEdge(12, 19, 71);
        
        // Pitesti
        graph.addEdge(13, 1, 211);
        graph.addEdge(13, 14, 97);
        graph.addEdge(13, 2, 138);
        
        // Rimnicu Vilcea
        graph.addEdge(14, 2, 146);
        graph.addEdge(14, 13, 97);
        graph.addEdge(14, 15, 80);
        
        // Sibiu
        graph.addEdge(15, 0, 140);
        graph.addEdge(15, 5, 99);
        graph.addEdge(15, 12, 151);
        graph.addEdge(15, 14, 80);
        
        // Timisoara
        graph.addEdge(16, 0, 118);
        graph.addEdge(16, 9, 111);
        
        // Urziceni
        graph.addEdge(17, 1, 85);
        graph.addEdge(17, 7, 98);
        graph.addEdge(17, 18, 142);
        
        // Vaslui
        graph.addEdge(18, 8, 92);
        graph.addEdge(18, 17, 142);
        
        // Zerind
        graph.addEdge(19, 0, 75);
        graph.addEdge(19, 12, 71);
        
        // print graph
        graph.printGraph();
        
        // User choice
        System.out.println("Destination wil always be Bucharest (City 1)");
        System.out.println("Please enter your starting city (0-19): ");
        Scanner sc = new Scanner(System.in);
        int starting_city = sc.nextInt();
        

        }

    
}
    
    
    
    
    
    
    
