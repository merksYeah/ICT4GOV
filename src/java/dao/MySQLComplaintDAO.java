/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import interfaces.ComplaintDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Complainant;
import models.Complaint;
import models.Franchise;
import models.Vehicle;

/**
 *
 * @author migue
 */
public class MySQLComplaintDAO implements ComplaintDAO {

    private static final String SQL_GET_COMPLAINT = "SELECT * FROM complaint c join complaint_status cs on c.complaint_status_code = cs.complaint_status_code where reference_number = ?";
    private static final String SQL_ADD_COMPLAINT = "insert into complaint(name,email,phone_number,mailing_address,complaint_status_code,complaint_type_code,franchise_id,vehicle_plate_number,mobile_number,subject,description)\n" +
                                                    "values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_GET_UNRESOLVED_COMPLAINTS = "select * from complaint c join complaint_status cs on c.complaint_status_code = cs.complaint_status_code join complaint_type ct on c.complaint_type_code = ct.complaint_type_code  where c.complaint_status_code != 5 and c.complaint_status_code != 6";
    private static final String SQL_GET_LEGAL_COMPLAINTS = "select * from complaint c join complaint_status cs on c.complaint_status_code = cs.complaint_status_code join complaint_type ct on c.complaint_type_code = ct.complaint_type_code  where c.complaint_status_code = 2";
    private static final String SQL_GET_COMPLAINT_BY_REF = "SELECT * FROM complaint c\n" +
                                                            "		 join franchise f on c.franchise_id = f.franchise_id\n" +
                                                            "         join vehicle v on v.plate_number = c.vehicle_plate_number\n" +
                                                            "         join complaint_type ct on c.complaint_type_code = ct.complaint_type_code\n" +
                                                            "         where reference_number = ?";
    private static final String SQL_UPDATE_COMPLAINT_STATUS = "update complaint set complaint_status_code = ? where reference_number = ?";
    private static final String SQL_UPDATE_REPLY = "update complaint set reply = ? where reference_number = ?";
    private static final String SQL_GET_FRANCHISE_COMPLAINTS = "SELECT * FROM complaint c \n" +
                                                               "join complaint_status cs on cs.complaint_status_code = c.complaint_status_code\n" +
                                                               "join complaint_type ct on ct.complaint_type_code = c.complaint_type_code\n" +
                                                               "join franchise f on c.franchise_id = f.franchise_id\n" +
                                                               "where f.franchise_owner_id = ?";
    private static final String SQL_UPDATE_HEARING_DATE = "update complaint set hearing_date = ? where reference_number = ?";
    
