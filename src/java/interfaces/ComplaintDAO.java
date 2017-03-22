/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import models.Complaint;
import models.Franchise;

/**
 *
 * @author migue
 */
public interface ComplaintDAO {
    public Complaint getComplaint(int referenceNumb);
    public int addComplaint(Complaint complaint);
    public ArrayList<Complaint> getUnresolvedComplaints();
    public Complaint getComplaintDetails(int referenceNumb);
    public void updateComplaintStatus(Complaint complaint);
    public void updateReply(Complaint complaint);
    public void updateHearingDate(Complaint complaint);
    public ArrayList<Complaint> getFranchiseComplaints(Franchise franchise);
    public ArrayList<Complaint> getLegalComplaints();
        
}
