/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.FranchiseDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Franchise;

/**
 *
 * @author migue
 */
public class MySQLFranchiseDAO implements FranchiseDAO {
    private static final String SQL_GET_FRANCHISE_BY_PLATE = "SELECT franchise_id from franchise where vehicle_plate_number = ?";

    @Override
    public Franchise getFranchiseByPlate(String vehicle_plate_number) {
        ResultSet rs = null;
        PreparedStatement pstmst;
        Connection conn = MySQLDaoFactory.createConnection();
        Franchise franchise = new Franchise();
        try {
            pstmst = conn.prepareStatement(SQL_GET_FRANCHISE_BY_PLATE);
            pstmst.setString(1, vehicle_plate_number);
            rs = pstmst.executeQuery();
            while(rs.next()){
                franchise.setFranchiseId(rs.getInt("franchise_id"));
            }
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLFranchiseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return franchise;
    }
}