    @Override
    public Complaint getComplaint(int referenceNumb) {
            ResultSet rs = null;
            Complaint complaint = new Complaint();
            Connection conn = MySQLDaoFactory.createConnection();
       
        try {  
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_COMPLAINT);
            pstmt.setInt(1, referenceNumb);
            rs = pstmt.executeQuery();
            while(rs.next()){
                complaint.setHearingDate(rs.getString("hearing_date"));
                complaint.setComplaintStatusDesc(rs.getString("complaint_status_desc"));
                conn.close();
                return complaint;
            } 
           
        } catch (SQLException ex) {
            Logger.getLogger(MySQLComplaintDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }

    @Override
    public int addComplaint(Complaint complaint) {
        Connection conn = MySQLDaoFactory.createConnection();
         ResultSet rs = null;
         int key = 0;
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmst = conn.prepareStatement(SQL_ADD_COMPLAINT,PreparedStatement.RETURN_GENERATED_KEYS);
            pstmst.setString(1, complaint.getComplainant().getName());
            pstmst.setString(2, complaint.getComplainant().getEmail());
            pstmst.setString(3, complaint.getComplainant().getPhoneNumber());
            pstmst.setString(4, complaint.getComplainant().getMailingAddress());
            pstmst.setInt(5,complaint.getComplaintStatus());
            pstmst.setInt(6,complaint.getComplaintType());
            pstmst.setInt(7,complaint.getFranchise().getFranchiseId());
            Vehicle[] vehicles = complaint.getFranchise().getVehicles();
            pstmst.setString(8, vehicles[0].getPlateNumber());
            pstmst.setString(9, complaint.getComplainant().getMobileNumber());
            pstmst.setString(10, complaint.getSubject());
            pstmst.setString(11, complaint.getDescription());
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
    public ArrayList<Complaint> getUnresolvedComplaints() {
        Connection conn = MySQLDaoFactory.createConnection();
        ResultSet rs = null;
        ArrayList<Complaint> complaints = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_UNRESOLVED_COMPLAINTS);
            rs = pstmt.executeQuery();
             while(rs.next()){
                 Complaint complaint = new Complaint();
                 Complainant complainant = new Complainant();
                 Franchise franchise = new Franchise();
                 Vehicle vehicle = new Vehicle();
                 vehicle.setPlateNumber(rs.getString("vehicle_plate_number"));
                 Vehicle[] vehicles = {vehicle};
                 franchise.setVehicles(vehicles);
                 complainant.setName(rs.getString("name"));
                 complaint.setReference_number(rs.getInt("reference_number"));
                 complaint.setSubject(rs.getString("subject"));
                 complaint.setComplaintStatusDesc(rs.getString("complaint_status_desc"));
                 complaint.setComplaintTypeDesc(rs.getString("complaint_type_desc"));
                 complaint.setFranchise(franchise);
                 complaint.setComplainant(complainant);
                 complaint.setDateUpdated(rs.getString("date_updated"));
                 complaint.setDateFiled(rs.getString("date_filed"));
                 complaints.add(complaint);
             }
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLComplaintDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return complaints;
    }

    @Override
    public Complaint getComplaintDetails(int referenceNumb) {
        Connection conn = MySQLDaoFactory.createConnection();
        ResultSet rs = null;
        Complaint complaint = new Complaint();
        Complainant complainant = new Complainant();
        Franchise franchise = new Franchise();
        Vehicle vehicle = new Vehicle();
        try {
            PreparedStatement pstmst = conn.prepareStatement(SQL_GET_COMPLAINT_BY_REF);
            pstmst.setInt(1, referenceNumb);
            rs = pstmst.executeQuery();
            while(rs.next()){
                complainant.setName(rs.getString("name"));
                complainant.setEmail(rs.getString("email"));
                complainant.setMobileNumber(rs.getString("mobile_number"));
                complainant.setPhoneNumber(rs.getString("phone_number"));
                complainant.setMailingAddress(rs.getString("mailing_address"));
                vehicle.setVehicleType(rs.getString("vehicle_type"));
                vehicle.setPlateNumber(rs.getString("plate_number"));
                vehicle.setBodyNumber(rs.getInt("body_no"));
                Vehicle[] vehicles = {vehicle};
                franchise.setVehicles(vehicles);
                franchise.setTradeName(rs.getString("trade_name"));
                complaint.setDateFiled(rs.getString("date_filed"));
                complaint.setDateUpdated(rs.getString("date_updated"));
                complaint.setComplaintTypeDesc(rs.getString("complaint_type_desc"));
                complaint.setSubject(rs.getString("subject"));
                complaint.setDescription(rs.getString("description"));
                complaint.setComplainant(complainant);
                complaint.setFranchise(franchise);
                complaint.setReply(rs.getString("reply"));
                conn.close();
                return complaint;
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLComplaintDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void updateComplaintStatus(Complaint complaint) {
        Connection conn = MySQLDaoFactory.createConnection();
        ResultSet rs = null;
        try {
            PreparedStatement pstmst = conn.prepareStatement(SQL_UPDATE_COMPLAINT_STATUS);
            conn.setAutoCommit(false);
            pstmst.setInt(1, complaint.getComplaintStatus());
            pstmst.setInt(2, complaint.getReference_number());
            pstmst.executeUpdate();
            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLComplaintDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public ArrayList<Complaint> getFranchiseComplaints(Franchise franchise) {
        Connection conn = MySQLDaoFactory.createConnection();
        ResultSet rs = null;
        ArrayList<Complaint> complaints = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_FRANCHISE_COMPLAINTS);
            pstmt.setInt(1, franchise.getFranchiseId());
            rs = pstmt.executeQuery();
             while(rs.next()){
                 System.out.println("hey");
                 Complaint complaint = new Complaint();
                 Complainant complainant = new Complainant();
                 Vehicle vehicle = new Vehicle();
                 vehicle.setPlateNumber(rs.getString("vehicle_plate_number"));
                 Vehicle[] vehicles = {vehicle};
                 franchise.setVehicles(vehicles);
                 complainant.setName(rs.getString("name"));
                 complaint.setReference_number(rs.getInt("reference_number"));
                 complaint.setSubject(rs.getString("subject"));
                 complaint.setComplaintStatusDesc(rs.getString("complaint_status_desc"));
                 complaint.setComplaintTypeDesc(rs.getString("complaint_type_desc"));
                 complaint.setFranchise(franchise);
                 complaint.setComplainant(complainant);
                 complaint.setDateUpdated(rs.getString("date_updated"));
                 complaint.setDateFiled(rs.getString("date_filed"));
                 complaints.add(complaint);
             }
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLComplaintDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return complaints;
    }

    @Override
    public void updateReply(Complaint complaint) {
        Connection conn = MySQLDaoFactory.createConnection();
        ResultSet rs = null;
        try {
            PreparedStatement pstmst = conn.prepareStatement(SQL_UPDATE_REPLY);
            conn.setAutoCommit(false);
            pstmst.setString(1, complaint.getReply());
            pstmst.setInt(2, complaint.getReference_number());
            pstmst.executeUpdate();
            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLComplaintDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Complaint> getLegalComplaints() {
        Connection conn = MySQLDaoFactory.createConnection();
        ResultSet rs = null;
        ArrayList<Complaint> complaints = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL_GET_LEGAL_COMPLAINTS);
            rs = pstmt.executeQuery();
             while(rs.next()){
                 Complaint complaint = new Complaint();
                 Complainant complainant = new Complainant();
                 Franchise franchise = new Franchise();
                 Vehicle vehicle = new Vehicle();
                 vehicle.setPlateNumber(rs.getString("vehicle_plate_number"));
                 Vehicle[] vehicles = {vehicle};
                 franchise.setVehicles(vehicles);
                 complainant.setName(rs.getString("name"));
                 complaint.setReference_number(rs.getInt("reference_number"));
                 complaint.setSubject(rs.getString("subject"));
                 complaint.setComplaintStatusDesc(rs.getString("complaint_status_desc"));
                 complaint.setComplaintTypeDesc(rs.getString("complaint_type_desc"));
                 complaint.setFranchise(franchise);
                 complaint.setComplainant(complainant);
                 complaint.setDateUpdated(rs.getString("date_updated"));
                 complaint.setDateFiled(rs.getString("date_filed"));
                 complaints.add(complaint);
             }
             conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLComplaintDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return complaints;
    }

    @Override
    public void updateHearingDate(Complaint complaint) {
        Connection conn = MySQLDaoFactory.createConnection();
        ResultSet rs = null;
        try {
            PreparedStatement pstmst = conn.prepareStatement(SQL_UPDATE_HEARING_DATE);
            String datetimeLocal = complaint.getHearingDate().replace("T"," ") + ":00";
            java.sql.Timestamp timestamp = Timestamp.valueOf(datetimeLocal);
            conn.setAutoCommit(false);
            pstmst.setInt(2, complaint.getReference_number());
            pstmst.setTimestamp(1, timestamp);
            pstmst.executeUpdate();
            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLComplaintDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
