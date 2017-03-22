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
public class Complaint {

    private Complainant complainant;
    private Franchise franchise;
    private String subject;
    private String description;
    private int complaintType;
    private int complaintStatus;
    private String hearingDate;
    private String reply;

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
    

    public String getDateFiled() {
        return dateFiled;
    }

    public void setDateFiled(String dateFiled) {
        this.dateFiled = dateFiled;
    }
    private String dateFiled;

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
    private String dateUpdated;
    private String complaintStatusDesc;
    private String complaintTypeDesc;
    private int reference_number;

    public String getComplaintTypeDesc() {
        return complaintTypeDesc;
    }

    public void setComplaintTypeDesc(String complaintTypeDesc) {
        this.complaintTypeDesc = complaintTypeDesc;
    }
    

    
    public int getReference_number() {
        return reference_number;
    }

    public void setReference_number(int reference_number) {
        this.reference_number = reference_number;
    }
   

    public String getComplaintStatusDesc() {
        return complaintStatusDesc;
    }

    public void setComplaintStatusDesc(String complaintStatusDesc) {
        this.complaintStatusDesc = complaintStatusDesc;
    }
    

    public String getHearingDate() {
        return hearingDate;
    }

    public void setHearingDate(String hearingDate) {
        this.hearingDate = hearingDate;
    }
    

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(int complaintStatus) {
        this.complaintStatus = complaintStatus;
    }
    
    
    public Complaint(Complainant complainant, Franchise franchise, int complaintType,int complaintStatus){
        this.complainant = complainant;
        this.franchise = franchise;
        this.complaintType = complaintType;
        this.complaintStatus = complaintStatus;
    }
    
    public Complaint(){
        
    }


    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }
    
    public Complainant getComplainant() {
        return complainant;
    }

    public void setComplainant(Complainant complainant) {
        this.complainant = complainant;
    }

    public int getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(int complaintType) {
        this.complaintType = complaintType;
    }
    
    
    
}
