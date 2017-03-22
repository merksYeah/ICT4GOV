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
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author migue
 */
public class MySQLDaoFactory extends DAOFactory {
        private static final String driverName = "com.mysql.jdbc.driver";
        private static final String dataSourceName = "java:comp/env/jdbc/ict4govDB";
        
       public static Connection createConnection() {
           try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup(dataSourceName);
            Connection conn = ds.getConnection();
            return conn;
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(MySQLDaoFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }

    @Override
    public ComplaintDAO getComplaintDAO() {
        return new MySQLComplaintDAO();
    }
    
    @Override
    public FranchiseDAO getFranchiseDAO(){
        return new MySQLFranchiseDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySQLUserDAO();
    }

    @Override
    public ApplicationDAO getApplicationDAO() {
        return new MySQLApplicationDAO();
    }
       
}
