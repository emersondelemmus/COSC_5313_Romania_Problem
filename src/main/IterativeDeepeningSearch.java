/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Stack;
import java.util.*;

/**
 *
 * @author Adrien Fabian, Emerson de Lemmus
 * 
 *  Created a Node object version of IDS 
 */
public class IterativeDeepeningSearch {
    
    // Target node is destination city
    public Node destination_City;
    // default is target found to false 
    public boolean isTargetFound = false;
    
    public IterativeDeepeningSearch(Node node) 
    {
        this.destination_City = node;
    }
    
    // Recursively call Iterative Deepening Search
    public void runIDS(Node startNode)
    {
        int depth = 0;
        
        // while destination city isn't found, call Depth Limited Search
        // (starting city, depth)
        while (!isTargetFound)
        {
            System.out.println();
            DLS(startNode, depth);
            depth++;
        }
    }
    
    // Depth Limited Search
    private void DLS(Node startNode, int depth) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // create Node stack 
        Stack<Node> stack = new Stack<>();
        // starting city is set to depth level 0
        startNode.setDepth_Level(0);
        // visited linked list to keep track of visited cities
        LinkedList<String> visited = new LinkedList<String>();
        
        // push first city in to the stack
        stack.push(startNode);
        
        while (!stack.isEmpty())
        {
            // pop element and print it out as visited to currentNode
            Node currentNode = stack.pop();
            System.out.print(currentNode + " ");
            
            // if current city is equal to the destination city exit
            if (currentNode.equals(this.destination_City))
            {
                System.out.println("You have reached Bucharest");
                this.isTargetFound = true;
                return;
            }
            
            // check depth parameter. If the current cities' depth is >= depth
            // clear visited stack
            if (currentNode.getDepth_Level() >= depth)
            {
                visited.clear();
                continue;
            }
            
            // if the visited cities arraylist doesn't contains the current node 
            // run for loop that grabs current city's neighborlist
            if(!visited.contains(currentNode.getCity_name()))
            {
                for (Node node : currentNode.getAdjacenciesList())
                {
                    // increment the depth by one to the next node in the neighborlist of current city
                    node.setDepth_Level(currentNode.getDepth_Level() + 1);
                    // add the current city to the visited list
                    visited.add(currentNode.getCity_name());
                    // push the new city into the stack
                    stack.push(node);
                }
            }
        }  
    }
}
