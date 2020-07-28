/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Stack;

/**
 *
 * @author cecilia
 */
public class IterativeDeepeningSearch {
    
    private Node targetNode;
    private Boolean isTargetFound;
    
    public IterativeDeepeningSearch(Node node) 
    {
        this.targetNode = targetNode;
    }
    
    public void runIDS(Node startNode)
    {
        int depth = 0;
        
        while (!isTargetFound)
        {
            System.out.println();
            DLS(startNode, depth);
            depth++;
        }
        
    }

    private void DLS(Node startNode, int depth) 
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Stack<Node> stack = new Stack<>();
        startNode.setDepth_Level(0);
        stack.push(startNode);
        
        while (!stack.isEmpty())
        {
            Node currentNode = stack.pop();
            System.out.print(currentNode + " ");
            
            if (currentNode.getName().equals(this.targetNode.getName()))
            {
                System.out.println("You have reached Bucharest");
                this.isTargetFound = true;
                return;
            }
            
            if (currentNode.getDepth_Level() >= depth)
            {
                continue;
            }
            
            for (Node node : currentNode.getAdjacenciesList())
            {
                node.setDepth_Level(currentNode.getDepth_Level() + 1);
                stack.push(node);
            }
        }
            
    }
    
}
