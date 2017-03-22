/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Blob;

/**
 *
 * @author migue
 */
public class Attachment {
    private Long id;
    private Blob fileData;
    private int caseNumber;
    private String fileName;

  
   

    public Attachment(Long id, String fileName, Blob fileData, int caseNumber){
        this.id = id;
        this.fileName = fileName;
        this.fileData = fileData;
        this.caseNumber = caseNumber;
    }

    public Attachment() {
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blob getFileData() {
        return fileData;
    }

    public void setFileData(Blob fileData) {
        this.fileData = fileData;
    }

     public int getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(int caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
   
}
