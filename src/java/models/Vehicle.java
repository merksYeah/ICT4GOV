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
public class Vehicle {

    private String plateNumber;
    private String vehicleType;
    private int bodyNumber;
    
    public Vehicle(String plateNumber, String vehicleType, int bodyNumber){
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.bodyNumber = bodyNumber;
    }
    
    public Vehicle(String plateNumber, String vehicleType){
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
    }

    public Vehicle() {
       
    }
    
    

    public int getBodyNumber() {
        return bodyNumber;
    }

    public void setBodyNumber(int bodyNumber) {
        this.bodyNumber = bodyNumber;
    }
   
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
}
