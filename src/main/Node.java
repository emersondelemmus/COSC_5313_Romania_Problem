/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emerson de Lemmus, Adrien Fabian
 */

// Class node consists of auto generated Getters and Setters for the following items:
// City Names
// Adjacency Lists (Neighbour lists)
// and depth_level
public class Node {
    
    public String city_name;
    public int depth_Level = 0;
    public List<Node> adjacenciesList;
    
    public Node(String city_name)
    {
        this.city_name = city_name;
        this.adjacenciesList = new ArrayList<>();
    }
    
    public void addNeighbour (Node node)
    {
        this.adjacenciesList.add(node);
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getDepth_Level() {
        return depth_Level;
    }

    public void setDepth_Level(int depth_Level) {
        this.depth_Level = depth_Level;
    }

    public List<Node> getAdjacenciesList() {
        return adjacenciesList;
    }

    public void setAdjacenciesList(List<Node> adjacenciesList) {
        this.adjacenciesList = adjacenciesList;
    }
    
    
    
    public String toString()
    {
        return this.city_name;      
    }
    
    

}
