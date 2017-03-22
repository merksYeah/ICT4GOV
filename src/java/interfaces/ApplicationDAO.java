/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import models.Application;
import models.Attachment;

/**
 *
 * @author migue
 */
public interface ApplicationDAO {
    public int createApplication(String applicant_name);
    public ArrayList<Application> getApplications();
    public Application getApplicationByCase(Application app);
    public ArrayList<Attachment> getAttachmentsByCase(Application app);
    public Application getReRegistrationByCase(Application app);
    public ArrayList<Attachment> getAttachmentsReByCase(Application app);
    public void updateApplicationInspection(Application app);
    public void updateApplicationStatus(Application app);
    public void updateApplicationHearing(Application app);
    public void updateReRegisterInspection(Application app);
    public void updateReRegisterStatus(Application app);
    public void updateReRegisterHearing(Application app);
    public ArrayList<Application> getLegalApplications();
    public ArrayList<Application> getMotorApplications();
    public ArrayList<Application> getLegalReRegister();
    public ArrayList<Application> getMotorReRegister();
    public int createReregister(String plate_number, int franchiseId);
    public int getFranchiseId(String plate_number);
    public ArrayList<Application> getReRegister();

}
