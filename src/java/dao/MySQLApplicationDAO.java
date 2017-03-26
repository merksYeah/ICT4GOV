/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.ApplicationDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Application;
import models.Attachment;
import models.Franchise;

/**
 *
 * @author migue
 */
public class MySQLApplicationDAO implements ApplicationDAO {
    
    private static final String SQL_CREATE_APPLICATION = "insert into application(first_name,application_status_id,middle_name,last_name) values(?,?,?,?)";
    private static final String SQL_GET_APPLICATIONS = "select * from application a join application_status aps on a.application_status_id = aps.application_status_id where a.application_status_id = 1 or a.application_status_id = 2 or a.application_status_id = 3";
    private static final String SQL_GET_LEGAL_APPLICATIONS = "select * from application a join application_status aps on a.application_status_id = aps.application_status_id where a.application_status_id = 4 or a.application_status_id = 6";
    private static final String SQL_GET_LEGAL_REREGISTER = "select * from renewal_application a join application_status aps on a.application_status_id = aps.application_status_id join franchise f on f.franchise_id = a.franchise_id where a.application_status_id = 4 or a.application_status_id = 6";
    private static final String SQL_GET_APPLICATION_BY_CASE = "select * from application a join application_status ast on a.application_status_id = ast.application_status_id where case_number = ?";
    private static final String SQL_GET_REREGISTRATION_BY_CASE = "select * from renewal_application a join application_status aps on a.application_status_id = aps.application_status_id join franchise f on f.franchise_id = a.franchise_id  where renewal_case_number = ?";
    private static final String SQL_GET_ATTACHMENTS_BY_CASE = "select * from application a join attachment ac on a.case_number = ac.case_number where a.case_number = ?";
    private static final String SQL_GET_ATTACHMENTSRE_BY_CASE = "select * from renewal_application a join attachmentre ac on a.renewal_case_number = ac.case_number where ac.case_number = ?";
    private static final String SQL_UPDATE_INSPECTION_DATE = "update application set inspection_date = ?, application_status_id = ? where case_number = ?";
    private static final String SQL_UPDATE_REREGISTER_INSPECTION_DATE = "update renewal_application set inspection_date = ?, application_status_id = ? where renewal_case_number = ?";
    private static final String SQL_UPDATE_HEARING_DATE = "update application set hearing_date = ?, application_status_id = ? where case_number = ?"; 
    private static final String SQL_UPDATE_REREGISTER_HEARING_DATE = "update renewal_application set hearing_date = ?, application_status_id = ? where renewal_case_number = ?"; 
    private static final String SQL_UPDATE_APPLICATION_STATUS = "update application set application_status_id = ? where case_number = ?";
    private static final String SQL_UPDATE_REREGISTER_STATUS = "update renewal_application set application_status_id = ? where renewal_case_number = ?";
    private static final String SQL_GET_MOTOR_APPLICATIONS = "select * from application a join application_status aps on a.application_status_id = aps.application_status_id where a.application_status_id = 5 or a.application_status_id = 7";
    private static final String SQL_GET_MOTOR_REREGISTER = "select * from renewal_application a join application_status aps on a.application_status_id = aps.application_status_id join franchise f on f.franchise_id = a.franchise_id where a.application_status_id = 5 or a.application_status_id = 7";
    private static final String SQL_CREATE_REREGISTER = "insert into renewal_application(franchise_id,vehicle_plate_number,application_status_id) values(?,?,?)";
    private static final String SQL_GET_FRANCHISE = "select * from franchise where vehicle_plate_number = ?";
    private static final String SQL_GET_REREGISTER = "select * from renewal_application a join application_status aps on a.application_status_id = aps.application_status_id join franchise f on f.franchise_id = a.franchise_id where a.application_status_id = 1 or a.application_status_id = 2 or a.application_status_id = 3";
     
