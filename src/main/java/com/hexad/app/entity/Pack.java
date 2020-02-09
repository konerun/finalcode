package com.hexad.app.entity;

/**
 *  This is entity object
 *  It just holds the size of the packet and cost of that packet
 */
public class Pack {

    private int size;
    private double cost;

    public Pack(int size,double cost){
        this.size = size;
        this.cost = cost;
    }

    public int getSize() { return size; }

    public double getCost() { return cost; }
}
