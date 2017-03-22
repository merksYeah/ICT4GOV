/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import models.Franchise;

/**
 *
 * @author migue
 */
public interface FranchiseDAO {
    public Franchise getFranchiseByPlate(String vehicle_plate_number);
    
}
