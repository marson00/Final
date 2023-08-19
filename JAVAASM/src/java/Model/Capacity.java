/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Tingle
 */
public class Capacity {
    private String  capID;     //capacityID
    private int     capacity;
    private int     addOns;
    
    public Capacity(){
        
    }
    
    public Capacity(String capID, int capacity, int addOns){
        this.capID = capID;
        this.capacity = capacity;
        this.addOns = addOns;
    }

    public String getCapID() {
        return capID;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAddOns() {
        return addOns;
    }

    public void setCapID(String capID) {
        this.capID = capID;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAddOns(int addOns) {
        this.addOns = addOns;
    }

}