    @Override
    public int createApplication(String first_name, String middle_name, String last_name) {
         Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
         int key = 0;
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmst = conn.prepareStatement(SQL_CREATE_APPLICATION,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmst.setString(1, first_name);
            pstmst.setInt(2, 1);
            pstmst.setString(3, middle_name);
            pstmst.setString(4, last_name);
            pstmst.executeUpdate();
            rs = pstmst.getGeneratedKeys();
            conn.commit();
            if(rs.next()){
                key = rs.getInt(1);
                conn.close();
                return key;
                }
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLComplaintDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }

    @Override
    public ArrayList<Application> getApplications() {
       Connection conn = MySQLDaoFactory.createConnection();
       ResultSet rs = null;
       ArrayList<Application> applications = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_APPLICATIONS);
             rs = pstmt.executeQuery();
            while(rs.next()){
                Application app = new Application();
                app.setCaseNumber(rs.getInt("case_number"));
                app.setApplicantName(rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name"));
                app.setDateReceived(rs.getString("date_received"));
                app.setDateUpdated(rs.getString("date_updated"));
                app.setStatus(rs.getString("application_status_desc"));
                app.setInspectionDate(rs.getString("inspection_date"));
                applications.add(app);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applications;
    }
    
    @Override
    public ArrayList<Application> getLegalApplications() {
       Connection conn = MySQLDaoFactory.createConnection();
       ResultSet rs = null;
       ArrayList<Application> applications = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_LEGAL_APPLICATIONS);
             rs = pstmt.executeQuery();
            while(rs.next()){
                Application app = new Application();
                app.setCaseNumber(rs.getInt("case_number"));
                app.setApplicantName(rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name"));
                app.setDateReceived(rs.getString("date_received"));
                app.setDateUpdated(rs.getString("date_updated"));
                app.setStatus(rs.getString("application_status_desc"));
                applications.add(app);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applications;
    }

    @Override
    public Application getApplicationByCase(Application app) {
          Connection conn = MySQLDaoFactory.createConnection();
       ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_APPLICATION_BY_CASE);
             pstmt.setInt(1,app.getCaseNumber());
             rs = pstmt.executeQuery();
            while(rs.next()){
                app.setCaseNumber(rs.getInt("case_number"));
                app.setApplicantName(rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name"));
                app.setDateReceived(rs.getString("date_received"));
                app.setDateUpdated(rs.getString("date_updated"));
                app.setHearingDate(rs.getString("hearing_date"));
                app.setInspectionDate(rs.getString("inspection_date"));
                
                app.setStatus(rs.getString("application_status_desc"));
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return app;
    }

    @Override
    public ArrayList<Attachment> getAttachmentsByCase(Application app) {
         Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
         ArrayList<Attachment> attachments = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ATTACHMENTS_BY_CASE);
             pstmt.setInt(1,app.getCaseNumber());
             rs = pstmt.executeQuery();
            while(rs.next()){
                Attachment att = new Attachment();
                att.setId(rs.getLong("id"));
                att.setFileName(rs.getString("file_name"));
                attachments.add(att);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attachments;
    }

    @Override
    public void updateApplicationInspection(Application app) {
        Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
        try {
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_INSPECTION_DATE);
             String datetimeLocal = app.getInspectionDate().replace("T"," ") + ":00";
             java.sql.Timestamp timestamp = Timestamp.valueOf(datetimeLocal);
             conn.setAutoCommit(false);
             pstmt.setTimestamp(1, timestamp);
             pstmt.setInt(2, app.getStatusId());
             pstmt.setInt(3, app.getCaseNumber());
             pstmt.executeUpdate();
             conn.commit();
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     @Override
    public void updateApplicationStatus(Application app) {
        Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
        try {
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_APPLICATION_STATUS);
             conn.setAutoCommit(false);
             pstmt.setInt(1, app.getStatusId());
             pstmt.setInt(2, app.getCaseNumber());
             pstmt.executeUpdate();
             conn.commit();
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateApplicationHearing(Application app) {
        Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
        try {
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_HEARING_DATE);
             String datetimeLocal = app.getHearingDate().replace("T"," ") + ":00";
             java.sql.Timestamp timestamp = Timestamp.valueOf(datetimeLocal);
             conn.setAutoCommit(false);
             pstmt.setTimestamp(1, timestamp);
             pstmt.setInt(2, app.getStatusId());
             pstmt.setInt(3, app.getCaseNumber());
             pstmt.executeUpdate();
             conn.commit();
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Application> getMotorApplications() {
        Connection conn = MySQLDaoFactory.createConnection();
       ResultSet rs = null;
       ArrayList<Application> applications = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_MOTOR_APPLICATIONS);
             rs = pstmt.executeQuery();
            while(rs.next()){
                Application app = new Application();
                app.setCaseNumber(rs.getInt("case_number"));
                app.setApplicantName(rs.getString("first_name") + " " + rs.getString("middle_name") + " " + rs.getString("last_name"));
                app.setDateReceived(rs.getString("date_received"));
                app.setDateUpdated(rs.getString("date_updated"));
                app.setStatus(rs.getString("application_status_desc"));
                applications.add(app);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applications;
    }

    @Override
    public int createReregister(String plate_number, int franchiseId) {
       Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
         int key = 0;
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmst = conn.prepareStatement(SQL_CREATE_REREGISTER,PreparedStatement.RETURN_GENERATED_KEYS);
            System.out.println(franchiseId);
            pstmst.setInt(1, franchiseId);
            pstmst.setString(2, plate_number);
            pstmst.setInt(3, 1);
            pstmst.executeUpdate();
            rs = pstmst.getGeneratedKeys();
            conn.commit();
            if(rs.next()){
                key = rs.getInt(1);
                conn.close();
                return key;
                }
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLComplaintDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; 
    }

    @Override
    public int getFranchiseId(String plate_number) {
       Connection conn = MySQLDaoFactory.createConnection();
       ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_FRANCHISE);
            pstmt.setString(1, plate_number);
            rs = pstmt.executeQuery();
            while(rs.next()){
                int key = rs.getInt("franchise_id");
                conn.close();
                return key;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    @Override
    public ArrayList<Application> getReRegister() {
         Connection conn = MySQLDaoFactory.createConnection();
       ResultSet rs = null;
       ArrayList<Application> applications = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_REREGISTER);
             rs = pstmt.executeQuery();
            while(rs.next()){
                Application app = new Application();
                app.setTradeName(rs.getString("trade_name"));
                app.setPlateNumber(rs.getString("vehicle_plate_number"));
                app.setCaseNumber(rs.getInt("renewal_case_number"));
                app.setDateReceived(rs.getString("date_filed"));
                app.setDateUpdated(rs.getString("date_updated"));
                app.setStatus(rs.getString("application_status_desc"));
                app.setInspectionDate(rs.getString("inspection_date"));
                applications.add(app);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applications;
    }

    @Override
    public Application getReRegistrationByCase(Application app) {
          Connection conn = MySQLDaoFactory.createConnection();
       ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_REREGISTRATION_BY_CASE);
             pstmt.setInt(1,app.getCaseNumber());
             rs = pstmt.executeQuery();
            while(rs.next()){
                app.setTradeName(rs.getString("trade_name"));
                app.setPlateNumber(rs.getString("vehicle_plate_number"));
                app.setCaseNumber(rs.getInt("renewal_case_number"));
                app.setDateReceived(rs.getString("date_filed"));
                app.setDateUpdated(rs.getString("date_updated"));
                app.setStatus(rs.getString("application_status_desc"));
                app.setInspectionDate(rs.getString("inspection_date"));
                
                //app.setStatus(rs.getString("application_status_desc"));
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return app;
    }

    @Override
    public ArrayList<Attachment> getAttachmentsReByCase(Application app) {
       Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
         ArrayList<Attachment> attachments = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ATTACHMENTSRE_BY_CASE);
             pstmt.setInt(1,app.getCaseNumber());
             rs = pstmt.executeQuery();
            while(rs.next()){
                Attachment att = new Attachment();
                att.setId(rs.getLong("id"));
                att.setFileName(rs.getString("file_name"));
                attachments.add(att);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return attachments;
    }

    @Override
    public void updateReRegisterInspection(Application app) {
        Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
        try {
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_REREGISTER_INSPECTION_DATE);
             String datetimeLocal = app.getInspectionDate().replace("T"," ") + ":00";
             java.sql.Timestamp timestamp = Timestamp.valueOf(datetimeLocal);
             conn.setAutoCommit(false);
             pstmt.setTimestamp(1, timestamp);
             pstmt.setInt(2, app.getStatusId());
             pstmt.setInt(3, app.getCaseNumber());
             pstmt.executeUpdate();
             conn.commit();
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateReRegisterStatus(Application app) {
       Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
        try {
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_REREGISTER_STATUS);
             conn.setAutoCommit(false);
             pstmt.setInt(1, app.getStatusId());
             pstmt.setInt(2, app.getCaseNumber());
             pstmt.executeUpdate();
             conn.commit();
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateReRegisterHearing(Application app) {
        Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
        try {
             PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_REREGISTER_HEARING_DATE);
             String datetimeLocal = app.getHearingDate().replace("T"," ") + ":00";
             java.sql.Timestamp timestamp = Timestamp.valueOf(datetimeLocal);
             conn.setAutoCommit(false);
             pstmt.setTimestamp(1, timestamp);
             pstmt.setInt(2, app.getStatusId());
             pstmt.setInt(3, app.getCaseNumber());
             pstmt.executeUpdate();
             conn.commit();
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Application> getLegalReRegister() {
       Connection conn = MySQLDaoFactory.createConnection();
       ResultSet rs = null;
       ArrayList<Application> applications = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_LEGAL_REREGISTER);
             rs = pstmt.executeQuery();
            while(rs.next()){
                Application app = new Application();
                app.setTradeName(rs.getString("trade_name"));
                app.setPlateNumber(rs.getString("vehicle_plate_number"));
                app.setCaseNumber(rs.getInt("renewal_case_number"));
                app.setDateReceived(rs.getString("date_filed"));
                app.setDateUpdated(rs.getString("date_updated"));
                app.setStatus(rs.getString("application_status_desc"));
                app.setInspectionDate(rs.getString("inspection_date"));
                applications.add(app);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applications;
    }

    @Override
    public ArrayList<Application> getMotorReRegister() {
         Connection conn = MySQLDaoFactory.createConnection();
       ResultSet rs = null;
       ArrayList<Application> applications = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_MOTOR_REREGISTER);
             rs = pstmt.executeQuery();
            while(rs.next()){
                Application app = new Application();
                app.setTradeName(rs.getString("trade_name"));
                app.setPlateNumber(rs.getString("vehicle_plate_number"));
                app.setCaseNumber(rs.getInt("renewal_case_number"));
                app.setDateReceived(rs.getString("date_filed"));
                app.setDateUpdated(rs.getString("date_updated"));
                app.setStatus(rs.getString("application_status_desc"));
                app.setInspectionDate(rs.getString("inspection_date"));
                applications.add(app);
            } 
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLApplicationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return applications;
    }
    
    
}
