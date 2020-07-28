/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.*;

/**
 *
 * @author cecilia
 */
public class Node {
    
    private String city_name;
    private int depth_Level = 0;
    private List<Node> adjacenciesList;
    
    public Node(String city_name)
    {
        this.city_name = city_name;
        this.adjacenciesList = new ArrayList<>();
    }
    
    public String toString()
    {
        return this.city_name;      
    }
    
    public void addNeighbour (Node node)
    {
        this.adjacenciesList.add(node);
    }

    public String getName() {
        return city_name;
    }

    public void setName(String name) {
        this.city_name = name;
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
    
   
    
    
}
