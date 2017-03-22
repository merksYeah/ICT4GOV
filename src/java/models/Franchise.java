/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author migue
 */
public class Franchise {
    private Vehicle[] vehicles;
    private String tradeName;
    private int franchiseId;

    public Franchise() {
        
    }

    public int getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(int franchiseId) {
        this.franchiseId = franchiseId;
    }
    

    public Franchise(String tradeName, Vehicle[] vehicles){
        this.vehicles = vehicles;
        this.tradeName = tradeName;
     }
     
    public Franchise(Vehicle[] vehicles){
        this.vehicles = vehicles;
     }
   
     
    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }
    
   
}
