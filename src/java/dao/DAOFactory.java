/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.ApplicationDAO;
import interfaces.ComplaintDAO;
import interfaces.FranchiseDAO;
import interfaces.UserDAO;

/**
 *
 * @author migue
 */
public abstract class DAOFactory {
    public static final int MYSQL = 1;
     
    public abstract ComplaintDAO getComplaintDAO();
    public abstract FranchiseDAO getFranchiseDAO();
    public abstract UserDAO getUserDAO();
    public abstract ApplicationDAO getApplicationDAO();
     
     
     public static DAOFactory getDAOFactory(int factoryNumber){
         switch(factoryNumber){
             case MYSQL:
                return new MySQLDaoFactory();
             default:
                return null;
            }
        }
    }
    

