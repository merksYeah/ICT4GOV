/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author migue
 */
public class MySQLUserDAO implements UserDAO {
    
    private static final String SQL_GET_USER = "select * from users where username = ? and password = ?";

    @Override
    public User getUser(User user) {
         Connection conn = MySQLDaoFactory.createConnection();
         PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(SQL_GET_USER);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                user.setUserId(Integer.parseInt(rs.getString("userid")));
                user.setRole(rs.getString("role"));
                if(rs.getString("employee_id") != null){
                    user.setEmployeeId(Integer.parseInt(rs.getString("employee_id")));
                }
                else if(rs.getString("franchise_owner_id") != null){
                    user.setFranchiseId(Integer.parseInt(rs.getString("franchise_owner_id")));
                }
                conn.close();
                return user;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLUserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
